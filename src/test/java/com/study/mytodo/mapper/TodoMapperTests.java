package com.study.mytodo.mapper;

import com.study.mytodo.domain.TodoVO;
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
import java.util.List;

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

    @Test
    public void testSelectAll() {

        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));

    }

    @Test
    public void testSelectOne() {

        TodoVO vo = todoMapper.selectOne(1L);

        log.info(vo);

    }

    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        // skip = (page - 1) * 10 이기 때문에 page=2라면 skip은 10이 된다. -> 즉 처음 10개의 게시글을 건너뛴 2페이지라는 뜻.

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));

    }

    @Test
    public void testSelectSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .keyword("ABC")
                .finished(true)
                .from(LocalDate.of(2022,12,25))
                .to(LocalDate.of(2023,12,25))
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));

    }


}
