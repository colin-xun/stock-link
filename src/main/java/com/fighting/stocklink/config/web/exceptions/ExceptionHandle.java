package com.fighting.stocklink.config.web.exceptions;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.config.web.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;

/**
 * @author： colin_xun@163.com
 * @create: 2019-06-30 16:47
 * @description:
 */
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ActionResult handle(Exception e) {
        logger.error(e.getMessage(), e);
        if (e instanceof BusinessException) {
            return new ActionResult(ResultCode.SYSTEM_ERROR, "接口异常",null);
        } else if (e instanceof InternalServerException) {
            return new ActionResult(ResultCode.SYSTEM_ERROR, "服务异常",null);
        } else if (e instanceof ParameterInvalidException) {
            // 这个异常抛出给前端
            return new ActionResult(ResultCode.MISSIONG_PARAM, e.getMessage(),null);
        } else if (e instanceof IllegalArgumentException) {
            // 这个异常抛出给前端
            return new ActionResult(ResultCode.MISSIONG_PARAM, e.getMessage(),null);
        } else if (e instanceof DataException) {
            // 这个异常抛出给前端
            return new ActionResult(ResultCode.DATA_NOT_FOUNT, e.getMessage(), null);
        } else if (e instanceof CustomerException) {
            // 这个异常抛出给前端
            return new ActionResult(ResultCode.FAIL, e.getMessage(), null);
        } else {
            return new ActionResult(ResultCode.SYSTEM_ERROR, "未知异常",null);
        }
    }

}
