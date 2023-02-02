package com.jahongir.mini_transaction.service;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.mappers.UserMapper;
import com.jahongir.mini_transaction.repository.UserRepository;
import com.jahongir.mini_transaction.security.UserDetailsImpl;
import com.jahongir.mini_transaction.service.base.AbstractService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jahongir
 * @created 02/02/23 - 13:05
 * @project Mini_transaction/IntelliJ IDEA
 */
@Service
public class UserDetailsServiceImpl extends AbstractService<UserRepository, UserMapper> implements UserDetailsService {
    public UserDetailsServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));
        return UserDetailsImpl.build(user);
    }
}
