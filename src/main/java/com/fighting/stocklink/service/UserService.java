package com.fighting.stocklink.service;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    ActionResult<User> register(User user);

    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return 登录结果
     */
    ActionResult<User> login(User user, HttpSession session);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改结果
     */
    ActionResult<User> update(User user) throws Exception;

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
     *
     * @param session 传入请求session
     * @return 返回结果，若用户已登录则返回用户信息
     */
    ActionResult<User> isLogin(HttpSession session);

}