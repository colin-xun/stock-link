package com.fighting.stocklink.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.config.web.exceptions.CustomerException;
import com.fighting.stocklink.constant.UserConstants;
import com.fighting.stocklink.dao.UserMapper;
import com.fighting.stocklink.model.User;
import com.fighting.stocklink.service.UserService;
import com.fighting.stocklink.util.ClassExamine;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ActionResult<User> register(User user) {
        // 先去数据库找用户名是否存在
        User getUser = userMapper.getByUsername(user.getUsername());
        if (getUser != null) {
            throw new CustomerException("该用户名已存在！");
        }
        // 加密储存用户的密码（使用Hutool实用工具中的DigestUtil工具类）
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        // 存入数据库
        userMapper.insert(user);
        // 返回成功消息
        return ActionResult.success("注册用户成功！");
    }

    @Override
    public ActionResult<User> login(User user, HttpSession session) {
        // 去数据库查找用户
        User getUser = userMapper.getByUsername(user.getUsername());
        if (getUser == null) {
            throw new CustomerException("该用户名已存在！");
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtil.md5Hex(user.getPassword()))) {
            throw new CustomerException("用户名或者密码错误！");
        }
        // 设定登录成功消息
        // 如果登录成功，则设定session
        session.setAttribute(UserConstants.SESSION_NAME, getUser);
        return ActionResult.success("登录成功！");
    }

    @Override
    public ActionResult<User> update(User user) throws Exception {
        // 去数据库查找用户
        User getUser = userMapper.selectByPrimaryKey(user.getId());
        if (getUser == null) {
            throw new CustomerException("该用户名已存在！");
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StrUtil.isEmpty(user.getPassword())) {
            // 加密储存
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        }
        // 对象互补
        ClassExamine.objectOverlap(user, getUser);
        // 存入数据库
        userMapper.updateByPrimaryKey(user);
        return ActionResult.success("修改用户成功！");
    }

    @Override
    public ActionResult<User> isLogin(HttpSession session) {
        // 从session中取出用户信息
        User sessionUser = (User) session.getAttribute(UserConstants.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionUser == null) {
            throw new CustomerException("用户未登录！");
        }
        // 登录了则去数据库取出信息进行比对
        User getUser = userMapper.selectByPrimaryKey(sessionUser.getId());
        // 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            throw new CustomerException("用户信息无效！");
        }
        return ActionResult.success("用户已登录！");
    }

}