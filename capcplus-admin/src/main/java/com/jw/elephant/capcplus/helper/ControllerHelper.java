package com.jw.elephant.capcplus.helper;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.constant.Assert;
import org.apache.shiro.authc.AccountException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * controller层面响应数据封装(包含常见类型Content-Type: application/json;charset:utf-8;，image,text/html,image/gif)
 *
 * @author zhangjie
 */
public class ControllerHelper {

    /**
     * 封装json响应数据
     * @param response 当前响应参数
     * @throws IOException
     */
    public static void successJson(HttpServletResponse response,JSONObject data) throws IOException {
        // header
        setJsonHeader(response);
        PrintWriter result = response.getWriter();
        result.write(data.toString());
        result.flush();
        result.close();
    }
    /**
     * 封装json响应数据
     * @param response 当前响应参数
     * @throws IOException
     */
    public static void successJson(HttpServletResponse response) throws IOException {
        // header
        setJsonHeader(response);
        PrintWriter result = response.getWriter();
        result.write(success());
        result.flush();
        result.close();
    }
    public static void sendErrorMsgJson(HttpServletResponse response, String msg, Exception e) throws IOException {
        JSONObject result = new JSONObject();
        msg = msg == null ? "": msg;
        //输出错误参数的原因
        if( e != null && e instanceof Assert){
            msg = msg == null ? e.getMessage() : msg +"\t" + e.getMessage();
        }
        if (e != null &&e instanceof AccountException){
            msg = msg == null ? e.getMessage() : msg +"\t" + e.getMessage();
        }
        result.put("code", 0001);
        result.put("msg", msg);
        successJson(response, result);
    }

    /**
     *  响应图片数据给请求端
     *
     * @param response 当前响应参数
     * @param byteDate 图片字节数据
     * @author zhangjie
     * @date 2022/5/20
     */
    public static void successImage(HttpServletResponse response,byte[] byteDate) throws IOException {
        // header
        setImageHeader(response);
        ServletOutputStream result = response.getOutputStream();
        result.write(byteDate);
        result.flush();
        result.close();
    }
    /**
     *  响应content-type: application/json
     *
     * @param response 当前响应参数
     * @author zhangjie
     * @date 2022/5/20
     */
    private static void setJsonHeader(HttpServletResponse response){
        //服务端控制ajax页面缓存（不允许缓存）
        response.setHeader("Cache-Contro","no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("application/json;charset=UTF-8");
    }
    /**
     *  响应content-type: application/json
     *
     * @param response 当前响应参数
     * @author zhangjie
     * @date 2022/5/20
     */
    private static void setImageHeader(HttpServletResponse response){
        //服务端控制ajax页面缓存（不允许缓存）
        response.setHeader("Cache-Contro","no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");
    }
    /**
     * 无数据成功响应封装
     * @author zhangjie
     * @date 2022/5/20
     * @return java.lang.String
     */
    public static String success(){
        JSONObject result = new JSONObject();
        result.put("code",0000);
        result.put("msg","一切正常");
        return result.toString();
    }
    /**
     *  返回失败信息
     *
     * @param resultEnum 枚举常量
     * @author zhangjie
     * @date 2022/5/20
     * @return java.lang.String
     */
    public static String fail(ResultEnum resultEnum){
        JSONObject result = new JSONObject();
        result.put("code",ResultEnum.getMsg(resultEnum));
        result.put("msg",ResultEnum.getCode(resultEnum));
        return result.toString();
    }

    /**
     * json字符串
     *
     * @param response
     */
    public static void successJson2(HttpServletResponse response,JSONObject data) throws IOException {
        // header
        setJsonHeader(response);
        PrintWriter result = response.getWriter();
        data.put("success",true);
        result.write(data.toString());
        result.flush();
        result.close();
    }

    public static void sendErrorMsgJson2(HttpServletResponse response, String msg, Exception e) throws IOException {
        JSONObject result = new JSONObject();
        msg = msg == null ? "": msg;
        //输出错误参数的原因
        if( e != null && e instanceof Assert){
            msg = msg == null ? e.getMessage() : msg +"\t" + e.getMessage();
        }
        if (e != null &&e instanceof AccountException){
            msg = msg == null ? e.getMessage() : msg +"\t" + e.getMessage();
        }
        result.put("success", false);
        result.put("msg", msg);
        successJson(response, result);
    }

    public static void sendErrorMsgJson(HttpServletResponse response, String msg) throws IOException {
        JSONObject result = new JSONObject();
        msg = msg == null ? "": msg;
        result.put("success", false);
        result.put("msg", msg);
        successJson(response, result);
    }
    /**
     * 将数据封装成datatable需要的格式
     *
     * @param response
     * @param page
     * @author zhangjie
     * @date 2022/5/31
     * @return void
     */
    public static void putFailData(HttpServletResponse response,Exception e) throws IOException {
        JSONObject result = new JSONObject();
        result.put("data", new ArrayList<>());
        result.put("recordsTotal", 0);
        result.put("recordsFiltered", 0);
        result.put("error", "数据错误:"+e.getMessage());
        successJson(response,result);
        return;
    }
    /**
     * 将数据封装成datatable需要的格式
     *
     * @param response
     * @param page
     * @author zhangjie
     * @date 2022/5/31
     * @return void
     */
    public static <T> void putData(HttpServletResponse response,PageDT<T> page) throws IOException {
        JSONObject result = new JSONObject();
        result.put("draw",page.getDraw());
        result.put("data", page.getData());
        result.put("recordsTotal", page.getRecordsTotal());
        result.put("recordsFiltered", page.getRecordsFiltered());
        result.put("error", page.getError());
        successJson(response,result);
    }
}
