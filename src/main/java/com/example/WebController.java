package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/profile")
    public String profile(Model model){
        log.info("request = "+ new Date());
        // Tạo ra thông tin
        List<Info> profile = new ArrayList<>();
        String pattern = "MM/dd/yyyy HH:mm:ss";

// Create an instance of SimpleDateFormat used for formatting
// the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);

// Get the today date using Calendar object.
        Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String todayAsString = df.format(today);
        profile.add(new Info("Date", todayAsString));
        profile.add(new Info("fullname", "Nguyễn Hoàng Nam"));
        profile.add(new Info("nickname", "lốddaf"));
        profile.add(new Info("gmail", "loda.namnh@gmail.com"));
        profile.add(new Info("facebook", "https://www.facebook.com/nam.tehee"));
        profile.add(new Info("website", "https://loda.me"));

        // Đưa thông tin vào Model
        model.addAttribute("lodaProfile", profile);

        // TRả về template profile.html
        return "profile";
    }
}
