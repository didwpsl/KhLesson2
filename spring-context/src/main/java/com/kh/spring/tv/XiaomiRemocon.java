package com.kh.spring.tv;

public class XiaomiRemocon implements RemoteControl {

	@Override
	public void changeChannel(int no) {
		System.out.println("채널을 " +no+ "번으로 변경합니다 by Xiaomi");
	}

}
