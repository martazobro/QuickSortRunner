package com.mz.concurrency.main.impl.recursive;

import com.mz.concurrency.main.QuickSort;

public class RecursiveQuickSort implements QuickSort {

	@Override
	public <T extends Comparable<T>> void sort(T[] comparable) {
		doSort(comparable, 0, comparable.length);
	}

	private <T extends Comparable<T>> void doSort(T[] array, int startIndx,
			int endIndx) {
		if (startIndx >= endIndx - 1) {
			return;
		}
		int i = splitInTwoParts(array, startIndx, endIndx);
		doSort(array, startIndx, i);
		doSort(array, i + 1, endIndx);
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
