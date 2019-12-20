package com.hecm.vuelog.common.util;

import com.hecm.vuelog.sys.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 */
public class Util {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
