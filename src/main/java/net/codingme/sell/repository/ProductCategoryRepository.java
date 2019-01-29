package net.codingme.sell.repository;

import net.codingme.sell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品类目数据操作类
 *
 * @Author niujinpeng
 * @Date 2019/1/27 22:53
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 查询 categoryType 在 list 集合中的类目数据
     * 
     * @param list
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

}
