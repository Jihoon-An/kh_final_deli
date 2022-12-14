package kh.deli.domain.member.order.controller;

import com.google.gson.Gson;
import kh.deli.domain.member.order.dto.OrderOrdersDTO;
import kh.deli.domain.member.order.service.OrderOrdersService;
import kh.deli.domain.member.order.service.OrderPaymentService;
import kh.deli.domain.member.store.dto.BasketDTO;
import kh.deli.domain.member.store.dto.StoreBasketMenuRequestDTO;
import kh.deli.domain.member.store.service.StoreStoreService;
import kh.deli.global.entity.PaymentDTO;
import kh.deli.global.entity.StoreDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/order/orders")
@AllArgsConstructor
public class OrderPayController {

    private final HttpSession session;
    private final OrderOrdersService orderOrdersService;
    private final StoreStoreService storeStoreService;
    private final OrderPaymentService paymentService;

    private final Gson gson;

    // 주문 페이지로 이동
    @RequestMapping("")
    public ModelAndView toOrders() throws  Exception{


        String email = (String)session.getAttribute("loginEmail");
        int accSeq = (int) session.getAttribute("acc_seq");
        OrderOrdersDTO orderOrdersDTO = new OrderOrdersDTO();

        BasketDTO basketDTO = (BasketDTO) session.getAttribute("basket");
        int orderPrice = basketDTO.getTotalPrice();
        orderOrdersDTO.setOrder_price(orderPrice);

        int storeSeq = basketDTO.getStoreSeq();
        StoreDTO storeDTO = storeStoreService.getStoreInfo(storeSeq);
        int tip = storeDTO.getStore_deli_tip();
        orderOrdersDTO.setDelivery_tip(tip);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/order/orders");
        mav.addObject("orderOrdersDTO", orderOrdersDTO);

        return mav;
    }

    @RequestMapping("selectInitInfo")
    @ResponseBody
    public OrderOrdersDTO selectInitInfo() throws Exception {
        int accSeq = (int) session.getAttribute("acc_seq");
        OrderOrdersDTO result = orderOrdersService.selectInitInfo(accSeq);
        BasketDTO basketDTO = (BasketDTO) session.getAttribute("basket");
        int storeSeq = basketDTO.getStoreSeq();
        StoreDTO storeDTO = storeStoreService.getStoreInfo(storeSeq);
        int tip = storeDTO.getStore_deli_tip();
        result.setDelivery_tip(tip);

        return result;
    }

    @RequestMapping("updateMemberAddr")
    @ResponseBody
    public void updateMemberAddr(OrderOrdersDTO ordersDTO){
        orderOrdersService.updateMemberAddr(ordersDTO);
    }

    @RequestMapping("updateMemberPhone")
    @ResponseBody
    public int updateMemberPhone(OrderOrdersDTO ordersDTO){
        String accSeq = (String)session.getAttribute("acc_seq");
        int result = orderOrdersService.updateMemberPhone(ordersDTO);
        ordersDTO.setAcc_seq(Integer.valueOf(accSeq));
        return result;
    }
    @RequestMapping("selectCouponList")
    @ResponseBody
    public List<OrderOrdersDTO> selectCouponList(){
        Integer accSeq = (Integer) session.getAttribute("acc_seq");
        return  orderOrdersService.selectCouponList(accSeq);
    }

    @RequestMapping("insertOrder")
    @ResponseBody
    public ModelAndView saveOrder(OrderOrdersDTO orderOrdersDTO) throws Exception {

        //Insert orders table
        BasketDTO basketDTO = (BasketDTO) session.getAttribute("basket");

        int storeSeq = basketDTO.getStoreSeq();
        int accSeq = (Integer) session.getAttribute("acc_seq");

        List<StoreBasketMenuRequestDTO> manuList = basketDTO.getMenuList();
        String manuListStr = gson.toJson(manuList);

        orderOrdersDTO.setMenuList(manuListStr);

        StoreDTO storeDTO = storeStoreService.getStoreInfo(storeSeq);

        int tip = storeDTO.getStore_deli_tip();

        orderOrdersDTO.setDelivery_tip(tip);
        orderOrdersDTO.setAcc_seq(accSeq);
        orderOrdersDTO.setStore_seq(storeSeq);

        int orderSeq = orderOrdersService.insertOrder(orderOrdersDTO);
        orderOrdersService.deleteCouponList(orderOrdersDTO);

        //보유포인트 차감 & 적립
        int orgPoint = Integer.parseInt(orderOrdersDTO.getOwnPoint());
        int ordPrice = orderOrdersDTO.getOrder_price();
        int usePoint = orderOrdersDTO.getUsePoint();
        int savePoint = (int) Math.round(ordPrice * 0.01);
        int ownPoint = orgPoint - usePoint + savePoint;

        orderOrdersDTO.setOwnPoint(String.valueOf(ownPoint));
        orderOrdersService.updateOwnPoint(orderOrdersDTO);

        //Insert payment table
        paymentService.put(PaymentDTO.builder()
                .order_seq(orderSeq)
                .pay_price(orderOrdersDTO.getPay_price())
                .pay_method(orderOrdersDTO.getPay_method())
                .build());

        //Delete session
        session.removeAttribute("basket");

        //Input data model, set view
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/order/detail/" + orderSeq);
        mav.addObject("orderOrdersDTO", orderOrdersDTO);

        return mav;
    }


}
