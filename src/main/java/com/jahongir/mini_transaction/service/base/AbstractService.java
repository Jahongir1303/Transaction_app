package com.jahongir.mini_transaction.service.base;

import com.jahongir.mini_transaction.mappers.BaseMapper;
import com.jahongir.mini_transaction.repository.GenericRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author jahongir
 * @created 02/02/23 - 00:46
 * @project Mini_transaction/IntelliJ IDEA
 */
@RequiredArgsConstructor
public abstract class AbstractService<R extends GenericRepository, M extends BaseMapper> {
    protected final R repository;
    protected final M mapper;
}
