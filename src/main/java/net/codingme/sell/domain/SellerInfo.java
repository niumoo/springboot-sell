package net.codingme.sell.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>
 * 卖家信息表
 *
 * @Author niujinpeng
 * @Date 2019/1/30 17:59
 */
@Data
@Entity
public class SellerInfo {

    @Id
    public String id;

    public String username;

    private String password;

    /** 微信openid */
    private String opendId;

    private Date createTime;

    private Date updateTime;
}
