package com.davydov.util;

public class PageableUtil {
  private String sortBy;
  private String orderBy;
  private Integer page;
  private Integer size;

  public PageableUtil() {

  }

  public PageableUtil(String sortBy, String orderBy, int page, int size) {
    this.sortBy = sortBy;
    this.orderBy = orderBy;
    this.page = page;
    this.size = size;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
