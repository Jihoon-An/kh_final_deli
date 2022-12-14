package kh.deli.domain.member.order.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kh.deli.domain.member.order.dto.OrderHistoryDTO;
import kh.deli.domain.member.order.service.OrderBasketService;
import kh.deli.domain.member.order.service.OrderHistoryService;
import kh.deli.domain.member.order.service.OrderOrdersService;
import kh.deli.domain.member.store.dto.BasketDTO;
import kh.deli.domain.member.store.dto.BasketMenu;
import kh.deli.domain.member.store.dto.StoreBasketMenuRequestDTO;
import kh.deli.domain.member.store.service.StoreBasketService;
import kh.deli.global.entity.MenuDTO;
import kh.deli.global.entity.MenuOptionDTO;
import kh.deli.global.entity.OrdersDTO;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/order/history")
public class OrderHistoryController {

    @Autowired
    private HttpSession session;
    @Autowired
    private OrderHistoryService orderHistoryService;


    private final OrderOrdersService orderOrdersService;
    private final OrderBasketService orderBasketService;
    private final Gson gson;
    private final StoreBasketService storeBasketService;
    @RequestMapping("")
    public String history(Model model) throws Exception {

        int acc_seq = (Integer) session.getAttribute("acc_seq");
        System.out.println(acc_seq);
        List<OrderHistoryDTO> orderList= orderHistoryService.selectOrderHistory(acc_seq);

        List<BasketMenu> menuList = new ArrayList<>();
        List<Integer> menuCountList = new ArrayList<>();
//        List<MenuOptionDTO> menuOptionList = new ArrayList<>();


       // List<BasketMenu> menuList = new ArrayList<>();
        for(int i = 0; i<orderList.size(); i++) {

            String getMenuList = orderList.get(i).getMenu_list();

            //System.out.println(getMenuList);
            Type type2 = new TypeToken<List<StoreBasketMenuRequestDTO>>(){}.getType();
            List<StoreBasketMenuRequestDTO> parseMenuList = gson.fromJson(getMenuList, type2);

            // parseMenuList??? seq??? ?????? seq ?????????,  ?????? basketMenuListDToObject????????????  name?????? ??????
            List<BasketMenu> menuListName =storeBasketService.basketMenuListDtoToObject(parseMenuList);
            //basketMenuListDtoToObject ??? ??????????????? List<BasketMenu>
//            System.out.println(menuList2);

                BasketMenu MenuName= menuListName.get(0);
            //System.out.println(menuList2.size());
                int menuCount = menuListName.size();

               // ???????????? 0???????????????????????? ????????? ??????

                //for??? ?????? ????????? ?????? ?????? orderList???????????? for??? ???????????? add



        // MenuOptionDTO MenuOption=  menuListName.get(0).getOptionList().get(0);
            //get??? 1??????????????? ??????????????? ???????????? ?????????

            //menuOptionList.add(MenuOption);
            menuList.add(MenuName);
            menuCountList.add(menuCount);
        }

       // model.addAttribute("menu_option", menuOptionList);
        model.addAttribute("menu_count_list", menuCountList); //?????? ??????
        model.addAttribute("menu_list", menuList); // parse??? ?????????
        model.addAttribute("order_list", orderList);  //join ?????? ?????? list
        return "/member/order/ordersHistory";
    }


//    @RequestMapping("{orderSeq}")
//    public String reOrder(@PathVariable("orderSeq")Integer order_seq) throws Exception {
//        System.out.println("??????seq"+order_seq);
//
//        OrdersDTO ordersDTO = orderOrdersService.findOrdersBySeq(order_seq);
//       String menu_list = ordersDTO.getMenu_list();
//
//
//
////               for(int i = 0; i<ordersDTO.getMenu_list().length(); i++){
////                   ordersDTO.getMenu_list(i);
////               }
//
//       JSONParser jsonParser = new JSONParser();
//        JSONArray jsonArr = (JSONArray) jsonParser.parse(ordersDTO.getMenu_list());
//
//            List<String> menuNameList  = new ArrayList<>();
//
//        if (jsonArr.size() > 0) {
//
//            for (Integer i = 0; i < jsonArr.size(); i++) {
//                JSONObject jsonObj = (JSONObject) jsonArr.get(i);
//              String storeSeq = jsonObj.get("storeSeq").toString();
//                String menuSeq = jsonObj.get("menuSeq").toString();
//                String optionSeqList = jsonObj.get("optionSeqList").toString();
//                String count = jsonObj.get("count").toString();
//                String price = jsonObj.get("price").toString();
//
//                menuNameList.add(storeSeq);
//                menuNameList.add(menuSeq);
//                menuNameList.add(optionSeqList);
//                menuNameList.add(count);
//                menuNameList.add(price);
//          }
//        }
//
////        StoreBasketMenuRequestDTO s= new StoreBasketMenuRequestDTO(menuNameList.get(0),menuNameList.get(1),menuNameList.get(2), menuNameList.get(3), menuNameList.get(4));
////
//        for(int i = 0; i<menuNameList.size(); i++){
//
//            storeBasketService.setBasketInSession(session, menuNameList.get(0) );
//       }
//
//        storeBasketService.setBasketInSession(session, jsonArr.toJSONString() );
//        //ystem.out.println(menuString);
////        Menu[] menu;
////
////        List<Menu> menuList = Arrays.asList(ObjectMapper.readValue(menu_list, menu.class));
////        //???????????? ?????????
//
////        Type type2 = new TypeToken<List<StoreBasketMenuRequestDTO>>(){}.getType();
////        List<StoreBasketMenuRequestDTO> basket = gson.fromJson(menu_list, type2);
//
//
//        //List<BasketMenu> basketMenu = storeBasketService.basketMenuListDtoToObject(basket);
//
//    //    List<BasketMenu> basketMenu = storeBasketService.basketMenuListDtoToObject(basket);
////        basketMenu.get(0).getMenu().
//
//       // basketMenu.get().
//
//    //
//        // System.out.println(storeSeq);
//
//        return "redirect:/basket";
//    }



    @RequestMapping("/{orderSeq}")
    public String reOrder(@PathVariable("orderSeq")Integer order_seq) throws Exception {
        session.removeAttribute("basket");
        //?????? ????????? ????????? ???????????? ???????????? ?????? ?????? ??? ??????
        OrdersDTO ordersDTO = orderOrdersService.findOrdersBySeq(order_seq);

        int storeSeq = ordersDTO.getStore_seq();
        int orderPrice = ordersDTO.getOrder_price();
        String menuListStr = ordersDTO.getMenu_list();
        //BasketDTO ???????????? ????????? ?????????

        Gson gson = new Gson();
        Type type = new TypeToken<List<StoreBasketMenuRequestDTO>>(){}.getType();
        List<StoreBasketMenuRequestDTO> basketList = gson.fromJson(menuListStr, type);
        BasketDTO basket = new BasketDTO(storeSeq,basketList,orderPrice);

        session.setAttribute("basket", basket);

        return "redirect:/basket";
    }







    @ResponseBody
    @RequestMapping("reviewChk")
    public boolean isExistReview(int order_seq){
       boolean result=  orderHistoryService.isExistReivew(order_seq);

        System.out.println(result+"????????????");
        return result;
    }
}
