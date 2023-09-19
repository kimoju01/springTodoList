package com.study.mytodo.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

// 컨트롤러에서 발생하는 예외에 맞게 처리할 수 있는 기능을 제공한다. 스프링의 Bean으로 처리된다.
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // 메서드가 반환하는 객체나 데이터가 HTTP 응답 본문에 직접 들어간다.
    @ResponseBody
    // 전달되는 Exception 객체들을 지정하고 @ExceptionHandler를 가진 모든 메서드는 해당 타입의 예외를 파라미터로 전달 받을 수 있다.
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException) {
        // @ResponseBody를 통해 만들어진 문자열을 그대로 브라우저에 전송한다.

        log.error("--------------------");
        log.error(numberFormatException.getMessage());

        // return 메시지를 브라우저에 출력한다.
        return "NUMBER FORMAT EXCEPTION";

    }

    @ResponseBody
    // 발생한 문제를 자세한 메시지로 확인하고 싶은 경우 예외 처리의 상위 타입인 Exception 타입을 처리하도록 구성한다.
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {

        log.error("--------------------");
        log.error(exception.getMessage());

        // <ul> 태그로 리스트 시작
        StringBuffer buffer = new StringBuffer("<ul>");
        // 예외 메시지를 <li> 태그로 감싸서 리스트 아이템으로 추가
        buffer.append("<li>" + exception.getMessage() + "</li>");

        // 예외의 stackTrace 정보를 스트림으로 변환, 각 stackTrace 요소를 <li> 태그로 감싸서 리스트 아이템으로 추가
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });

        // 리스트 닫는 </ul> 태그 추가
        buffer.append("</ul>");

        // buffer에 저장된 HTML 형식의 문자열을 반환해 클라이언트에게 응답
        return buffer.toString();

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    // 잘못된 URL을 호출하게 되면 404(Not Found) 예외가 발생하면서 톰캣이 보내는 메시지를 보게 된다.
    // @ResponseStatus를 이용하면 상태에 맞는 화면을 별도로 작성할 수 있다.
    // web.xml 설정 조정 필요!
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {

        // custom404.jsp 파일을 보여준다.
        return "custom404";

    }

}
