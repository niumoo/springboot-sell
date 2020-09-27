package net.codingme.sell.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

/**
 * <p>
 * 商品
 *
 * @Author niujinpeng
 * @Date 2019/1/28 23:23
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    /** 名称 */
    private String productName;

    /** 单价 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 小图 */
    private String productIcon;

    /** 状态,0正常 1下架 */
    private Integer productStatus;

    /** 类目 */
    private Integer categoryType;

}
