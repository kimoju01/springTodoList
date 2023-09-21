package com.study.mytodo.controller;

import com.study.mytodo.dto.TodoDTO;
import com.study.mytodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    // /t odo/list 경로
    @RequestMapping("/list")
    public void list(Model model) {

        log.info("todo list..........");

        model.addAttribute("dtoList", todoService.getAll());

    }

    // GET /t odo/register 경로
    @GetMapping("/register")
    public void registerGet() {

        log.info("GET todo register..........");

    }

    // POST /t odo/register
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // @Valid, BindingResult를 통해 DTO 검증
        // RedirectAttributes: 리다이렉션을 통해 다른 페이지로 이동할 때 데이터를 함께 전달할 수 있다.

        log.info("POST todo register..........");

        if(bindingResult.hasErrors()) {
            // 검증에 문제가 있다면 다시 입력 화면으로 리다이렉션
            // 처리 과정에서 잘못된 결과는 error라는 이름의 일회용 데이터로 JSP에 전달된다. (새로고침하면 삭제되는 데이터)
            log.info("has error.........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";

    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model) {

        log.info("todo read..........");

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);

    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {

        log.info("todo remove..........");
        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";

    }


}
