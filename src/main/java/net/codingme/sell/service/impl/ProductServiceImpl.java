package net.codingme.sell.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.codingme.sell.domain.ProductInfo;
import net.codingme.sell.dto.CartDTO;
import net.codingme.sell.enums.ProductStatusEnum;
import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.exception.SellException;
import net.codingme.sell.repository.ProductInfoRepository;
import net.codingme.sell.service.ProductService;

/**
 * <p>
 * 商品
 *
 * @Author niujinpeng(niumoo.github.io)
 * @Date 2019/1/29 9:58
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findById(String productId) {
        Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(productId);
        return productInfoOptional.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    /**
     * 加库存
     * 
     * @param cartDtoList
     */
    @Override
    public void increaseStock(List<CartDTO> cartDtoList) {
        for (CartDTO cartDTO : cartDtoList) {
            ProductInfo productInfo = findById(cartDTO.getProductId());
            Integer stock = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(stock);
            ProductInfo updateReult = productInfoRepository.save(productInfo);
            if (updateReult == null) {
                log.info("【增加库存】增加库存失败，cartDTO={}", cartDTO);
                throw new SellException(ResultEnum.PRODUCT_STOCK_UPDATE_FIELD);
            }
        }
    }

    /**
     * 扣库存
     * 
     * @param cartDtoList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDtoList) {
        for (CartDTO cartDto : cartDtoList) {
            ProductInfo productInfo = findById(cartDto.getProductId());
            Integer stock = productInfo.getProductStock() - cartDto.getProductQuantity();
            // 库存不正确
            if (stock < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stock);
            productInfoRepository.save(productInfo);
        }
    }

}
