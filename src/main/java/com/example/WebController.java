package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class WebController {

    //    @GetMapping("/profile")
//    public String profile(Model model){
//        log.info("request = "+ new Date());
//        // Tạo ra thông tin
//        List<Info> profile = new ArrayList<>();
//        String pattern = "MM/dd/yyyy HH:mm:ss";
//
//// Create an instance of SimpleDateFormat used for formatting
//// the string representation of date according to the chosen pattern
//        DateFormat df = new SimpleDateFormat(pattern);
//
//// Get the today date using Calendar object.
//        Date today = Calendar.getInstance().getTime();
//// Using DateFormat format method we can create a string
//// representation of a date with the defined format.
//        String todayAsString = df.format(today);
//        profile.add(new Info("Date", todayAsString));
//
//
//        // Đưa thông tin vào Model
//        model.addAttribute("lodaProfile", profile);
//
//        // TRả về template profile.html
//        return "profile";
//    }
    @GetMapping("/")
    public String showIndex(Model model, @RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "4") Integer pageSize) {
        List<IndexInfo> indexInfoList = new ArrayList<>();
        indexInfoList.add(new IndexInfo("img-03.jpg", "Clocks", "18 Oct 2020", "9,906 views"));
        indexInfoList.add(new IndexInfo("img-04.jpg", "Plants", "14 Oct 2020", "16,100 views"));
        indexInfoList.add(new IndexInfo("img-05.jpg", "Morning", "12 Oct 2020", "12,460 views"));
        indexInfoList.add(new IndexInfo("img-06.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-01.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-02.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-07.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-08.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-09.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-10.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-11.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-12.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-13.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-14.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-15.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-16.jpg", "dâuntp", "10 Oct 2020", "11,402 views"));

        indexInfoList.add(new IndexInfo("img-03.jpg", "Clocks-2", "18 Oct 2020", "9,906 views"));
        indexInfoList.add(new IndexInfo("img-04.jpg", "Plants-2", "14 Oct 2020", "16,100 views"));
        indexInfoList.add(new IndexInfo("img-05.jpg", "Morning", "12 Oct 2020", "12,460 views"));
        indexInfoList.add(new IndexInfo("img-06.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-01.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-02.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-07.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-08.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-09.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-10.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-11.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-12.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-13.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-14.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-15.jpg", "Pinky", "10 Oct 2020", "11,402 views"));
        indexInfoList.add(new IndexInfo("img-16.jpg", "dâuntp", "10 Oct 2020", "11,402 views"));

        List<IndexInfo> indexInfo = new ArrayList<>();
        int start = pageIndex*pageSize;
        int end = (pageIndex+1)*pageSize;
        for (int i = start ;i<end;i++){
            indexInfo.add(indexInfoList.get(i));
        }
        SearchResponse response = new SearchResponse()
                .setTotalData(indexInfoList.size())
                .setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .setInfo(indexInfo)
                ;


        model.addAttribute("searchResponse", response);
        return "index"; // Return the name of the Thymeleaf template (index.html)
    }


}
