package com.jw.elephant.capcplus.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.mapper.*;
import com.jw.elephant.capcplus.pojo.*;
import com.jw.elephant.capcplus.pojo.enums.UserSuperEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 大象realm
 *
 * @author zhangjie
 */
public class CapcPlusRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BindRoleMapper bindRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;

    /**
     *  登录授权
     *
     * @param principals
     * @author zhangjie
     * @date 2022/5/25
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //用户授权
        User user = (User) principals.getPrimaryPrincipal();
        if (Objects.isNull(user)){
            throw new AccountException("当前用户不存在！");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //是否是超级用户（是超级用户拥有所有权限）
        if (UserSuperEnum.YES.equals(user.getIsSuper())){
            //所有权限
            authorizationInfo.addStringPermission("*");
            return authorizationInfo;
        }
        //查询当前用户的所有角色
        List<BindRole> bindRoles = bindRoleMapper.selectList(new QueryWrapper<BindRole>()
                                                .eq("user_id", user.getId())
                                                .eq("is_del",0));
        //查询所有角色
        Set<Long> roleIds = bindRoles.stream().map(BindRole::getRoleId).collect(Collectors.toSet());
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        Set<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toSet());
        authorizationInfo.setRoles(roleNames);
        //查询所有角色拥有的权限
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new QueryWrapper<RolePermission>()
                                                                   .in("role_id", roleIds));
        Set<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);
        authorizationInfo.setStringPermissions(permissions.stream().map(Permission::getPermission).collect(Collectors.toSet()));
        return authorizationInfo;
    }

    /**
     *  登录认证
     *
     * @param token 登录数据
     * @author zhangjie
     * @date 2022/5/25
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
        //获取当前用户名
        String account = loginToken.getUsername();
        User userDO = new User();
        userDO.setAccount(account);
        User user = userMapper.selectOne(new QueryWrapper<>(userDO));
        if (Objects.isNull(user)){
            throw new AccountException("当前用户不存在！");
        }
        ByteSource bytes = ByteSource.Util.bytes(TextConstant.MD5_SALT);
        return new SimpleAuthenticationInfo(user,user.getPassword(), bytes,"capcPlusRealm");
    }
}
