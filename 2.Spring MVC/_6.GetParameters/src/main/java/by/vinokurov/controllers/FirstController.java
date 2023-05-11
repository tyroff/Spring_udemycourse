package by.vinokurov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    // объект HttpServletRequest содержит все данные об http запросе (заголовки, параметры, хедары и т.д)
    // http://localhost:8080/first/hello?name=Kir&surname=Vin в консоль выведется сообщение "Hello Kir Vin"
    // если в запросе не указывать параметры то http://localhost:8080/first/hello в консоль выведется сообщение "Hello null null"
//    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//
//        System.out.println("Hello " + name + " " + surname);
//
//        return "first/hello";
//    }


    // если в запросе нужно только параметры то используют аннотацию @RequestParam
    // http://localhost:8080/first/hello?name=Kir&surname=Vin в консоль выведется сообщение "Hello Kir Vin"
    // если в запросе не буде параметров, будет ошибка "HTTP Status 400 – Bad Request"
    // т.к. данный способ ждёт параметры.
    // и что бы этого не было необходимо в аннотацию @RequestParam добавить переменную "required = false"
    // если значения не будет то подставиться null
    @GetMapping("/hello")
//    public String helloPage(@RequestParam("name") String name,
//                            @RequestParam("surname") String surname) {
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                @RequestParam(value = "surname", required = false) String surname) {
        System.out.println("Hello " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
