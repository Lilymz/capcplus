package com.jw.elephant.capcplus.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.service.KaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 验证码service
 *
 * @author zhangjie
 */
@Service
public class KaptchaServiceImpl implements KaptchaService {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @Override
    public ByteArrayOutputStream getCode(HttpServletRequest request) throws IOException {
        int random = ThreadLocalRandom.current().nextInt(2);
        // 创建验证码内容
        String text;
        switch (random){
            //成语验证码
            case 1:
                //获取内容集合
                List<String> phrase = TextConstant.PHRASE;
                int randomList = ThreadLocalRandom.current().nextInt(phrase.size());
                text =  phrase.get(randomList);
                if (StringUtils.isBlank(text)){
                    text = defaultKaptcha.createText();
                }
                break;
            //字符验证码
            default:
                text = defaultKaptcha.createText();
                break;
        }
        //获取当前session
        HttpSession session = request.getSession();
        session.setAttribute(TextConstant.CODE_KEY,text);
        //创建验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outImage = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",outImage);
        return outImage;
    }
}
