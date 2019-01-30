package net.codingme.sell.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 买家商品
 *
 * @Author niujinpeng
 * @Date 2019/1/29 21:30
 */
@Data
public class ProductVo {

    @JSONField(name = "name")
    private String categoryName;

    @JSONField(name = "type")
    private Integer categoryType;

    @JSONField(name = "foods")
    private List<ProductInfoVo> productInfoVoList;

}
