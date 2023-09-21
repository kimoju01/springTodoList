package com.study.mytodo.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

// 체크박스가 클릭된 상태일 때 브라우저에서 전송되는 'on' 값을
// boolean으로 처리하기 위해 타입을 변경해줄 때 사용할 Formatter.
// servlet-context.xml에 등록 필요
public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null) {
            return false;
        }
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
