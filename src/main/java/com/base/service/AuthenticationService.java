package com.base.service;

import com.base.config.security.JwtTokenUtil;
import com.base.dao.entity.TokenEntity;
import com.base.dao.entity.UserEntity;
import com.base.dao.entity.UserInfoEntity;
import com.base.dao.repository.TokenRepo;
import com.base.dao.repository.UserInfoRepo;
import com.base.dao.repository.UserRepo;
import com.base.enums.ErrorSystem;
import com.base.model.request.RegisterRequest;
import com.base.model.response.BaseResponse;
import com.base.model.response.JwtResponse;
import com.base.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AuthenticationService extends BaseService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<UserEntity> userEntity = userRepo.findByUsernameOrAlias(username, username);
            if (userEntity.isPresent()) {
                return (UserDetails) userEntity.get();
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public String getAlias(String username) {
        String alias = null;
        StringBuilder temp = new StringBuilder(username.split("@")[0]);
        int index = 1;
        while (alias == null) {
            List<UserEntity> aliasList = userRepo.findAllByAlias(temp.toString());
            if (aliasList.size() > 0) {
                String name = temp.toString().replaceAll("\\d+", "");
                temp.setLength(0);
                temp.append(name);
                temp.append(index);
                index++;
            } else {
                alias = temp.toString();
            }
        }
        return alias;
    }

    public BaseResponse<JwtResponse> register(RegisterRequest request) {
        try {
            BaseResponse valid = validRegister(request);
            if (!valid.isSuccess()) return valid;
            String alias = getAlias(request.getUsername());

            UserEntity entity = new UserEntity()
                    .setEnabled(true)
                    .setUsername(request.getUsername())
                    .setAlias(alias)
                    .setPassword(passwordEncoder.encode(request.getPassword()))
                    .setRoles(Arrays.asList(AppConstant.Role.USER))
//                    .setIp()
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            userRepo.save(entity);

            UserInfoEntity userInfo = new UserInfoEntity()
                    .setAlias(alias)
                    .setUsername(request.getUsername())
                    .setModifiedDate(new Date())
                    .setAccount("123456");
            userInfo.encrypt();
            userInfoRepo.save(userInfo);
            JwtResponse tokenInfo = generateToken(request.getUsername(), request.getPassword());
            tokenInfo.setAlias(alias);
            return new BaseResponse(ErrorSystem.SUCCESS, tokenInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public JwtResponse generateToken(String username, String password) {
        try {

            authenticate(username, password);
            Optional<UserEntity> userOp = userRepo.findByUsernameOrAlias(username, username);
            if (!userOp.isPresent()) {
                return null;
            }
            UserEntity userDetails = userOp.get();
            JwtResponse token = jwtTokenUtil.generateToken(userDetails);

            Optional<TokenEntity> tokenOp = tokenRepo.findByUsernameOrAlias(username, username);
            TokenEntity tokenEntity = null;
            if (tokenOp.isPresent()) {
                tokenEntity = tokenOp.get();
            } else {
                tokenEntity = new TokenEntity()
                        .setUsername(userOp.get().getUsername())
                        .setAlias(userOp.get().getAlias())
                        .setRoles(userOp.get().getRoles());
            }
            InetAddress ip = InetAddress.getLocalHost();
            tokenEntity.setModifiedDate(new Date());
            tokenEntity.setToken(token.getToken());
            tokenEntity.setId(ip.getHostAddress());
            tokenEntity.setExpTime(token.getExpTime());
            tokenRepo.save(tokenEntity);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private BaseResponse validRegister(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return new BaseResponse(ErrorSystem.DATA_EXIST);
        } else if (!Pattern.compile(AppConstant.Regex.email).matcher(request.getUsername()).matches()) {
            return new BaseResponse(ErrorSystem.EMAIL_NOT_FORMAT);
        } else if (!Pattern.compile(AppConstant.Regex.password).matcher(request.getPassword()).matches()) {
            return new BaseResponse(ErrorSystem.PASS_NOT_FORMAT);
        }
        return new BaseResponse(ErrorSystem.SUCCESS);
    }

    public BaseResponse logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // Hoặc phương thức lấy token khác
        String jwtToken = token.substring(7);
        String username = tokenUtil.getUsernameFromToken(jwtToken);
        Optional<TokenEntity> checkToken = tokenRepo.findByUsernameOrAlias(username, username);
        if (checkToken.isPresent()) {
            tokenRepo.delete(checkToken.get());
        }
        return new BaseResponse(ErrorSystem.SUCCESS);
    }
}