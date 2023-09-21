package com.study.mytodo.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data   // getter, setter, toString, equals, hashCode, requiredArgsConstructor를 모두 컴파일할 때 생성해준다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    @NotEmpty   // Null 불가
    private String title;

    @Future     // 현재 보다 미래인가?
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer;

}
