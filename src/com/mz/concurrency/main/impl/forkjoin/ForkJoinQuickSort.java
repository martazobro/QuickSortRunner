package com.mz.concurrency.main.impl.forkjoin;

import com.mz.concurrency.main.QuickSort;

public class ForkJoinQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] comparable) {
		new QuickSortAction<T>(comparable, 0, comparable.length).fork().join();
	}
}
