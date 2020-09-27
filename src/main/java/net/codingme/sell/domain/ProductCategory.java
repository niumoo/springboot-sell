package net.codingme.sell.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

/**
 * <p>
 * 商品类目表
 *
 * @DynamicUpdate 动态更新，如时间
 * @Author niujinpeng
 * @Date 2019/1/27 22:44
 */
@DynamicUpdate
@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    /** 类目创建时间 */
    private Date createTime;

    /** 类目更新时间 */
    private Date updateTime;

}
