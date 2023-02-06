package com.jahongir.mini_transaction.security;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author jahongir
 * @created 02/02/23 - 12:52
 * @project Mini_transaction/IntelliJ IDEA
 */
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    //    private UUID id;
//    private String phoneNumber;
//    private String password;
//    private UserStatus status;
    private final User user;

//    public static UserDetailsImpl build(User user) {
//        return new UserDetailsImpl(user.getId(), user.getPhoneNumber(), user.getPassword(), user.getStatus());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(UserStatus.ACTIVE);
    }
}
