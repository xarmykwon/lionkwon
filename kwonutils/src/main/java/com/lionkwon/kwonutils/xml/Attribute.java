package com.lionkwon.kwonutils.xml;

/**
 * @class Attribute
 * @brief XML 노드의 Attribute 정보를 담고있는 객체.
 */
public class Attribute {
	private String name = ""; 
	private String value = ""; 

	public Attribute(){
	}

	public Attribute(String name, String value){
		setName(name);
		setValue(value);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

