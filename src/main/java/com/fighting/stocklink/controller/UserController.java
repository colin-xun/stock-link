package com.fighting.stocklink.controller;


import com.fighting.stocklink.constant.UserConstants;
import com.fighting.stocklink.model.Result;
import com.fighting.stocklink.model.UserVO;
import com.fighting.stocklink.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userVO   传入注册用户信息
     * @param errors Validation的校验错误存放对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody @Valid UserVO userVO, BindingResult errors) {
        Result<UserVO> result;
        // 如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用注册服务
        result = userService.register(userVO);
        return result;
    }

    /**
     * 用户登录
     *
     * @param userVO    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody @Valid UserVO userVO, BindingResult errors, HttpServletRequest request) {
        Result<UserVO> result;
        // 如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用登录服务
        result = userService.login(userVO);
        // 如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(UserConstants.SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/is-login")
    public Result<UserVO> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return userService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param userVO    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PatchMapping("/update")
    public Result<UserVO> update(@RequestBody UserVO userVO, HttpServletRequest request) throws Exception {
        Result<UserVO> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        UserVO sessionUserVO = (UserVO) session.getAttribute(UserConstants.SESSION_NAME);
        if (sessionUserVO.getId() != userVO.getId().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.update(userVO);
        // 修改成功则刷新session信息
        if (result.isSuccess()) {
            session.setAttribute(UserConstants.SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(UserConstants.SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！");
        return result;
    }

}
