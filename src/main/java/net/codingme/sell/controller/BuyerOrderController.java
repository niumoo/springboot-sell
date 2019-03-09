package net.codingme.sell.controller;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.converter.OrderFormToOrderDTOConverter;
import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.form.OrderForm;
import net.codingme.sell.service.BuyerService;
import net.codingme.sell.service.OrderService;
import net.codingme.sell.utils.ResultVoUtil;
import net.codingme.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 买家端
 * 
 * @Author niujinpeng
 * @Date 2019/3/2 15:37
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * 
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVo create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数校验失败,orderForm={}", orderForm);
            String message = bindingResult.getFieldError().getDefaultMessage();
            throw new SellException(ResultEnum.PARAMS_ERROR, message);
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空,orderForm={}", orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO insertResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", insertResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 订单列表
     * 
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVo list(@RequestParam(value = "openid", required = true) String openid,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        // TODO OPENDI 处理
        if (StringUtils.isEmpty(openid)) {
            log.error("【订单列表】opendId为空");
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * 
     * @param opendid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVo detail(@RequestParam(value = "openid", required = true) String opendid,
        @RequestParam(value = "orderId", required = true) String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(opendid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * 
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam(value = "openid", required = true) String openid,
        //@RequestParam(value = "orderId", required = true,
        @NotEmpty(message = "订单id不能为空") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

}
