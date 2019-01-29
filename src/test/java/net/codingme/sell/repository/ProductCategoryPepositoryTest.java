package net.codingme.sell.repository;

import com.alibaba.fastjson.JSON;
import net.codingme.sell.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryPepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 根据 ID 获取测试
     */
    @Test
    public void findById() {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(1);
        ProductCategory productCategory = productCategoryOptional.get();
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    /**
     * 查找测试
     */
    @Test
    public void findOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        Example<ProductCategory> example = Example.of(productCategory);
        ProductCategory result = productCategoryRepository.findOne(example).get();
        Assert.assertNotNull(result);
    }

    /**
     * 新增数据
     * 
     * @Transactional 测试之后不保存到数据库
     */
    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(2);
        ProductCategory category = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(category);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List list = Arrays.asList(1, 2, 3);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

}