package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // HttpMessageConverter
        // JsonConverter, StringConverter
        /**
         * @ResponseBody를 사용
         * HTTP의 BODY에 문자 내용을 직접 반환
         * `viewResolver` 대신에 `HttpMessageConverter`가 동작
         * 기본 문자처리: `StringHttpMessageConverter`
         * 기본 객체처리: `MappingJackson2HttpMessageConverter`
         * byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
         * 
         * 참고: 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서
         * `HttpMessageConverter`가 선택된다. 더 자세한 내용은 스프링 MVC 강의에서 설명
         * [인프런] 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
         * 김영한
         **/
        return hello;
    }
    
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
