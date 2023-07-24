package com.tpm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tpm.**.mapper")
public class TicketCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketCatApplication.class, args);
    }

}
