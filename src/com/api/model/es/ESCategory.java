package com.api.model.es;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ESCategory {
	//Name of a index which hold information of all categories
	//private static String parentCategory="categories";
	
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
		
	}
	
	public static void main(String[] args) {
		ESCategory esCategories = new ESCategory();
		List<Category> categories = new ArrayList<Category>();
		Category category1 = new Category();
		category1.setCategoryLabel("Mobiles");
		category1.setCategoryName("mobiles");
		List<FieldGroup> fg = new ArrayList<FieldGroup>();
		
		FieldGroup fg1 = new FieldGroup();
		fg1.setGroupLabel("Display");
		fg1.setGroupName("display");
		List<Field> fields = new ArrayList<Field>();
		Field f1 = new Field();
		f1.setFieldName("size");
		f1.setFieldLable("Display Size");
		f1.setDataType("Text");
		f1.setFilterable(1);
		
		Field f2 = new Field();
		f2.setFieldName("price");
		f2.setFieldLable("Price");
		f2.setDataType("int");
		f2.setFilterable(1);
		List<String> ranges = new ArrayList<String>();
		ranges.add("10000");
		ranges.add("15000");
		ranges.add("20000");
		f2.setRanges(ranges);
		
		fields.add(f1);
		fields.add(f2);
		fg1.setFields(fields);
		
		fg.add(fg1);
		category1.setFieldgroups(fg);
		categories.add(category1);
		
		esCategories.setCategories(categories);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(esCategories));
	}

}



class Category{
	private String categoryName;
	private String categoryLabel;
	List<FieldGroup> fieldgroups;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryLabel() {
		return categoryLabel;
	}
	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}
	public List<FieldGroup> getFieldgroups() {
		return fieldgroups;
	}
	public void setFieldgroups(List<FieldGroup> fieldgroups) {
		this.fieldgroups = fieldgroups;
	}
	
}

class FieldGroup{
	private String groupName;
	private String groupLabel;
	List<Field> fields;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupLabel() {
		return groupLabel;
	}
	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
}

class Field{
	private String fieldLable;
	private String fieldName;
	private String dataType;
	private Integer filterable;
	private List<String> ranges;
	public String getFieldLable() {
		return fieldLable;
	}
	public void setFieldLable(String fieldLable) {
		this.fieldLable = fieldLable;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Integer getFilterable() {
		return filterable;
	}
	public void setFilterable(Integer filterable) {
		this.filterable = filterable;
	}
	public List<String> getRanges() {
		return ranges;
	}
	public void setRanges(List<String> ranges) {
		this.ranges = ranges;
	}
	
}
