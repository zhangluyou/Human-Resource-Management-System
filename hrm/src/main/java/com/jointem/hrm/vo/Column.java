package com.jointem.hrm.vo;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private String type;
	private String name;
	private int[] data;
	
	public static List<Column> getColumns(){
		List<Column> cList = new ArrayList<>();
		cList.add(new Column("column","好"));
		cList.add(new Column("column","中"));
		cList.add(new Column("column","差"));
		cList.add(new Column("spline","总次数"));
		return cList;
	}
	
	public Column(){
		
	}
	
	public Column(String type,String name){
		this.type=type;
		this.name=name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getData() {
		return data;
	}
	public void setData(int[] data) {
		this.data = data;
	}
}
