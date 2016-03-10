package com.mz.concurrency.main;

import java.util.function.Supplier;

public interface QuickSort {

	<T extends Comparable<T>> void sort(T[] intArray, Supplier consumer);
}
