package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.lcools")
public class BootApplicationTest {


    public static void main(String[] args) {
        SpringApplication.run(BootApplicationTest.class, args);
    }


}