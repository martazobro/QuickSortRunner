package com.mz.concurrency.main.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

	public static Void sleepRandomTime(int maxTime, TimeUnit unit){
		try {
			unit.sleep(new Random().nextInt(maxTime));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Void sleepNoException(int time, TimeUnit unit) {
		try {
			unit.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
