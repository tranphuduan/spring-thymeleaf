package com.base.controller;

import com.base.IndexInfo;
import com.base.model.request.LoginRequest;
import com.base.model.response.BaseResponse;
import com.base.model.response.LoginResponse;
import com.base.model.response.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HttpSession session;

    @GetMapping("/item-detail")
    public String itemDetail(Model model, @RequestParam(defaultValue = "id") String id) {

        List<IndexInfo> indexInfoList = getListData();
        IndexInfo info = indexInfoList.stream().filter(x -> x.getId().equals(id)).findFirst().get();
        model.addAttribute("info", info);
        return "item-detail"; // Return the name of the Thymeleaf template (index.html)
    }

    @GetMapping("/error")
    public String itemDetail(Model model) {
        return "error"; // Return the name of the Thymeleaf template (index.html)
    }



    @GetMapping("/")
    public String showIndex(Model model, @RequestParam(defaultValue = "0") Integer pageIndex
            , @RequestParam(defaultValue = "32") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search) {
        session.setAttribute("sessionKeyMap", sessionKeyMap);
        session.getAttribute("SESSION_ROLE");
        List<IndexInfo> indexInfoList = getListData();
        List<IndexInfo> searchList = getListData();

        if (search != null && !search.trim().equals("")) {
            searchList = indexInfoList.stream().filter(x -> x.getTitle().equalsIgnoreCase(search.trim())).collect(Collectors.toList());
        }
        List<IndexInfo> indexInfo = new ArrayList<>();
        int start = pageIndex * pageSize;
        int end = Math.min((pageIndex + 1) * pageSize, searchList.size());
        for (int i = start; i < end; i++) {
            indexInfo.add(searchList.get(i));
        }
        int totalPage = searchList.size() / pageSize;
        if (searchList.size() % pageSize != 0) {
            totalPage += 1;
            SearchResponse response = new SearchResponse()
                    .setTotalData(searchList.size())
                    .setPageIndex(pageIndex)
                    .setTotalPage(totalPage)
                    .setPageSize(pageSize)
                    .setInfo(indexInfo);

            model.addAttribute("searchResponse", response);
            model.addAttribute("search", search == null ? "" : search);
            model.addAttribute("page", "home");
            if (checkUser) {
                LoginResponse userInfo = new LoginResponse("duantp", "Tran Phu Duan", "Barial xxxx", "xxxx");
                model.addAttribute("userInfo", userInfo);
            }
        }
        return "index"; // Return the name of the Thymeleaf template (index.html)
    }


    public List<IndexInfo> getListData() {
        List<IndexInfo> indexInfoList = new ArrayList<>();
        indexInfoList.add(new IndexInfo("1", "/img/img-03.jpg", "Clocks", "18 Oct 2020", "9,906 views"));
        indexInfoList.add(new IndexInfo("2", "/img/img-04.jpg", "Plants", "14 Oct 2020", "16,100 views"));
        indexInfoList.add(new IndexInfo("3", "/img/img-05.jpg", "Morning", "12 Oct 2020", "12,460 views"));
        indexInfoList.add(new IndexInfo("4", "/img/img-06.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("5", "/img/img-01.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("6", "/img/img-02.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("7", "/img/img-07.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("8", "/img/img-08.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("9", "/img/img-09.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("19", "/img/img-10.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("11", "/img/img-11.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("12", "/img/img-12.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("13", "/img/img-13.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("14", "/img/img-14.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("15", "/img/img-15.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("16", "/img/img-16.jpg", "dâuntp", "10 Oct 2020", "11,402 views"));

        indexInfoList.add(new IndexInfo("17", "/img/img-03.jpg", "Clocks-2", "18 Oct 2020", "9,906 views"));
        indexInfoList.add(new IndexInfo("18", "/img/img-04.jpg", "Plants-2", "14 Oct 2020", "16,100 views"));
        indexInfoList.add(new IndexInfo("19", "/img/img-05.jpg", "Morning", "12 Oct 2020", "12,460 views"));
        indexInfoList.add(new IndexInfo("20", "/img/img-06.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("21", "/img/img-01.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("22", "/img/img-02.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("23", "/img/img-07.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("24", "/img/img-08.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("25", "/img/img-09.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("26", "/img/img-10.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("27", "/img/img-11.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("28", "/img/img-12.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("29", "/img/img-13.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("30", "/img/img-14.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("31", "/img/img-15.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("32", "/img/img-16.jpg", "dâuntp", "10 Oct 2020", "11,402 views"));

        return indexInfoList;
    }
}
