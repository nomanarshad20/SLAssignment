package com.system.test.assignment.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltersDTO {

	private Integer start;

	private Integer length;

	private Integer displayStart = 0;

	private Long totalRecords;

	private List<Object> data;

	public FiltersDTO() {

	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getDisplayStart() {
		return displayStart;
	}

	public void setDisplayStart(Integer displayStart) {
		this.displayStart = displayStart;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

}
