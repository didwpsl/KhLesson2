package com.kh.spring.pet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PetMain {
	public static void main(String[] args) {
		//빈 등록
		String configLocation = "/pet-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		System.out.println("==============spring container 빈 등록 완료================");
		
		Person person = context.getBean(Person.class);
		person.helloPet();
	}
}
