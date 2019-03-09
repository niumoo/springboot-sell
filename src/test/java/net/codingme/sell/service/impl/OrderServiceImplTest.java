package net.codingme.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.dto.OrderDTO;
import net.codingme.sell.enums.OrderStatusEnum;
import net.codingme.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final static String OPEN_ID = "123123";

    @Test
    public void save() {
        // 订单信息
        OrderDTO orderDto = new OrderDTO();
        orderDto.setBuyerName("刘备");
        orderDto.setBuyerAddress("成都");
        orderDto.setBuyerPhone("18318318318");
        orderDto.setBuyerOpenid(OPEN_ID);

        // 购物车
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("057f430a92944a3f9b68c80a54d98a8a");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("184dcec6e5824b61b04a43782e5db3be");
        o2.setProductQuantity(2);
        orderDetails.add(o1);
        orderDetails.add(o2);
        orderDto.setOrderDetailList(orderDetails);

        OrderDTO reuslt = orderService.create(orderDto);
        log.info("【创建订单】result={}", reuslt);
    }

    @Test
    public void findOne() {
        String orderId = "201901312355489322375271";
        OrderDTO orderDTO = orderService.findOne(orderId);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(OPEN_ID, pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        String orderId = "20190131235548932237527";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO cancelResult = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancelResult.getOrderStatus());
    }

    @Test
    public void finish() {
        String orderId = "20190131235548932237527";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        String orderId = "20190131235548932237527";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}