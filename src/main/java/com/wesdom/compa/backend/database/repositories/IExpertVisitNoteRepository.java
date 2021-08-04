package com.wesdom.compa.backend.database.repositories;

import com.wesdom.compa.backend.database.model.ExpertVisitNote;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IExpertVisitNoteRepository {

    public ExpertVisitNote get(Long id);
    Page<ExpertVisitNote> getAll(Map<String, String> params);
    public ExpertVisitNote save(ExpertVisitNote expertVisitNote);
    public ExpertVisitNote update(Long expertVisitNoteId, ExpertVisitNote expertVisitNote);
    public void delete(Long expertVisitNoteId);
}
