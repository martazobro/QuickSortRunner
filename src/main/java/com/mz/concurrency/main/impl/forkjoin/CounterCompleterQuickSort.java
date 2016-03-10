package com.mz.concurrency.main.impl.forkjoin;

import java.util.function.Supplier;

import com.mz.concurrency.main.QuickSort;

public class CounterCompleterQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] array, Supplier postProcess) {
		QuickSortCounterCompleter<T> counterCompleter = new QuickSortCounterCompleter<T>(null, array, 0, array.length, postProcess);
		counterCompleter.invoke();
	}

}
