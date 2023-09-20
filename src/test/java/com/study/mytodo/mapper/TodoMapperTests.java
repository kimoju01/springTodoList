package com.study.mytodo.mapper;

import com.study.mytodo.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
// @ExtendsWith: JUnit5 버전에서 'spring-test'를 사용하기 위한 설정
@ExtendWith(SpringExtension.class)
// @ContextConfiguration: 스프링의 설정 정보를 로딩하기 위해 사용
// XML 설정 시에는 locations 속성, 자바 설정 시에는 classes 속성을 이용
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    // @Autowired: 의존성 주입 관련 어노테이션. '만일 해당 타입의 Bean이 존재하면 여기에 주입해 주기를 원한다'라는 의미
    // required = false: 해당 객체를 주입 받지 못하더라도 예외가 발생하지 않는다.
    // (인텔리제이는 직접 스프링의 Bean으로 등록된 경우가 아니면 경고가 발생하므로 방지하기 위해 설정)
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {

        TodoVO todoVO = TodoVO.builder()
                .title("입력 테스트")
                .dueDate(LocalDate.of(2023,12,25))
                .writer("user00")
                .build();

        todoMapper.insert(todoVO);

    }

}
