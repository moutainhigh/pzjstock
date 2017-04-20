package com.pzj.core.product.entity;

public class PageEntity implements java.io.Serializable {

	private static final long serialVersionUID = 7950089588348562489L;

	private final int currentPage;

	private final int pageSize;

	private int startIndex;

	private String sortField;

	private static final int defaultCurPage = 1;

	private static final int defaultPageSize = 20;

	public PageEntity() {
		this.currentPage = defaultCurPage;
		this.pageSize = defaultPageSize;
		initPageIndex();
	}

	public PageEntity(int currentPage, int pageSize) {
		this.currentPage = currentPage <= 0 ? defaultCurPage : currentPage;
		this.pageSize = pageSize <= 0 ? defaultPageSize : pageSize;
		initPageIndex();
	}

	private void initPageIndex() {
		startIndex = (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
