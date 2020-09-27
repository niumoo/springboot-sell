package net.codingme.sell.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import net.codingme.sell.domain.OrderMaster;
import net.codingme.sell.dto.OrderDTO;

/**
 * <p>
 * OrderMaster 到 OrderDTO 转换器
 *
 * @Author niujinpeng
 * @Date 2019/2/23 22:05
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
