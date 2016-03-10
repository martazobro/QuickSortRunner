package com.mz.concurrency.main;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.mz.concurrency.main.impl.forkjoin.CounterCompleterQuickSort;
import com.mz.concurrency.main.impl.forkjoin.ForkJoinQuickSort;
import com.mz.concurrency.main.impl.recursive.RecursiveQuickSort;
import com.mz.concurrency.main.util.TimeUtil;

public class Main {
	private static final int ARRAY_SIZE = 50;
	private static boolean PRINT_ARRAY = true;
	
	private static final boolean WAIT_BEFORE_COMPLETION = false;
	private static final int SLEEP_TIME = 50;
	
	
	public static void main(String[] args) {
		Integer[] intArray = generateRandom(ARRAY_SIZE, 2000);
		sort(Arrays.copyOf(intArray, intArray.length), new RecursiveQuickSort());
		sort(Arrays.copyOf(intArray, intArray.length), new ForkJoinQuickSort());
		sort(Arrays.copyOf(intArray, intArray.length), new CounterCompleterQuickSort());
	}
	
	private static Integer[] generateRandom(int arraySize, int bound) {
		return new Random().ints(arraySize, 0, bound).boxed().toArray(Integer[]::new);
	}
	
	private static long sort(Integer[] intArray, QuickSort sorter) {
		printArray("Before sort", intArray);
		long start = System.nanoTime();
		Supplier<Void> supplier = !WAIT_BEFORE_COMPLETION ? null : () -> TimeUtil.sleepRandomTime(SLEEP_TIME, TimeUnit.MILLISECONDS);
		sorter.sort(intArray, supplier);
		long end = System.nanoTime();
		long duration = (end - start) / 1000000;
		printArray("After sort", intArray);
		System.out.printf("%s duration = %d millis; array size = %d\n", sorter.getClass().getSimpleName(), duration, intArray.length);
		System.out
				.println("**************************************************************\n");
		return duration;
	}

	private static void printArray(String message, Integer[] intArray) {
		if (PRINT_ARRAY){
			System.out.printf("%s: %s \n", message, Arrays.toString(intArray));
		}
	}
}
