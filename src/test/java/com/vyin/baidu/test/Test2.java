package com.vyin.baidu.test;
/**
* @ author liu qihang:
* @version 创建时间 2018年6月24日上午12:41:49
* 
*/
public class Test2 implements Runnable{
	
	private int i;
	
	public Test2(int i) {
		super();
		this.i = i;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
			Thread thread = new Thread(new Test2(i));
			thread.start();
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run=" + i);
	}

}
