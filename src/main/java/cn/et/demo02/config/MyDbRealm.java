package cn.et.demo02.config;

import cn.et.demo02.mapper.UserInfoMapper;
import cn.et.demo02.model.UserInfoModel;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MyDbRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取当前用户的权限
     * 将当前用户在数据库的角色的权限 加载到AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName =principals.getPrimaryPrincipal().toString();
        Set<String> roleList =userInfoMapper.getRoleByUserName(userName);
//        Set<String> permsList =userMapper.queryPermsByName(userName);

        //角色和权限集合对象
        SimpleAuthorizationInfo sai =new SimpleAuthorizationInfo();
        sai.setRoles(roleList);
//        sai.setStringPermissions(permsList);
        return sai;
    }

    /**
     * 认证
     * 将登陆输入的用户名和密码跟数据库里面的用户名密码对比 是否相等
     * 返回值 null表示认证失败 非null认证通过
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //页面传入的token
        UsernamePasswordToken upt =(UsernamePasswordToken)token;
        UserInfoModel queryUser =userInfoMapper.getUserByUserName(token.getPrincipal().toString());

        if (queryUser !=null && queryUser.getPassword().equals(new String(upt.getPassword()))) {
            SimpleAccount sa =new SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
            return sa;
        }
        return null;
    }
}
