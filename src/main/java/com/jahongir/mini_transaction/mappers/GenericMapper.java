package com.jahongir.mini_transaction.mappers;

import com.jahongir.mini_transaction.dtos.base.Dto;
import org.mapstruct.Mapper;

/**
 * @author jahongir
 * @created 02/02/23 - 01:07
 * @project Mini_transaction/IntelliJ IDEA
 */
public interface GenericMapper<E, CD> extends BaseMapper {
    E fromCreateDto(CD dto);
}
