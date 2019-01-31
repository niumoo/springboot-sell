package net.codingme.sell.repository;

import net.codingme.sell.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2");
        orderMaster.setBuyerName("曹操");
        orderMaster.setBuyerPhone("13913913919");
        orderMaster.setBuyerAddress("许昌");
        orderMaster.setBuyerOpenid("44444");
        orderMaster.setOrderAmount(new BigDecimal(15.0));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenidTest() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid("44444", pageRequest);
        Assert.assertNotEquals(0, orderMasterPage.getContent().size());
    }

}