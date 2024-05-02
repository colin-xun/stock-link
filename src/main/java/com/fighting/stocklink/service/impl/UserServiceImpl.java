package com.fighting.stocklink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.fighting.stocklink.constant.UserConstants;
import com.fighting.stocklink.dao.UserPOMapper;
import com.fighting.stocklink.model.Result;
import com.fighting.stocklink.model.UserVO;
import com.fighting.stocklink.po.UserPO;
import com.fighting.stocklink.service.UserService;
import com.fighting.stocklink.util.ClassExamine;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPOMapper userMapper;

    @Override
    public Result<UserVO> register(UserVO userVO) {
        Result<UserVO> result = new Result<>();

        // 先去数据库找用户名是否存在
        UserPO userPO = userMapper.getByUsername(userVO.getUsername());
        if (userPO != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码（使用Hutool实用工具中的DigestUtil工具类）
        // todo wtx 这里暂时不加密
//        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        userVO.setIsActive(true);
        userVO.setIsAdmin(false);
        userVO.setRegistrationDate(new Date());
        // 存入数据库
        UserPO user = BeanUtil.toBean(userVO, UserPO.class);
        userMapper.insert(user);
        // 返回成功消息
        result.setResultSuccess("注册用户成功！", userVO);
        return result;
    }

    @Override
    public Result<UserVO> login(UserVO userVO) {
        Result<UserVO> result = new Result<>();
        // 去数据库查找用户
        UserPO userPO = userMapper.getByUsername(userVO.getUsername());
        if (userPO == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!userPO.getPassword().equals(DigestUtil.md5Hex(userVO.getPassword()))) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }

        userPO.setLastLogin(new Date());
        userMapper.updateByPrimaryKey(userPO);

        // 设定登录成功消息
        result.setResultSuccess("登录成功！", userVO);
        return result;
    }

    @Override
    public Result<UserVO> update(UserVO userVO) throws Exception {
        Result<UserVO> result = new Result<>();
        // 去数据库查找用户
        UserPO userPO = userMapper.selectByPrimaryKey(userVO.getId());
        if (userPO == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StrUtil.isEmpty(userVO.getPassword())) {
            // 加密储存
            userVO.setPassword(DigestUtil.md5Hex(userVO.getPassword()));
        }
        // 对象互补
        ClassExamine.objectOverlap(userVO, userPO);
        // 存入数据库
        userMapper.updateByPrimaryKey(userPO);
        result.setResultSuccess("修改用户成功！", BeanUtil.toBean(userPO, UserVO.class));
        return result;
    }

    @Override
    public Result<UserVO> isLogin(HttpSession session) {
        Result<UserVO> result = new Result<>();
        // 从session中取出用户信息
        UserVO sessionUserVO = (UserVO) session.getAttribute(UserConstants.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionUserVO == null) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        // 登录了则去数据库取出信息进行比对
        UserPO userPO = userMapper.selectByPrimaryKey(sessionUserVO.getId());
        // 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
        if (userPO == null || !userPO.getPassword().equals(sessionUserVO.getPassword())) {
            result.setResultFailed("用户信息无效！");
            return result;
        }
        result.setResultSuccess("用户已登录！", BeanUtil.toBean(userPO, UserVO.class));
        return result;
    }

}