package net.codingme.sell.controller;

import net.codingme.sell.domain.ProductCategory;
import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.service.ProductCategoryService;
import net.codingme.sell.service.ProductInfoService;
import net.codingme.sell.vo.ProductInfoVo;
import net.codingme.sell.vo.ProductVo;
import net.codingme.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品
 * 
 * @Author niujinpeng
 * @Date 2019/1/29 21:22
 */

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ResultVo list() {
        // 1. 查询商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2. 查询类目
        List<Integer> typeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(typeList);

        // 3. 拼装数据
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        ResultVo resultVo = ResultVo.success(productVoList);
        return resultVo;
    }

    @GetMapping(value = "/list-test")
    public ResultVo listTest() {
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productInfoVo.setProductId("1");
        productInfoVo.setProductName("南瓜粥");
        productInfoVo.setProductDescription("好喝的南瓜粥");
        productInfoVo.setProductIcon("https://xxx.jpg");
        productInfoVo.setProductPrice(new BigDecimal(3.0));
        ProductVo productVo = new ProductVo();
        productVo.setProductInfoVoList(Arrays.asList(productInfoVo));
        productVo.setCategoryName("热销商品");
        productVo.setCategoryType(1);
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(Arrays.asList(productVo));
        return resultVo;
    }
}
