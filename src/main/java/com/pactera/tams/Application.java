package com.pactera.tams;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * 应用启动入口
 * 
 * @Author: mjh
 * @Date: 2018-03-19 12:19:21
 */

@Controller
@EnableWebMvc
@SpringBootApplication(scanBasePackages = { "com.pactera.tams" })
@MapperScan(basePackages = { "com.pactera.tams.module.system.mapper", "com.pactera.tams.module.feedback.mapper",
		"com.pactera.tams.module.front.mapper", "com.pactera.tams.module.notice.mapper",
		"com.pactera.tams.module.product.mapper", "com.pactera.tams.module.tool.mapper",
		"com.pactera.tams.module.video.mapper", "com.pactera.tams.module.machine.mapper" })
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("服务启动完成!");
	}

}
