package com.wujia.Shiro;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wujia.entity.Users;
import com.wujia.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*自定义Realm类*/

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;


    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("这是Realm的授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
//        AdminUser adminUser = (AdminUser) subject.getPrincipal();
        //查询用户权限
//        List<Grade> gradeList = gradeService.selectAdminUserGarde(adminUser);
        //添加资源授权字符串
//        info.addStringPermission(String.valueOf(gradeList.get(0).getId()));
        return info;
    }

    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("这是Realm的认证逻辑");
        //获取传递过来的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getUsername() + "\n" + token.getPassword());
        //给传递的用户进行存值
        Users users =new Users();
        users.setuName(token.getUsername());

        //查询数据库用户和密码
        List<Users> usersList = usersService.selectList(new EntityWrapper<Users>()
            .eq("u_name",users.getuName())
        );

        if (usersList.size() != 0) {
            /**管理员
             * 2.判断密码（直接new认证逻辑（AuthenticationInfo）的子类）
             */
            return new SimpleAuthenticationInfo(usersList, usersList.get(0).getuPsw(), "");
            //第一个参数，返回的数据.
            //第二个参数，从数据库获取的用户密码（Shiro自动进行比较）
            // 第三个参数，Shiro名字
        } else {
            System.out.println("用户不存在");
            return null;//返回null时Shiro底层会抛出UnknownAccountException异常
        }
    }
}
