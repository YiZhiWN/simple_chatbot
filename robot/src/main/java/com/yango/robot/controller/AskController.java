package com.yango.robot.controller;

import com.yango.robot.service.AskService;
import com.yango.robot.vo.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    @Autowired
    AskService askService;

    @GetMapping("/ask/{question}")
    public Bean ask(@PathVariable("question") String question){

        return askService.ask(question);
    }
}
