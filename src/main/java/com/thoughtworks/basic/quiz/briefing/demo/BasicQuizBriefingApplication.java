package com.thoughtworks.basic.quiz.briefing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO GTB-1: * 功能都完成了，good
// TODO GTB-4: * 分包比较合理
// TODO GTB-3: * 对Java8 Optional有不错的使用
// TODO GTB-3: * 对Spring MVC相关注解使用合理
// TODO GTB-3: * API设计符合Restful实践
// TODO GTB-3: * 可以加强下Java8 Stream的掌握
// TODO GTB-3: * 使用了三层架构
// TODO GTB-3: * 使用了Lombok
// TODO GTB-2: * Service和Controller层均有测试，且测试了大部分逻辑
@SpringBootApplication
public class BasicQuizBriefingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicQuizBriefingApplication.class, args);
	}

}
