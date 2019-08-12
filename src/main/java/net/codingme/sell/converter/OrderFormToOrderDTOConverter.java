package net.codingme.sell.converter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.form.OrderForm;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * orderForm 到 orderDTO 转换器
 *
 * @Author niujinpeng
 * @Date 2019/3/4 10:58
 */

@Slf4j
public class OrderFormToOrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        String iterms = orderForm.getItems();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = JSON.parseArray(iterms, OrderDetail.class);
        } catch (Exception e) {
            log.error("【对象转换】错误，String={}", iterms);
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
