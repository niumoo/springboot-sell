package net.codingme.sell.service.impl;

import net.codingme.sell.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl serviceImplTest;

    @Test
    public void findById() {
        ProductInfo productInfo = serviceImplTest.findById("351fd60c66274216aac840335f2c89b0");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ProductInfo> productInfoPage = serviceImplTest.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getContent().size());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = serviceImplTest.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString().replace("-", ""));
        productInfo.setProductName("油条");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("中国经典美食");
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("https://www.xxx.com/a2cxm.jpg");
        productInfo.setCategoryType(1);
        ProductInfo result = serviceImplTest.save(productInfo);
        Assert.assertNotNull(result);
    }
}