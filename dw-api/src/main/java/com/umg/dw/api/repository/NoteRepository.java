package com.umg.dw.api.repository;

import com.umg.dw.core.entities.Note;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface NoteRepository extends PagingAndSortingRepository<Note, Integer>,
        QueryByExampleExecutor<Note> {
}
