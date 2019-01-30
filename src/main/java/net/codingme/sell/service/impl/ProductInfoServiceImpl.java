package net.codingme.sell.service.impl;

import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.enums.ProductStatusEnum;
import net.codingme.sell.repository.ProductInfoRepository;
import net.codingme.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品
 *
 * @Author niujinpeng(niumoo.github.io)
 * @Date 2019/1/29 9:58
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findById(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).get();
        return productInfo;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> infoPage = productInfoRepository.findAll(pageable);
        return infoPage;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

}
