package net.codingme.sell.repository;

import net.codingme.sell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 买家订单
 *
 * @Author niujinpeng
 * @Date 2019/1/3018:15
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 按照openid分页查询买家订单
     * 
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
