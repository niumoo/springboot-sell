package net.codingme.sell.service.impl;

import net.codingme.sell.domain.ProductCategory;
import net.codingme.sell.repository.ProductCategoryRepository;
import net.codingme.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/28 11:17
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findById(Integer id) throws Exception {
        ProductCategory productCategory = this.productCategoryRepository.findById(id).get();
        return productCategory;
    }
}
