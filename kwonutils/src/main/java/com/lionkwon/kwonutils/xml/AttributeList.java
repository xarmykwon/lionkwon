package com.lionkwon.kwonutils.xml;

import java.util.Vector;

/**
 * @class AttributeList
 * @brief XML 노드의 Attribute 정보들을 Vector Collection 저장하는 객체.
 */
public class AttributeList extends Vector {
	private static final long serialVersionUID = 2825992612439852018L;

	public AttributeList() {
	}
	
	public Attribute getAttribute(int n){
		return (Attribute)get(n);
	}
	
	public Attribute getAttribute(String name) {
		if (name == null)
			return null;
		
		int nLists = size(); 
		for (int n=0; n<nLists; n++) {
			Attribute elem = getAttribute(n);
			if (name.compareTo(elem.getName()) == 0)
				return elem;
		}
		return null;
	}
}

