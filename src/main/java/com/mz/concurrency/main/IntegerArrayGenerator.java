package com.mz.concurrency.main;

import java.util.Random;

public class IntegerArrayGenerator {

	public static Integer[] generateRandom(int arraySize, int bound) {
		Random random = new Random();
		Integer[] integers = new Integer[arraySize];
		for (int i = 0; i < arraySize; i++){
			integers[i] = random.nextInt(bound);
		}
		return integers;
	}

}
