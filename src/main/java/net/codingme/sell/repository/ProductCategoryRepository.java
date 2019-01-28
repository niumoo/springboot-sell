package net.codingme.sell.repository;


import net.codingme.sell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品类目数据操作类
 *
 * @Author niujinpeng
 * @Date 2019/1/27 22:53
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
