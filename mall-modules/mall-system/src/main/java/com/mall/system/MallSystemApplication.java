package com.mall.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mall.common.security.annotation.EnableCustomConfig;
import com.mall.common.security.annotation.EnableRyFeignClients;

/**
 * 系统模块
 * 
 * @author mall
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class MallSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MallSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
