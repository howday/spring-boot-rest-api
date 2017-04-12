package com.rest.api.controller;

import com.rest.api.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by suresh on 4/11/17.
 */
@RestController
public class GeneController {

    @Autowired
    GeneService geneService;

    @RequestMapping(path = "/add")
    public String add() {

        geneService.add();
        return "ADD";
    }

    @RequestMapping(path = "/get")
    public String get() {

        geneService.get();
        return "GET";
    }
}
