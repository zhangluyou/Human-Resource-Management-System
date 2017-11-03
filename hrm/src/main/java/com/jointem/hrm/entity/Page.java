package com.jointem.hrm.entity;

import java.util.List;

public class Page<T> {
    
	private int pageNum=1;//当前页
	
	private int pageSize;//每页记录条数
	
	private int totalCount;//总条数
	
	private int totalPageNum;//总页数
	
	private List<T> t;//当前页需要数据

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public List<T> getT() {
		return t;
	}

	public void setT(List<T> t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", totalPageNum="
				+ totalPageNum + ", t=" + t + "]";
	}
	
	
}
