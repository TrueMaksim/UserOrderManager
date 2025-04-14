package com.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//основной класс Spring Boot приложения

@SpringBootApplication//аннотация, которая добавляет все следующее три ключевые
// аннотации Spring( @Configuration,@EnableAutoConfiguration,@ComponentScan)
public class SpringMvcAppApplication {

    public static void main(String[] args) { //метод запускает приложенрие
        //инициализирует контекст Spring (контейнер управляемых объектов),
        // выполняет автоконфигурацию и запускает встроенный веб-сервер (например, Tomcat).
        SpringApplication.run(SpringMvcAppApplication.class, args);//args — аргументы командной строки, переданные при запуске.
    }

}
