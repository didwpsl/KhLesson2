package com.kh.spring.tv;

public class SamsungTv implements Tv {
	
	private RemoteControl remocon;
	
//	public SamsungTv(RemoteControl remocon) {
//		this.remocon = remocon;
//	}
//	
//	public SamsungTv() {
//		System.out.println("SamsungTv 객체를 생성합니다");
//	}
	
	public void setRemocon(RemoteControl remocon) {
		System.out.println("remocon의존을 주입" + remocon);
		this.remocon = remocon;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTv의 전원을 켭니다");
	}

	@Override
	public void changeChannel(int no) {
		remocon.changeChannel(no);
	}

}
