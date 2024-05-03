package com.fighting.stocklink.controller;


import cn.hutool.core.lang.Validator;
import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.config.web.exceptions.DataException;
import com.fighting.stocklink.config.web.exceptions.ParameterInvalidException;
import com.fighting.stocklink.constant.UserConstants;
import com.fighting.stocklink.model.User;
import com.fighting.stocklink.service.UserService;
import com.google.common.base.Preconditions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user   传入注册用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ActionResult<User> register(@RequestBody @Valid User user) {
        Preconditions.checkArgument(user != null, "用户信息不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空");
        Preconditions.checkArgument(user.getPassword().length() >= 8, "密码长度不能小于8");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPhoneNumber()), "手机号不能为空");
        Preconditions.checkArgument(!Validator.isMobile(user.getPhoneNumber()), "手机号不能为空");

        // 调用注册服务
        return userService.register(user);
    }

    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public ActionResult<User> login(@RequestBody User user, HttpServletRequest request) {
        Preconditions.checkArgument(user != null, "用户信息不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getUsername()), "用户名不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空");

        // 调用登录服务
        return userService.login(user, request.getSession());
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/is-login")
    public ActionResult<User> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return userService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param user    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PatchMapping("/update")
    public ActionResult<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        User sessionUser = (User) session.getAttribute(UserConstants.SESSION_NAME);
        if (sessionUser.getId() != user.getId().intValue()) {
            throw new DataException("当前登录用户和被修改用户不一致，终止！");
        }
        ActionResult<User> result = userService.update(user);
        // 修改成功则刷新session信息
        session.setAttribute(UserConstants.SESSION_NAME, result.getData());
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public ActionResult<Void> logout(HttpServletRequest request) {
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(UserConstants.SESSION_NAME, null);
        return ActionResult.success("用户退出登录成功！");
    }

}
