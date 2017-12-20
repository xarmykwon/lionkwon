package com.lionkwon.kwonutils.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @class Parser
 * @brief XML 노드를 분석하기 위해서 꼭 필요한 함수를 정의한 추상적인 객체.
 */
public abstract class Parser {
	
	public Parser()	{
		
	}

	
	public abstract Node parse(InputStream inStream) throws ParserException;

	/**
	 * parse (URL)
	 */
	public Node parse(URL locationURL) throws ParserException{
		try {
	 		HttpURLConnection urlCon = (HttpURLConnection)locationURL.openConnection();
			urlCon.setRequestMethod("GET");
			InputStream urlIn = urlCon.getInputStream();
			
			Node rootElem = parse(urlIn);
			
			urlIn.close();
			urlCon.disconnect();

			return rootElem;
			
		} catch (Exception e) {
			throw new ParserException(e);
		}
		
	}
	/**
	 * Parse File
	 * @param descriptionFile
	 * @return
	 * @throws ParserException
	 */
	public Node parse(File descriptionFile) throws ParserException{
		try {
			InputStream fileIn = new FileInputStream(descriptionFile);
			Node root = parse(fileIn);
			fileIn.close();
			return root;
			
		} catch (Exception e) {
			throw new ParserException(e);
		}
	}

	/**
	 * Parse Memory
	 * @param descr
	 * @return
	 * @throws ParserException
	 */
	public Node parse(String descr) throws ParserException {
		try {
			ByteArrayInputStream inputstream = new ByteArrayInputStream(descr.getBytes());
			Node root = parse(inputstream);
			return root;
		} catch (Exception e) {
			throw new ParserException(e);
		}
	}

}


