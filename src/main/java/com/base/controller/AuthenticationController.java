package com.base.controller;

import com.base.enums.ErrorSystem;
import com.base.model.request.LoginRequest;
import com.base.model.request.RegisterRequest;
import com.base.model.response.BaseResponse;
import com.base.model.response.JwtResponse;
import com.base.model.response.LoginResponse;
import com.base.service.AuthenticationService;
import com.base.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private HttpSession session;

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest){
//		JwtResponse result = authService.generateToken(loginRequest.getUsername(), loginRequest.getPassword());
//		return ResponseEntity.ok(result);
//	}
//
//
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> register(@RequestBody RegisterRequest request)
//			throws Exception {
//		BaseResponse<JwtResponse> result = authService.register(request);
//		return ResponseEntity.ok(result);
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public ResponseEntity<?> logout(HttpServletRequest request){
//		BaseResponse result = authService.logout(request);
//		return ResponseEntity.ok(result);
//	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("page", "login");
		return "login"; // Return the name of the Thymeleaf template (index.html)
	}


	@ResponseBody
	@PostMapping("/api/login")
	public BaseResponse<LoginResponse> loginPost(Model model, @RequestBody LoginRequest loginRequest) {
		log.info("LOGIN request = " + loginRequest);
		JwtResponse result = authService.generateToken(loginRequest.getUsername(), loginRequest.getPassword());
		if (result!=null){
			LoginResponse  loginResponse = new LoginResponse()
					.setUsername(result.getAlias())
					.setFullName("Trần Phú Duẩn")
					.setToken(result.getToken())
					.setExpTime(DateUtils.Date2String(result.getExpTime(),DateUtils.strDf));
			return new BaseResponse(ErrorSystem.SUCCESS,loginResponse);
		}

		return new BaseResponse(ErrorSystem.DATA_NOT_EXIST);
	}


	@ResponseBody
	@PostMapping("/api/confirm")
	public BaseResponse confirmPost(Model model, @RequestBody LoginRequest loginRequest) {
		log.info("confirm request = " + loginRequest);
		BaseResponse response = new BaseResponse();
		response.setCode("00");
		response.setMess("confirm");
		return response;
	}

}
