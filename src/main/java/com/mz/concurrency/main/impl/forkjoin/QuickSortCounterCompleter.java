package com.mz.concurrency.main.impl.forkjoin;

import java.util.concurrent.CountedCompleter;
import java.util.function.Supplier;

public class QuickSortCounterCompleter<T extends Comparable<T>> extends
		CountedCompleter<Void> {

	private static final long serialVersionUID = 1L;
	private T[] array;
	private int startIndx;
	private int endIndx;
	private Supplier postProcess;

	public QuickSortCounterCompleter(CountedCompleter<Void> parentCompleter, T[] array, int startIndx, int endIndx, Supplier postProcess2) {
		super(parentCompleter);
		this.array = array;
		this.startIndx = startIndx;
		this.endIndx = endIndx;
		this.postProcess = postProcess2;
	}

	@Override
	public void compute() {
		if (startIndx >= endIndx - 1) {
			tryComplete();
			return;
		}
		int i = splitInTwoParts(array, startIndx, endIndx);
		QuickSortCounterCompleter<T> quickSortCounterCompleter1 = null, quickSortCounterCompleter2 = null;
		if (i-startIndx > 1){
			addToPendingCount(1);
			quickSortCounterCompleter1 = new QuickSortCounterCompleter<T>(this, array,	startIndx, i, postProcess);
		}
		if (endIndx - i- 1 > 1){	
			addToPendingCount(1);
			quickSortCounterCompleter2 = new QuickSortCounterCompleter<T>(this, array, i + 1, endIndx, postProcess);
		}
		if (quickSortCounterCompleter1 !=null)quickSortCounterCompleter1.fork();
		if (quickSortCounterCompleter2 !=null)quickSortCounterCompleter2.fork();

		tryComplete();
		if (postProcess != null){
			postProcess.get();
		}
	}

	@Override
	public boolean onExceptionalCompletion(Throwable ex,
			CountedCompleter<?> caller) {
		System.out.println(ex.toString());
		return super.onExceptionalCompletion(ex, caller);
	}
	
	@Override
	public void onCompletion(CountedCompleter<?> caller) {
//		System.out.println("Done!");
		super.onCompletion(caller);
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
