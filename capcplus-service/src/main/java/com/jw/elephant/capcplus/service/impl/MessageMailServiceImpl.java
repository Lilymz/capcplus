package com.jw.elephant.capcplus.service.impl;

import com.jw.elephant.capcplus.service.MessageMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.MessageFormat;

/**
 * 邮件服务
 * @author zhangjie
 */
@Service
@Slf4j
public class MessageMailServiceImpl implements MessageMailService {
    @Resource
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String userName;
    private MimeMessage message;
    @PostConstruct
    public void init(){
        //创建一次
        message = sender.createMimeMessage();
    }
    @Override
    public void simpleMail(String toUser, String subject, String code) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName);
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(code);
            sender.send(message);
            log.info("向====>{}发送邮件成功~",toUser);
        } catch (Exception e) {
           log.error("发送邮件错误：",e.getMessage());
           log.error("发送邮件错误堆栈：",e);
        }
    }
    /**
     *  发送余额预警邮件
     *
     * @param toUser 用户邮箱
     * @param subject 主题
     * @param user 对应用户
     * @author zhangjie
     * @date 2022/5/30
     */
    @Override
    public void tipBalanceMail(HttpServletRequest request,String toUser, String subject, String user) {
        try {
            //邮箱发送内容组成
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setText(loadBalanceTemplate(user,request), true);
            helper.setTo(toUser);
            helper.setFrom(userName);
            sender.send(message);
        }catch (Exception e){
            log.error("发送余额预警邮件错误：",e.getMessage());
            log.error("发送余额预警邮件错误堆栈：",e);
        }
    }
    /**
     *  加载余额预警freemarker邮件模板
     *
     * @param user 用户名称
     * @author zhangjie
     * @date 2022/5/30
     * @return java.lang.String
     */
    private String loadBalanceTemplate(String user,HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/images/");
        String fileName =path+"balance-warning.ftl";
        File file = new File(fileName);
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = new FileInputStream(file);
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.info("发送邮件读取模板失败{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), user);
    }
}
