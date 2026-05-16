package com.mall.ums;

import com.mall.common.security.annotation.EnableCustomConfig;
import com.mall.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 * 
 * @author mall
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class MallUmsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MallUmsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ums模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
