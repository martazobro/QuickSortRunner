package com.mz.concurrency.main.impl.forkjoin;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mz.concurrency.main.QuickSort;

public class ForkJoinQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] comparable, Supplier postAction) {
		new QuickSortAction<T>(comparable, 0, comparable.length, postAction).fork().join();
	}

}
