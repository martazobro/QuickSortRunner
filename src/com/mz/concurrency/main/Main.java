package com.mz.concurrency.main;

import java.util.Arrays;

import com.mz.concurrency.main.impl.forkjoin.CounterCompleterQuickSort;
import com.mz.concurrency.main.impl.forkjoin.ForkJoinQuickSort;
import com.mz.concurrency.main.impl.recursive.RecursiveQuickSort;

public class Main {
	private static boolean PRINT_ARRAY = false;
	
	
	public static void main(String[] args) {
		Integer[] intArray = IntegerArrayGenerator.generateRandom(1000000, 2000);
		sort(Arrays.copyOf(intArray, intArray.length), new RecursiveQuickSort());
		sort(Arrays.copyOf(intArray, intArray.length), new CounterCompleterQuickSort());
		sort(Arrays.copyOf(intArray, intArray.length), new ForkJoinQuickSort());
	}

	private static long sort(Integer[] intArray, QuickSort sorter) {
		printArray("Before sort", intArray);
		long start = System.nanoTime();
		sorter.sort(intArray);
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
