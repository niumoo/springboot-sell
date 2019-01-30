package net.codingme.sell.repository;

import net.codingme.sell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品
 *
 * @Author niujinpeng
 * @Date 2019/1/2823:28
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询指定状态的商品
     * 
     * @param productStatus
     *            商品状态
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
