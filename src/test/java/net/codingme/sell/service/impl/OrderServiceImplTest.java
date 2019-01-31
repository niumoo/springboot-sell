package net.codingme.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.domain.OrderDetail;
import net.codingme.sell.dto.CartDto;
import net.codingme.sell.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        OrderDto orderDto = new OrderDto();
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

        OrderDto reuslt = orderService.insert(orderDto);
        log.info("【创建订单】result={}", reuslt);
    }

    @Test
    public void findOne() {}

    @Test
    public void findList() {}

    @Test
    public void cancel() {}

    @Test
    public void finish() {}

    @Test
    public void paid() {}
}