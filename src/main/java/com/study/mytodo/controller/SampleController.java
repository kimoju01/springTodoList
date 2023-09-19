package com.study.mytodo.controller;

import com.study.mytodo.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello...........");
    }

    // 예를 들어 /ex1?name=AAA&age=16 호출 시 자동으로 처리된다.
    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    // defaultValue? 전달된 파라미터 없을 때 기본값 지정하기
    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,
                    @RequestParam(name = "age", defaultValue = "20") int age) {
        log.info("ex2..........");
        log.info("name: " + name);
        log.info("age: " + age);

    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3..........");
        log.info("dueDate: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("-----------");

        model.addAttribute("message", "Hello World");

    }

    @GetMapping("/ex4_1")
    public void ex4Extra(TodoDTO todoDTO, Model model) {    // 이 경우에는 JSP에서 ${todoDTO}로 사용
    // 또는 public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model) { // 이 경우에는 ${dto}로 사용

        log.info(todoDTO);

    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {

        // 리다이렉트할 때 쿼리 스트링이 되는 값을 지정
        redirectAttributes.addAttribute("name", "ABC");
        // 일회용으로만 데이터를 전달하고 삭제되는 값을 지정. URL에는 보이지 않지만 JSP에서 일회용으로 사용한다.
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6";

    }

    @GetMapping("/ex6")
    public void ex6() {

    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
        // 문자열과 숫자를 파라미터로 처리한다.
        // 만약 브라우저에서 숫자 대신에 알파벳을 보낸다면 NumberFormatException이 발생하고 실행 결과 400 에러(잘못된 요청)가 발생한다.
        // 이를 해결하기 위해서 CommonExceptionAdvice에서 처리한다. -> 처리 후 @ResponseBody 이기 때문에 해당 return 메시지를 출력한다.
        log.info("p1.........." + p1);
        log.info("p2.........." + p2);
    }

}
