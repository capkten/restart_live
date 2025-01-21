package org.capten.live;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.capten.live.mapper")
public class RestartLiveBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestartLiveBackendApplication.class, args);
    }

}
