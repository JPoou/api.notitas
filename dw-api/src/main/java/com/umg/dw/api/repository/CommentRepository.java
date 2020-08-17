package com.umg.dw.api.repository;

import com.umg.dw.core.entities.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>,
        QueryByExampleExecutor<Comment> {
}
