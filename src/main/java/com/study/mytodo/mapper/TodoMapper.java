package com.study.mytodo.mapper;

import com.study.mytodo.domain.TodoVO;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

}
