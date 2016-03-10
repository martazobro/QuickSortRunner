package com.mz.concurrency.main.impl.recursive;

import java.util.function.Supplier;

import com.mz.concurrency.main.QuickSort;

public class RecursiveQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] comparable, Supplier postProcess) {
		doSort(comparable, 0, comparable.length, postProcess);
	}

	private <T extends Comparable<T>> void doSort(T[] comparable, int startIndx,
			int endIndx, Supplier postProcess) {
		if (startIndx >= endIndx - 1) {
			return;
		}
		int i = splitInTwoParts(comparable, startIndx, endIndx);
		doSort(comparable, startIndx, i, postProcess);
		doSort(comparable, i + 1, endIndx, postProcess);
		if (postProcess != null){
			postProcess.get();
		}
	}

	private <T extends Comparable<T>> int splitInTwoParts(T[] array,
			int startIndx, int endIndx) {
		T pivot = array[endIndx - 1];
		int i = startIndx;
		for (int j = startIndx; j < endIndx - 1; j++) {
			if (array[j].compareTo(pivot) < 1) {
				swap(array, i++, j);
			}
		}
		swap(array, i, endIndx - 1);
		return i;
	}

	private <T extends Comparable<T>> void swap(T[] array, int indx1, int indx2) {
		if (indx1 != indx2) {
			T indx1Item = array[indx1];
			array[indx1] = array[indx2];
			array[indx2] = indx1Item;
		}
	}

}
