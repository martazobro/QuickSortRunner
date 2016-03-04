package com.mz.concurrency.main.impl.forkjoin;

import com.mz.concurrency.main.QuickSort;

public class CounterCompleterQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] array) {
		new QuickSortCounterCompleter<T>(null, array, 0, array.length).invoke();
	}

}
