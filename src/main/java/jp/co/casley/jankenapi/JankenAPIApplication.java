package jp.co.casley.jankenapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("jp.co.casley.jankenapi.repository.db.dao")
@SpringBootApplication
public class JankenAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(JankenAPIApplication.class, args);
    }

}
