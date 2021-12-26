package com.kh.spring.tv;

public class LgTv implements Tv {

	private RemoteControl remocon;
	
//	public LgTv(RemoteControl remocon) {
//		this.remocon = remocon;
//	System.out.println("LgTv 객체를 생성합니다");
//	}

	//setter
	public void setRemocon(RemoteControl remocon) {
		System.out.println("remocon의존을 주입: " + remocon);
		this.remocon = remocon;
	}
	
	//Tv.changeChannel
	public void changeChannel(int no) {
		remocon.changeChannel(no);
	}

	@Override
	public void powerOn() {
		System.out.println("LgTv의 전원을 켭니다");

	}

}
