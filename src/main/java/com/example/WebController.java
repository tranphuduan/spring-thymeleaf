package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String showIndex(Model model, @RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "2") Integer pageSize) {

        List<IndexInfo> indexInfoList = getListData();
        List<IndexInfo> indexInfo = new ArrayList<>();
        int start = pageIndex * pageSize;
        int end = Math.min((pageIndex + 1) * pageSize,indexInfoList.size());
        for (int i = start; i < end; i++) {
            indexInfo.add(indexInfoList.get(i));
        }
        SearchResponse response = new SearchResponse()
                .setTotalData(indexInfoList.size())
                .setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .setInfo(indexInfo);


        model.addAttribute("searchResponse", response);
        return "index"; // Return the name of the Thymeleaf template (index.html)
    }


//    @GetMapping("/search")
//    public String search(@RequestParam("query") String query, Model model) {
//        // Thực hiện tìm kiếm dựa trên query
//        // Bạn có thể gọi hàm index ở đây và truyền thêm các thông tin cần thiết
//
//        // Ví dụ:
//        List<IndexInfo> searchResults = searchService.searchByQuery(query); // Giả sử có service tìm kiếm
//
//        // Thêm kết quả tìm kiếm vào model
//        model.addAttribute("searchResults", searchResults);
//        return "index"; // Trả về template index.html
//    }


    public List<IndexInfo> getListData() {
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

        return indexInfoList;
    }
}
