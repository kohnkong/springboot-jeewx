package com.jeecg.p3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/3/24 10:31
 * @Description:
 */
@Component
@PropertySource("classpath:commonweixin.properties")
public class PropertiesConfig {

    private static String imageUrl;

    private static String fastdfsApiUrl;

    private static String certOAuthDomain;

    private static String certAppId;

    @Value("${certAppId}")
    public static void setCertAppId(String certAppId) {
        PropertiesConfig.certAppId = certAppId;
    }

    @Value("${certOAuthDomain}")
    public void setCertOAuthDomain(String certOAuthDomain) {
        PropertiesConfig.certOAuthDomain = certOAuthDomain;
    }

    @Value("${imageUrl}")
    public void setImageUrl(String imageUrl) {
        PropertiesConfig.imageUrl = imageUrl;
    }

    @Value("${fastdfsApiUrl}")
    public void setFastdfsApiUrl(String fastdfsApiUrl) {
        PropertiesConfig.fastdfsApiUrl = fastdfsApiUrl;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static String getFastdfsApiUrl() {
        return fastdfsApiUrl;
    }

    public static String getCertOAuthDomain() {
        return certOAuthDomain;
    }

    public static String getCertAppId() {
        return certAppId;
    }

}
