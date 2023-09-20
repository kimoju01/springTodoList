package com.study.mytodo.config;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration  // 스프링의 설정 클래스임을 나타낸다. 스프링 컨테이너가 Bean으로 등록.
@Log4j2
public class ModelMapperConfig {
    // ModelMapper를 스프링 Bean으로 등록

    @Bean   // getMapper() 메서드의 실행 결과로 반환된 객체를 스프링의 Bean으로 등록시킨다.
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)  // 필드 매칭 활성화
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)  // 매핑을 위해 private 필드 접근 허용
                .setMatchingStrategy(MatchingStrategies.STRICT);    // 엄격한 매핑

        return modelMapper; // 설정이 완료된 ModelMapper 인스턴스 반환
    }
}
