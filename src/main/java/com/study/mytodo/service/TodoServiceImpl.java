package com.study.mytodo.service;

import com.study.mytodo.domain.TodoVO;
import com.study.mytodo.dto.TodoDTO;
import com.study.mytodo.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
