package com.mz.concurrency.main.impl.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.function.Supplier;

public class QuickSortAction<T extends Comparable<T>> extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	private T[] array;
	private int startIndx;
	private int endIndx;
	private Supplier postAction;

	public QuickSortAction(T[] array, int startIndx, int endIndx, Supplier postAction2) {
		this.array = array;
		this.startIndx = startIndx;
		this.endIndx = endIndx;
		this.postAction = postAction2;
	}

	@Override
	protected void compute() {
		if (startIndx >= endIndx - 1) {
			return;
		}
		int i = splitInTwoParts(array, startIndx, endIndx);
		QuickSortAction<T> quickSortPart1 = null, quickSortPart2 = null;
		if (i-startIndx>1){
			quickSortPart1 = new QuickSortAction<T>(array, startIndx, i, postAction);
			quickSortPart1.fork();
		}
		if (endIndx - i-1 > 1){
			quickSortPart2 = new QuickSortAction<T>(array, i + 1, endIndx, postAction);
			quickSortPart2.fork();
			quickSortPart2.join();
		}
		if (quickSortPart1 != null){
			quickSortPart1.join();
		}
		if (postAction != null){
			postAction.get();
		}
	}

	private int splitInTwoParts(T[] array, int startIndx, int endIndx) {
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

	private void swap(T[] array, int indx1, int indx2) {
		if (indx1 != indx2) {
			T indx1Item = array[indx1];
			array[indx1] = array[indx2];
			array[indx2] = indx1Item;
		}
	}
}
