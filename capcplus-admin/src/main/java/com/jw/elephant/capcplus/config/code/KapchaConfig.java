package com.jw.elephant.capcplus.config.code;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KapchaConfig {
    /**
     * 验证码边框是否开启
     */
    @Value("${kaptcha.border}")
    private String border;

    /**
     * 验证码边框颜色
     */
    @Value("${kaptcha.border.color}")
    private String borderColor;

    /**
     * 验证码内容字体颜色
     */
    @Value("${kaptcha.textproducer.font.color}")
    private String textproducerFontColor;

    /**
     * 验证码内容字体大小
     */
    @Value("${kaptcha.textproducer.font.size}")
    private String textproducerFontSize;

    /**
     * 验证码内容字体名称
     */
    @Value("${kaptcha.textproducer.font.names}")
    private String textproducerFontName;

    /**
     * 验证码内容长度
     */
    @Value("${kaptcha.textproducer.char.length}")
    private String textproducerCharLength;

    /**
     * 验证码图片宽
     */
    @Value("${kaptcha.image.width}")
    private String imageWidth;

    /**
     * 验证码图片高
     */
    @Value("${kaptcha.image.height}")
    private String imageHeight;

    /**
     * 验证码sessionKey
     */
    @Value("${kaptcha.session.key}")
    private String sessionKey;

    /**
     *  默认google验证码bean
     *
     * @author zhangjie
     * @date 2022/5/20
     * @return com.google.code.kaptcha.impl.DefaultKaptcha
     */
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha =new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border",border);
        properties.setProperty("kaptcha.border.color",borderColor);
        properties.setProperty("kaptcha.textproducer.font.color",textproducerFontColor);
        properties.setProperty("kaptcha.textproducer.font.size",textproducerFontSize);
        properties.setProperty("kaptcha.textproducer.font.names",textproducerFontName);
        properties.setProperty("kaptcha.textproducer.char.length",textproducerCharLength);
        properties.setProperty("kaptcha.image.width",imageWidth);
        properties.setProperty("kaptcha.image.height",imageHeight);
        properties.setProperty("kaptcha.session.key",sessionKey);
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
