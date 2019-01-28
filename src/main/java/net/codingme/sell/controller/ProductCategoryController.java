package net.codingme.sell.controller;

import net.codingme.sell.domain.ProductCategory;
import net.codingme.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品类目
 *
 * @Author niujinpeng
 * @Date 2019/1/27 23:12
 */
@Controller
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @ResponseBody
    @GetMapping("/{id}")
    public ProductCategory findById(@PathVariable(name = "id") Integer id) throws Exception {
        return productCategoryService.findById(id);
    }
}
