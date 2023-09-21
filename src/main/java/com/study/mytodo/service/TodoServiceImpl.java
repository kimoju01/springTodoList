package com.study.mytodo.service;

import com.study.mytodo.domain.TodoVO;
import com.study.mytodo.dto.TodoDTO;
import com.study.mytodo.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor    // 생성자 생성
public class TodoServiceImpl implements TodoService {

    // 생성자 주입
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);

        todoMapper.insert(todoVO);

    }

    @Override
    public List<TodoDTO> getAll() {

        // todoMapper.selectAll()이 TodoVO를 반환하기 때문에 VO를 DTO로 바꿔주는 작업 필요
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;

    }

    @Override
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;

    }

    @Override
    public void remove(Long tno) {

        todoMapper.delete(tno);

    }

}
