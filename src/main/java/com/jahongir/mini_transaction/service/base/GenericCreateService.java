package com.jahongir.mini_transaction.service.base;

import com.jahongir.mini_transaction.dtos.base.Dto;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

/**
 * @author jahongir
 * @created 04/02/23 - 20:13
 * @project Mini_transaction/IntelliJ IDEA
 */

public interface GenericCreateService<ID extends Serializable, CD extends Dto> extends BaseService {
    ID create(@Nonnull CD dto);
}
