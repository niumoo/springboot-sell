package net.codingme.sell.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单详细
 *
 * @Author niujinpeng
 * @Date 2019/1/30 17:50
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id */
    private String orderId;

    /** 商品id */
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品数量 */
    private Integer productQuantity;

    /** 商品小图 */
    private String productIcon;

    private Date createTime;

    private Date updateTime;
}
