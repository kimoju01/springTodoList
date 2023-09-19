package com.study.mytodo.controller;

import com.study.mytodo.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    // /t odo/list 경로
    @RequestMapping("/list")
    public void list() {
        log.info("todo list..........");
    }

    // /t odo/register 경로
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("todo register..........");
    }

    @PostMapping("/register")
    public void registerPost(TodoDTO todoDTO) {

        log.info("POST todo register..........");
        log.info(todoDTO);

    }

}
