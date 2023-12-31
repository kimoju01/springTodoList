package com.study.mytodo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

// 제네릭을 이용해 다른 종류의 객체를 이용해서 구성할 수 있도록 한다.
// 예를 들어 회원 정보 등도 페이징 처리가 필요할 수 있기 때문에 공통적인 처리를 위해 사용.
// ex) public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) { .. }
@Getter
@ToString
public class PageResponseDTO<E> {
    // TodoMapper에서 가져온 TodoVO의 목록과 전체 데이터 수를 한 번에 담아서 처리하는 DTO

    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;
    // 마지막 페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    // 생성자
    @Builder
//    @Builder(builderMethodName = "withAlll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        // 현재 페이지 번호(page)가 1인 경우: 시작 페이지(start)는 1, 마지막 페이지(end)는 10
        // 현재 페이지 번호(page)가 10인 경우: 시작 페이지(start)는 1, 마지막 페이지(end)는 10
        // 현재 페이지 번호(page)가 11인 경우: 시작 페이지(start)는 11, 마지막 페이지(end)는 20

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        // end 값은 page를 10으로 나눈 값을 올림 처리하고 * 10
        // 1 / 10 => 0.1 => 1 => 10
        // 11 / 10 => 1.1 => 2 => 20
        // 하지만 마지막 페이지가 10이 아닌 8 이런식으로 떨어지는 것도 처리해야하기 때문에 아래 코드에서 처리해준다.
        this.end = (int) (Math.ceil(this.page / 10.0) * 10);
        // start 값은 end 값에서 9만 빼면 된다.
        this.start = end - 9;

        // 마지막 페이지의 경우 전체 데이터 개수(total)을 고려해야 한다.
        // 왜? 만약 10개씩(size) 보여주는 경우 total이 75라면 마지막 페이지는 10이 아닌 8이 되어야하니까.
        // total=75, size=10이라고 치면 last=8이 된다.
        // last=8이라면 9페이지부턴 보여줄 리스트가 없다는 뜻.
        // 그렇기 때문에 end=10, last=8이라면 9, 10페이지엔 보여줄 리스트가 없다. => end 값이 last 값으로 변경되어야 한다.
        int last = (int) Math.ceil(total / (double) size);
        this.end = end > last ? last : end;

        // 이전 페이지의 존재 여부는 시작 페이지(start)가 1이 아니라면 무조건 true
        // 다음 페이지는 전체 데이터 수가 마지막 페이지의 끝 번호를 초과하면 존재한다.
        // 마지막 페이지=8 이고 페이지당 개수=10 이면 80인데 전체 데이터 수가 80을 넘으면 다음 페이지가 있다는 뜻
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}
