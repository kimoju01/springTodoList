package com.study.mytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 현재 페이지의 번호(page), 한 페이지당 보여주는 데이터의 수(size)를 보관하고 전달하기 위한 DTO
    // 2개의 숫자를 매번 전달하기 보다 DTO로 만들어두면 확장성이 좋다.

    @Builder.Default    // 매개변수를 제공하지 않으면 page 값을 기본 1로 설정
    @Min(value = 1)
    @Positive
    private int page = 1;   // 현재 페이지의 번호

    @Builder.Default    // 매개변수를 제공하지 않으면 size 값을 기본 10으로 설정
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;  // 한 페이지당 보여주는 데이터 수

    public int getSkip() {  // limit에서 사용할 skip의 숫자는 (page - 1) * 10으로 설정된다.
        return (page - 1) * 10;
    }

}
