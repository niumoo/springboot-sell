package net.codingme.sell.service;

import net.codingme.sell.domain.ProductCategory;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/28 11:16
 */
public interface ProductCategoryService {

    /**
     * 根据 ID 查询商品类目
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ProductCategory findById(Integer id) throws Exception;
}
