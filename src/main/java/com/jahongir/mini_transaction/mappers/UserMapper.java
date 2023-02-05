package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.domains.User;
import com.jahongir.mini_transaction.dtos.user.RegisterRequest;
import com.jahongir.mini_transaction.mappers.config.EncodedMapping;
import com.jahongir.mini_transaction.mappers.config.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * @author jahongir
 * @created 02/02/23 - 14:08
 * @project Mini_transaction/IntelliJ IDEA
 */
@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper extends BaseMapper {
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    User fromCreateDto(RegisterRequest request);

}
