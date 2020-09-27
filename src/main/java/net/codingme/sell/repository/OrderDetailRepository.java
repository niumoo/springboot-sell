package net.codingme.sell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codingme.sell.domain.OrderDetail;

/**
 * <p>
 * 订单详情
 *
 * @Author niujinpeng
 * @Date 2019/1/31 16:02
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单ID查询订单详细信息
     * 
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
