package kh.deli.domain.owner.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kh.deli.domain.member.order.dto.*;
import kh.deli.domain.member.order.service.OrderOrdersService;
import kh.deli.domain.member.store.dto.BasketMenu;
import kh.deli.domain.member.store.dto.StoreBasketMenuRequestDTO;
import kh.deli.domain.member.store.service.StoreBasketService;
import kh.deli.global.entity.OrdersDTO;
import kh.deli.global.exception.DeliveryDtlException;
import kh.deli.global.util.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/deliveryDtl/")
public class OrderDeliveryDtlController {

    private final OrderOrdersService orderOrdersService;
    private final StoreBasketService storeBasketService;
    private final RedisUtil redis;
    private final Gson gson;

    @RequestMapping("{redisKey}")
    public String toDeliveryDtl(Model model, @PathVariable("redisKey") String redisKey) throws Exception {
        try {
            int orderSeq = Integer.parseInt(redis.getData(redisKey));

            StoreInfoDTO storeInfoDTO = orderOrdersService.getStoreInfo(orderSeq); // 가게정보
            OrdererInfoDTO ordererInfoDTO = orderOrdersService.getOrdererInfo(orderSeq); // 주문자정보
            PayInfoDTO payInfoDTO = orderOrdersService.getPayInfo(orderSeq); // 결제정보
            OrdersDTO ordersDTO = orderOrdersService.findOrdersBySeq(orderSeq); //주문정보

            String menu_list = ordersDTO.getMenu_list();

            Type type2 = new TypeToken<List<StoreBasketMenuRequestDTO>>() {
            }.getType();
            List<StoreBasketMenuRequestDTO> basket = gson.fromJson(menu_list, type2);

            List<BasketMenu> basketMenu = storeBasketService.basketMenuListDtoToObject(basket);

            model.addAttribute("basketMenu", basketMenu);
            model.addAttribute("storeInfoDTO", storeInfoDTO);
            model.addAttribute("ordererInfoDTO", ordererInfoDTO);
            model.addAttribute("payInfoDTO", payInfoDTO);


//        StoreInfoDTO storeInfoDTO = orderOrdersService.getStoreInfo(orderSeq); // 가게정보
//        OrdererInfoDTO ordererInfoDTO =orderOrdersService.getOrdererInfo(orderSeq); // 주문자정보
//        PayInfoDTO payInfoDTO=orderOrdersService.getPayInfo(orderSeq); // 결제정보
//        OrdersDTO ordersDTO = orderOrdersService.findOrdersBySeq(orderSeq); //주문정보
//
//        String menu_list = ordersDTO.getMenu_list();
//
//        BasketDTO basket = gson.fromJson(menu_list, BasketDTO.class);
//
//        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
//
//        for (int i = 0; i<basket.getMenuList().size(); i++) {
//            MenuDTO menuDTO = orderBasketService.findMenuBySeq(basket.getMenuList().get(i).getMenuSeq());
//            List<MenuOptionDTO> menuOptionDTOList = new ArrayList<>();
//
//            for (int k = 0; k < basket.getMenuList().get(i).getOptionSeqList().size(); k++) {
//                MenuOptionDTO menuOptionDTO = orderBasketService.findMenuOptionBySeq(basket.getMenuList().get(i).getOptionSeqList().get(k));
//                menuOptionDTOList.add(menuOptionDTO);
//            }
//
//            int count = basket.getMenuList().get(i).getCount();
//            int price = basket.getMenuList().get(i).getPrice();
//
//            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(menuDTO, menuOptionDTOList, count, price);
//
//            orderDetailDTOList.add(orderDetailDTO);
//        }
//
//        model.addAttribute("orderDetailDTOList", orderDetailDTOList);
//        model.addAttribute("storeInfoDTO",storeInfoDTO);
//        model.addAttribute("ordererInfoDTO",ordererInfoDTO);
//        model.addAttribute("payInfoDTO",payInfoDTO);

//        // 아래는 주소 줄이는 shortURL 코드
//        String url = "https://developers.naver.com/forum/posts/33899";
//        NaverNShortURL nShortURL = new NaverNShortURL();
//        nShortURL.toShortURL(url);
//        System.out.println(url);
        } catch (Exception e) {
            throw new DeliveryDtlException();
        }
        return "owner/deliveryDtl";
    }
    @ResponseBody
    @RequestMapping("confirm")
    public String confirmDeliveryDtl(@RequestParam("redisKey") String redisKey) {
        redis.deleteData(redisKey);
        return "/deliveryDtl/toConfirm";
    }


}
