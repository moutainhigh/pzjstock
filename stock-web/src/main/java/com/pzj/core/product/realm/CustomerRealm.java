/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.product.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.framework.security.md5.MD5Hasher;

/**
 * 
 * @author dongchunfu
 * @version $Id: CustomerRealm.java, v 0.1 2016年8月7日 上午10:59:23 dongchunfu Exp $
 */

public class CustomerRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

    private String              username;
    private String              password;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);

        if (logger.isDebugEnabled()) {
            logger.debug("user Authorizat success,roles:{}.", roles);
        }

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String usernameInput = (String) token.getPrincipal();
        char[] credentials = (char[]) token.getCredentials();
        String passwordInput = new String(credentials);

        if (username.equals(usernameInput)) {
            if (password.equals(passwordInput)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(" user login success,username:{}.", username);
                }
                return new SimpleAuthenticationInfo(username, password, super.getName());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug(" user login fail ,username:{}.", username);
        }

        return null;
    }

    public static void main(String[] args) {
        String username = "piaozhijia";
        String passwordInput = "123456";
        MD5Hasher md5 = new MD5Hasher();
        String string = md5.doHash(username, passwordInput).toString();
        System.out.println(string);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}