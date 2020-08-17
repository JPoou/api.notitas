package com.umg.dw.api.repository;

import com.umg.dw.core.entities.Liked;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface LikedRepository extends PagingAndSortingRepository<Liked, Integer>,
        QueryByExampleExecutor<Liked> {
}
