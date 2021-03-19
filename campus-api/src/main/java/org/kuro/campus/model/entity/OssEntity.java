package org.kuro.campus.model.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 需要将这个类交给spring管理
 */
@ConfigurationProperties(prefix = "alioss")
@Component
@Data
public class OssEntity {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;
}
