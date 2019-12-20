package com.hecm.vuelog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.hecm.vuelog")
@MapperScan({"com.hecm.vuelog.**.mapper"})
public class VuelogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VuelogApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" );
    }

}
