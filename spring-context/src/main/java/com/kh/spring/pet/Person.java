package com.kh.spring.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
//bean으로 등록할 class에 @Component 추가 
public class Person {
	//의존 주입이 필요한 대상 
	@Autowired 
	private Pet pet;

	/*
	 * @Autowired를 쓸 수 있는 위치 
	 * 1. 생성자 
	 * 2. setter  
	 * 3. field 
	 */
	
	//@Autowired
	public Person(Pet pet) {
		System.out.println("생성자 : " + pet);
		this.pet = pet;
	}
	//@Autowired
	public void setPet(Pet pet) {
		System.out.println("setter : " + pet);
		this.pet = pet;
	}
	
	public void helloPet() {
		pet.helloPerson();
	}
}
