package com.wesdom.compa.backend.service.implementation;

import com.wesdom.compa.backend.database.enums.RequestStatusEnum;
import com.wesdom.compa.backend.database.model.Request;
import com.wesdom.compa.backend.database.repositories.IRequestRepository;
import com.wesdom.compa.backend.service.interfaces.INotificationService;
import com.wesdom.compa.backend.service.interfaces.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class RequestServiceImpl implements IRequestService {

    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private INotificationService notificationService;

    @Override
    public Request createRequest(Request request) {
        String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        request.setStatus(RequestStatusEnum.SENT.getCode())
                .setRequestDate(LocalDate.now())
                .setStatusDateHistory(dateString)
                .setStatusHistory(RequestStatusEnum.SENT.getCode());
        request = requestRepository.save(request);
        if (request != null) {
            notificationService.createRequestNotification(request);
        }
        return request;
    }

    @Override
    public Request updateRequest(Long id, Request request) {
        Request request1 = requestRepository.get(id);
        if (request.getStatus() != null && !request1.getStatus().equals(request.getStatus())) {
            String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String h1 = Objects.toString(request1.getStatusDateHistory(), dateString);
            String h2 = Objects.toString(request1.getStatusHistory(), request.getStatus());
            if (request1.getStatusHistory() == null || request1.getStatusHistory().isEmpty()) {
                request.setStatusDateHistory(h1);
                request.setStatusHistory(h2);
            } else {
                request.setStatusDateHistory(h1 + "," + dateString);
                request.setStatusHistory(h2 + "," + request.getStatus());
            }

        }
        request = requestRepository.update(id, request);
        return request;
    }
}
