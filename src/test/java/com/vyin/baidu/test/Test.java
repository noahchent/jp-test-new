package com.vyin.baidu.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ author liu qihang:
 * @version 创建时间 2018年6月20日下午3:51:38
 */
public class Test {

	public void task() {

		ExecutorService service = Executors.newFixedThreadPool(5);  
		for (int i = 0; i < 1000; i++) {
			final int index = i;
			if(index == 999){
				System.out.println("for循环走完了" + index);
			}
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.print("线程名称：" + Thread.currentThread().getName() +  ", " + index + ":");
					writeData(index);
				}
			});
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
		try {
			if(!service.awaitTermination(1 * 1000, TimeUnit.MILLISECONDS)){
				System.out.println("5秒时间到，终止线程");
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			service.shutdownNow();
		}
	}

	public void writeData(int num) {
		int flag = num;
		System.out.println(flag);
	}

	public static void main(String[] args) {
		Test test = new Test();
		for (int i = 0; i < 1; i++) {
//			System.out.println("第" + (i+1) + "次task");
			test.task();
		}
	}


}
