package com.study.mytodo.service;

import com.study.mytodo.dto.PageRequestDTO;
import com.study.mytodo.dto.PageResponseDTO;
import com.study.mytodo.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    // TodoService 타입의 Bean 주입
    // TodoService는 TodoServiceImpl 클래스의 인스턴스를 가리키며 이 인스턴스는 IoC 컨테이너에 의해 관리된다.
    @Autowired(required = false)
    private TodoService todoService;

    @Test
    public void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("등록 테스트")
                .dueDate(LocalDate.now())
                .writer("user1").build();

        // 스프링이 알아서 IoC 컨테이너에서 관리하는 TodoServiceImpl Bean을 주입하고 메서드를 실행한다.
        // 만약 TodoService 클래스에도 @Service 어노테이션이 붙어있었더라도 스프링 IoC 컨테이너가 알아서
        // TodoService 인터페이스 구현체를 주입해서 TodoServiceImpl의 메서드가 실행된다.
        todoService.register(todoDTO);

    }

    @Test
    public void testPaging() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);

        log.info(pageResponseDTO);

        pageResponseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));

    }


}
