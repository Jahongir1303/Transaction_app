package com.jahongir.mini_transaction.security;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author jahongir
 * @created 09/02/23 - 14:47
 * @project Mini_transaction/IntelliJ IDEA
 */
@Component
public class CurrentUser {
    public User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            if (principal != null) {
                user = principal.getUser();
            }
        }
        return user;
    }
}
