package com.study.mytodo.mapper;

import org.apache.ibatis.annotations.Select;

// Mapper 인터페이스
public interface TimeMapper {
    // DB의 현재 시각을 문자열로 처리하도록 구성

    @Select("select now()")
    String getTime();

}
