package com.syntask.sale.util;

import java.util.ArrayList;

public class Page {

	private int pageSize;
	private int currentPage;
	private int numOfPage;
	private int lastPage;
	private int firstPage;
	private ArrayList<Integer> tempPage;

	public Page(){
		pageSize = 5;
		currentPage = 1;
		firstPage = 1;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumOfPage() {
		return numOfPage;
	}

	public void setNumOfPage(int numOfPage) {
		this.numOfPage = numOfPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public ArrayList<Integer> getTempPage() {
		return tempPage;
	}

	public void setTempPage(ArrayList<Integer> tempPage) {
		this.tempPage = tempPage;
	}

}
