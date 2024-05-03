package com.fighting.stocklink.context;

import com.fighting.stocklink.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getLoginUser() {
        return userThreadLocal.get();
    }

    public static void setLoginUser(User user) {
        userThreadLocal.set(user);
    }

    public static String getLoginUserName() {
        User user = getLoginUser();
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

}
