package com.fighting.stocklink.service;

import com.fighting.stocklink.model.Result;
import com.fighting.stocklink.model.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 用户注册
     *
     * @param userVO 用户对象
     * @return 注册结果
     */
    Result<UserVO> register(UserVO userVO);

    /**
     * 用户登录
     *
     * @param userVO 用户对象
     * @return 登录结果
     */
    Result<UserVO> login(UserVO userVO);

    /**
     * 修改用户信息
     *
     * @param userVO 用户对象
     * @return 修改结果
     */
    Result<UserVO> update(UserVO userVO) throws Exception;

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
     *
     * @param session 传入请求session
     * @return 返回结果，若用户已登录则返回用户信息
     */
    Result<UserVO> isLogin(HttpSession session);

}