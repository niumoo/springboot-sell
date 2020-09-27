package net.codingme.sell.controller;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.service.OrderService;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *
 * 卖家端订单
 * 
 * @Author niujinpeng
 * @Date 2019/8/13 23:24
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * 
     * @param page
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer pageSize, Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<OrderDTO> orderDTOS = orderService.findList(pageRequest);
        model.addAttribute("orderDTOPage", orderDTOS);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", pageSize);
        return "order/list";
    }

    /**
     * 订单详情
     * 
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam(value = "orderId", defaultValue = "1") String orderId, Model model) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端订单详情】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("orderDTO", orderDTO);
        return "order/detail";
    }

    /**
     * 订单取消
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam(value = "orderId", defaultValue = "1") String orderId, Model model) {
        try {

            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端订单取消】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("url", "/sell/seller/order/list");
        model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        return "common/success";
    }

}
