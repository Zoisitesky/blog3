package com.jdch.blog3.util;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{
	private int total; // 总记录数
	private int pages; // 总页数
	private List<T> data; // 当前页面的数据
	private int pageNow; //当前页数
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}


	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	@Override
	public String toString() {
		return "PageBean [total=" + total + ", pages=" + pages + ", data=" + data + ", pageNow=" + pageNow + "]";
	}

	public PageBean() {
		super();
	}

	public PageBean(int total, int pages, List<T> data, int pageNow) {
		super();
		this.total = total;
		this.pages = pages;
		this.data = data;
		this.pageNow = pageNow;
	}
}
