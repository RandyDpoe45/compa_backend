package com.wesdom.compa.backend.service.interfaces;

import com.wesdom.compa.backend.database.model.Request;

public interface IRequestService {

    Request createRequest(Request request);
    Request updateRequest(Long id, Request request);
}
