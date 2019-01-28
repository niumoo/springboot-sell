package net.codingme.sell.service;

import net.codingme.sell.domain.ProductCategory;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/28 11:16
 */
public interface ProductCategoryService {

    public ProductCategory findById(Integer id) throws Exception;
}
