package net.codingme.sell.service;

import java.util.List;

import net.codingme.sell.domain.ProductCategory;

/**
 * <p>
 * 类目
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

    /**
     * 查询所有类目
     * 
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 查询 categoryType 在 list 集合中的类目数据
     * 
     * @param typeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);

    /**
     * 新增类目
     * 
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
