package kh.deli.domain.member.myPage.controller;


import kh.deli.domain.member.myPage.service.MyPageReviewService;
import kh.deli.global.entity.OrdersDTO;
import kh.deli.global.entity.ReviewDTO;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/myPage/reviewWrite/")
@AllArgsConstructor
public class MyPageReviewViewController {

    private final MyPageReviewService myPageReviewService;
    private final HttpSession session;


    @RequestMapping("{orderSeq}")
    public String toMemberMain(Model model,@PathVariable("orderSeq") Integer order_seq) throws Exception {

        OrdersDTO dto = myPageReviewService.selectByOrderSeq(order_seq);

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArr = (JSONArray) jsonParser.parse(dto.getMenu_list()); //파싱한 다음 jsonobject로 변환

        List<String> menuNameList=new ArrayList<>();

        if (jsonArr.size() > 0) {

            for (int i = 0; i < jsonArr.size(); i++) {

                JSONObject jsonObj = (JSONObject)jsonArr.get(i);
                String menuSeq= jsonObj.get("menuSeq").toString();
                String menuName=myPageReviewService.selectMenuName(menuSeq);
                menuNameList.add(menuName);

            }
        }
        String store_name=myPageReviewService.selectByStoreSeq(dto.getStore_seq()).getStore_name();

        model.addAttribute("dto", dto);
        model.addAttribute("menuNameList",menuNameList);
        model.addAttribute("store_name",store_name);

        return "/member/myPage/memberReview";
    }

    @PostMapping("reviewInsert")
    public String reviewInsert(ReviewDTO dto, MultipartFile[] files) throws Exception {

        myPageReviewService.reviewInsert(session, dto, files);

        return "redirect:/myPage/reviewList";
    }

}