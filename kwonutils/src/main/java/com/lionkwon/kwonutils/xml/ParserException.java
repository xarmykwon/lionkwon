package com.lionkwon.kwonutils.xml;

/**
 * @class ParserException
 * @brief XML 노드를 분석 실패 시 에러 메시지를 전달하는 객체.
 */
public class ParserException extends Exception{
	private static final long serialVersionUID = -7952833319822793151L;

	public ParserException(Exception e)	{
		super(e);
	}
	
	public ParserException(String s){
		super(s);
	}
}