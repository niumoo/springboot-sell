package net.codingme.sell.repository;

import com.alibaba.fastjson.JSON;
import net.codingme.sell.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryPepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findById() {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(1);
        ProductCategory productCategory = productCategoryOptional.get();
        System.out.println(productCategory);
    }

    @Test
    public void findOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        Example<ProductCategory> example = Example.of(productCategory);
        ProductCategory productCategory1 = productCategoryRepository.findOne(example).get();
        System.out.println(productCategory1);
    }

}