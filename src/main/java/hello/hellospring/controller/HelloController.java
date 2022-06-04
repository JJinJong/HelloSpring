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
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    
    
    /*템플릿 엔진 방식*/
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //  템플릿 엔진에 비해 그냥 데이터를 내려줌 페이지소스보면 html이런 태그들이 없음
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
//      단순 문자면 ResopnseBody를 주면 body에 뿌림
        return "hello " + name; //"hello spring"
    }

    
    /*API 방식*/
    @GetMapping("hello-api")
    @ResponseBody
//    @ResponseBody시 => viewResolver대신 HttpMessageConverter가 Json 스타일로 바꿈
    public Hello helloApi(@RequestParam("name") String name) {
//        객체가 오면 제이슨 방법으로 데이터를 만들어서 http응답에 반환하는게 기본 방식임
        Hello hello = new Hello();
        hello.setName(name);
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
