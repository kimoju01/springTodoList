package com.study.mytodo.dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@Data   // getter, setter, toString, equals, hashCode, requiredArgsConstructor를 모두 컴파일할 때 생성해준다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;

}
