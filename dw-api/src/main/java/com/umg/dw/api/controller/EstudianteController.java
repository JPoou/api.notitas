/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umg.dw.api.controller;


import com.umg.dw.api.repository.EstudianteRepository;
import com.umg.dw.core.entities.Estudiante;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mmendez
 */
@Api(value = "/", description = "REST Estudiante")
@RestController
@RequestMapping("/estudiante")
//@EnableJpaRepositories(basePackages = "com.umg.dw.api.repository")
public class EstudianteController {

    @Autowired
    EstudianteRepository estudianteRepository;

    @CrossOrigin
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Estudiante> getAll() {
        return (List<Estudiante>) estudianteRepository.findAll();
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = "application/json")
    public Estudiante create(@RequestBody Estudiante estudiante) {
        estudiante = estudianteRepository.save(estudiante);
        return estudiante;
    }


    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            produces = "application/json")
    public Estudiante update(@RequestBody Estudiante estudiante) {
        estudiante = estudianteRepository.save(estudiante);
        return estudiante;
    }
}
