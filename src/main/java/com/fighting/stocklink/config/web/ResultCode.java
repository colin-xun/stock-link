package com.fighting.stocklink.config.web;

import org.springframework.stereotype.Component;

/**
 * @author： colin_xun@163.com
 * @create: 2018-12-16 19:17
 * @description:
 */
@Component
public class ResultCode {
    // 成功
    public static final String SUCCESS = "200";

    // 失败
    public static final String FAIL = "400";

    // 系统异常
    public static final String SYSTEM_ERROR = "500";

    // 未认证（签名错误）
    public static final String UNAUTHORIZED = "401";

    // 接口不存在
    public static final String NOT_FOUND = "404";

    // 服务器内部错误
    public static final String INTERNAL_SERVER_ERROR = "500";

    // 参数不全
    public static final String MISSIONG_PARAM = "90003";

    // 登陆已过期
    public static final String SESSION_EXPIRE = "20011";

    // 有绑定关系，无法删除
    public static final String BIND_EXIST = "20012";

    // 数据库问题
    public static final String DATABASET_ERROR = "20013";

    // 数据冲突
    public static final String DATA_CONFILCT = "20014";

    // 数据不存在
    public static final String DATA_NOT_FOUNT = "20015";
}