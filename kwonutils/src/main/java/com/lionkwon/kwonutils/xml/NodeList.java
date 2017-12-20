package com.lionkwon.kwonutils.xml;

import java.util.Vector;

/**
 * @class NodeList
 * @brief XML 노드 정보들을 Vector Collection에 저장하는 객체.
 */
public class NodeList extends Vector {
	private static final long serialVersionUID = 243838835654524760L;

	public NodeList() {
	}
	
	public Node getNode(int n){
		return (Node)get(n);
	}

	public Node getNode(String name) {
		if (name == null)
			return null;
		
		int nLists = size(); 
		for (int n=0; n<nLists; n++) {
			Node node = getNode(n);
			String nodeName = node.getName();
			if (name.compareTo(nodeName) == 0)
				return node;
		}
		return null;
	}

	public Node getEndsWith(String name) {
		if (name == null)
			return null;

		int nLists = size(); 
		for (int n=0; n<nLists; n++) {
			Node node = getNode(n);
			String nodeName = node.getName();
			if (nodeName == null)
				continue;
			if (nodeName.endsWith(name) == true)
				return node;
		}
		return null;
	}
}

