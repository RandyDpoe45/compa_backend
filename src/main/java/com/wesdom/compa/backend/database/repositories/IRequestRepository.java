package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.Request;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IRequestRepository {
    public Request get(Long id);
    public Page<Request> getAll(Map<String,String> params);
    public Request create(Request request);
    public Request update(Long requestId, Request request);
    public void delete(Long requestId);
}
