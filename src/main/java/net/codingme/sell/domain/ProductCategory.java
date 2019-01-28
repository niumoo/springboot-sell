package net.codingme.sell.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>
 * 商品类目表
 *
 * @Author niujinpeng
 * @Date 2019/1/27 22:44
 */
@Entity
@Data
public class ProductCategory {

    /** 类目id */
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
