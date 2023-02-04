package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jahongir
 * @created 02/02/23 - 14:08
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, RegisterRequest> {

}
