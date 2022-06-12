package com.jw.elephant.capcplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.dto.UserLoginDTO;
import com.jw.elephant.capcplus.dto.UserRegisterDTO;
import com.jw.elephant.capcplus.mapper.UserMapper;
import com.jw.elephant.capcplus.pojo.User;
import com.jw.elephant.capcplus.pojo.enums.UserStatusEnum;
import com.jw.elephant.capcplus.pojo.enums.UserSuperEnum;
import com.jw.elephant.capcplus.service.MessageMailService;
import com.jw.elephant.capcplus.service.UserService;
import com.jw.elephant.capcplus.util.CommonUtil;
import com.jw.elephant.capcplus.util.IpUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * 用户相关业务
 *
 * @author zhangjie
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MessageMailService messageMailService;
    /**
     * 用户认证+授权
     *
     * @param request
     * @param response
     * @param userLoginDTO
     * @author zhangjie
     * @date 2022/5/26
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void login(HttpServletRequest request, HttpServletResponse response, UserLoginDTO userLoginDTO) {
        //用户参数校验
        UserLoginDTO.asserts(userLoginDTO);
        //校验验证码
        String sessionCode = (String) request.getSession().getAttribute(TextConstant.CODE_KEY);
        Assert.notTrue(userLoginDTO.getCode().equals(sessionCode),"验证码错误!");
        //设置shiro对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginDTO.getAccount(), userLoginDTO.getPassword());
        subject.login(token);
        User user = (User)subject.getPrincipal();
        //设置用于到当前session
        subject.getSession().setAttribute("user",user);
        Assert.isTrue(UserStatusEnum.DISABLED.equals(user.getStatus()),"该用户被禁用！");
        //获取当前用户ip地址
        String ipAddress = IpUtil.getIpAddress(request);
        User query = new User();
        query.setAccount(userLoginDTO.getAccount());
        User currentUser = userMapper.selectOne(new QueryWrapper<>(query));
        currentUser.setIp(ipAddress);
        currentUser.setLastLoginTime(new Date());
        //更新ip和登录时间
        userMapper.updateById(currentUser);
    }

    /**
     * 用户注册（相关数据校验）
     *
     * @param request 当前请求
     * @param response 当前响应
     * @param userRegisterDTO 注册DTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, UserRegisterDTO userRegisterDTO) {
        //数据校验
        UserRegisterDTO.asserts(userRegisterDTO);
        User account = getOne(new LambdaQueryWrapper<User>().eq(User::getAccount,userRegisterDTO.getAccount()));
        Assert.nonNull(account,"该账号已注册！");
        //DO入库数据
        User userDO =new User();
        BeanUtils.copyProperties(userRegisterDTO,userDO);
        if (TextConstant.ROOT.equals(userDO.getAccount())){
            userDO.setIsSuper(UserSuperEnum.YES);
        }
        userDO.setIp(IpUtil.getIpAddress(request));
        userDO.setCreateTime(new Date());
        userDO.setPassword(new SimpleHash("MD5", ByteSource.Util.bytes(userDO.getPassword())
                ,TextConstant.MD5_SALT,5).toString());
        userMapper.insert(userDO);
    }
    /**
     *  账号是否存在
     *
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @author zhangjie
     * @date 2022/5/26
     */
    @Override
    public boolean existAccount(HttpServletRequest request, HttpServletResponse response, String account) {
        Assert.isBlank(account,"请输入找回账号！");
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getAccount,account));
        return Objects.nonNull(user);
    }

    /**
     * 发送邮件
     * @param request 请求
     * @param response 响应
     * @param account 当前账号
     * @param email 当前邮箱
     * @author zhangjie
     * @date 2022/5/26
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long sendEmail(HttpServletRequest request, HttpServletResponse response, String account, String email) {
        Assert.isBlank(account,"账号为空，请返回上一步重新填写！");
        Assert.isBlank(email,"邮箱为空！");
        Assert.notTrue(CommonUtil.RegexValidatorUtil.isEmail(email),"邮箱格式不正确！");
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getAccount,account));
        //对比是否与当前账号绑定相同邮箱
        if (!user.getEmail().equals(email)) {
            Assert.isTrue(true,"当前账号与当前邮箱未绑定！");
        }
        int code = RandomUtils.nextInt(10000, 999999);
        String codeStr = StringUtils.rightPad(String.valueOf(code), 6, "6");
        //存储当前key
        request.getSession().setAttribute(TextConstant.EMAIL_CODE,codeStr);
        //暂时使用直接创建线程去发邮件
        new Thread(()->{
            messageMailService.simpleMail(email,TextConstant.EAMIL_SUBJECT, codeStr);
        }).start();
        return user.getId();
    }
    /**
     *  修改密码
     *
     * @param request 请求
     * @param response 响应
     * @param id 当前用户id
     * @param password 新密码
     * @param againPassword 确认密码
     * @author zhangjie
     */
    @Override
    public boolean changePassWord(HttpServletRequest request, HttpServletResponse response, Long id, String password, String againPassword) {
        Assert.isBlank(password,"请输入密码");
        Assert.isBlank(password,"未重复输入密码");
        User user = userMapper.selectById(id);
        Assert.isNull(user,"用户不存在");
        Assert.notTrue(password.equals(againPassword),"两次密码不一致");
        SimpleHash hashMD5  = new SimpleHash("MD5",password,TextConstant.MD5_SALT,5);
        user.setPassword(hashMD5.toString());
        userMapper.updateById(user);
        return true;
    }
}
