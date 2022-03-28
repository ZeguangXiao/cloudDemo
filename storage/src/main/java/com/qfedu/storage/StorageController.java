package com.qfedu.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author 百泽
 * @公众号 Java架构栈
 */
@RestController
public class StorageController {

    private static final Logger logger = getLogger(StorageController.class);

    @Value("${server.port}")
    Integer port;

    /**
     * 商品扣库存
     * @return
     */
    @GetMapping("/deduct")
    public String deduct() {
        logger.info("deduct");
        return "hello deduct:" + port;
    }
}
