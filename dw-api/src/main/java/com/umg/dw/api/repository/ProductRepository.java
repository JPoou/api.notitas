/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umg.dw.api.repository;


import com.umg.dw.core.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author mmendez
 */

public interface ProductRepository
        extends PagingAndSortingRepository<Product, Integer>,
        QueryByExampleExecutor<Product> {

}
