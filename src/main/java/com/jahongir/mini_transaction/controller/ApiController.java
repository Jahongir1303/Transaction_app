package com.jahongir.mini_transaction.controller;

import com.jahongir.mini_transaction.service.base.BaseService;
import lombok.RequiredArgsConstructor;

/**
 * @author jahongir
 * @created 01/02/23 - 12:25
 * @project Mini_transaction/IntelliJ IDEA
 */
@RequiredArgsConstructor
public abstract class ApiController<S extends BaseService> {
    protected final S service;
    protected final String API = "/api";
    protected final String V1 = "/v1";
}
