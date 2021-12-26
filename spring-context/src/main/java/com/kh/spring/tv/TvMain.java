package com.kh.spring.tv;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * context에 등록할 빈을 작성하는 방법 
 * 1. xml
 * 2. java annotation
 * 3. java annotation + xml
 */
public class TvMain {
	public static void main(String[] args) {
		//1. ApplicationContext 생성 (Bean을 관리하는 객체)
		String configLocation = "/application-context.xml";
		//configLocation을 읽어 ApplicationContext가 Bean을 관리한다 이 때 bean에 등록된 객체 생성 
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		System.out.println("=====================spring container 초기화 완료======================");
		
		// = Tv lgTv1 = new TV();
		Tv lgTv1 = context.getBean(LgTv.class); //클래스명으로 가져오기 
		lgTv1.powerOn();
		lgTv1.changeChannel(8);
		
		Tv lgTv2 = (Tv)context.getBean("lgTv"); //id로 가져오기 타입을 Object로 반환하므로 명시적 형변환 필요
		lgTv2.powerOn();
		lgTv2.changeChannel(20);
		
		System.out.println(lgTv1);
		System.out.println(lgTv2);
		System.out.println(lgTv1 == lgTv2);
		
		System.out.println("엔터 입력시 삼성Tv 객체를 사용합니다...");
		new Scanner(System.in).nextLine();
		Tv samsungTv = context.getBean("samsungTv", SamsungTv.class); //하이브리드로 명시할 경우 따로 형변환이 필요하지 않음 
		samsungTv.powerOn();
		samsungTv.changeChannel(10);
		
		
	}
}
