package com.study.mytodo.controller;

import com.study.mytodo.dto.PageRequestDTO;
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

    // /t odo/list 경로 - 페이징 X
//    @RequestMapping("/list")
//    public void list(Model model) {
//
//        log.info("todo list..........");
//
//        model.addAttribute("dtoList", todoService.getAll());
//
//    }

    @GetMapping("/list")    // /list?page=3&size=20..
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        // PageRequestDTO를 파라미터로 처리하고 Model에 PageResponseDTO 담아서 JSP로 넘겨준다.
        // 파라미터로 전달되는 PageRequestDTO는 Model로 자동 전달된다. 따로 선언할 필요 없이 JSP에서 사용 가능.

        log.info("todo list..........");

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()) {
            // 잘못된 파라미터 값이 들어오는 경우는 page=1, size=10으로 고정되게 처리한다.
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));

    }

    // GET /t odo/register 경로
    @GetMapping("/register")
    public void registerGet() {

        log.info("GET todo register..........");

    }

    // POST /t odo/register
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
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
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {
        // 조회 페이지, 수정 페이지에서 목록 페이지로 이동할 때 현재 페이지 번호를 유지하기 위해 PageRequestDTO 파라미터 필요
        // JSP에서 목록 버튼 링크 처리 해주어야 해서 필요하다.

        log.info("todo read/modify..........");

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);

    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("todo remove..........");
        log.info("tno: " + tno);

        todoService.remove(tno);

        // form 태그로 전송 받은 size 값과 1페이지로 설정한 page 값을 이용해 목록 페이지로 이동
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list?" + pageRequestDTO.getLink();   // 삭제 처리 후에도 검색 결과 반영

    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         PageRequestDTO pageRequestDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("has error..........");
            // 처리 과정에서 잘못된 결과는 error라는 이름의 일회용 데이터로 JSP에 전달된다. (새로고침하면 삭제되는 데이터)
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            // 리다이렉트할 때 쿼리 스트링이 되는 값을 지정. modify?tno= 경로로 이동된다.
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);
        todoService.modify(todoDTO);

        // 현재 페이지 번호를 유지하기 위해 리다이렉트할 때 쿼리 스트링으로 page, size 값도 추가해준다.
        // 주석 처리 한 이유: 글 수정 후 검색, 필터링 조건에 부합하지 않을 수 있기 때문에
        // 목록 화면으로 이동하지 않고 검색, 필터링 조건 초기화 하면서 조회 화면으로 이동할 거라서 page, size값 필요 없음
//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read";

    }


}
