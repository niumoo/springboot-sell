package net.codingme.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.dto.CartDTO;

/**
 * <p>
 * 商品
 *
 * @Author niujinpeng
 * @Date 2019/1/299:56
 */
public interface ProductService {

    /**
     * 根据 ID 查询商品
     * 
     * @param productId
     * @return
     */
    public ProductInfo findById(String productId);

    /**
     * 分页查询所有商品
     * 
     * @param pageable
     * @return
     */
    public Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询所有上架商品
     * 
     * @return
     */
    public List<ProductInfo> findUpAll();

    /**
     * 新增商品
     * 
     * @param info
     * @return
     */
    public ProductInfo save(ProductInfo info);

    /**
     * 加库存
     * 
     * @param cartDtoList
     */
    public void increaseStock(List<CartDTO> cartDtoList);

    /**
     * 减库存
     * 
     * @param cartDtoList
     */
    public void decreaseStock(List<CartDTO> cartDtoList);

}
