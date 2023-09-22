package com.study.mytodo.mapper;

import com.study.mytodo.domain.TodoVO;
import com.study.mytodo.dto.PageRequestDTO;
import com.study.mytodo.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    // tno 내림차순(최근 글 부터)으로 select
    List<TodoVO> selectAll();

    // tno를 통해 글 조회
    TodoVO selectOne(Long tno);

    void delete(Long tno);

    // 해당 tno 번호의 title, dueDate, finished 수정
    void update(TodoVO todoVO);

    // limit를 사용해서 pageRequestDTO의 getSkip, getSize 값으로 select해서 TodoVO 리스트로 반환
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    // 화면에 페이지 번호를 구성하기 위해 전체 데이터의 수를 가져옴
    int getCount(PageRequestDTO pageRequestDTO);

}
