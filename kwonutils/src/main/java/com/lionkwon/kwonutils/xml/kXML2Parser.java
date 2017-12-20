package com.lionkwon.kwonutils.xml;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;

/**
 * @class kXML2Parser
 * @brief XML 노드를 분석하는 객체.
 */
public class kXML2Parser extends Parser
{
	////////////////////////////////////////////////
	//	Constructor
	////////////////////////////////////////////////

	private String ENCODING_SYLE = "UTF-8";

	public kXML2Parser()
	{
		
	}

	public kXML2Parser(String encoding)
	{
		ENCODING_SYLE = encoding;
	}

	////////////////////////////////////////////////
	//	parse
	////////////////////////////////////////////////

	public Node parse(InputStream inStream) throws ParserException
	{
		Node rootNode = null;
		Node currNode = null;
		
		try {
			InputStreamReader inReader = new InputStreamReader(inStream,ENCODING_SYLE);
			XmlPullParser xpp = new KXmlParser();
			xpp.setInput(inReader);
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					{
						Node node = new Node();
						String nodeName = xpp.getName();
						node.setName(nodeName);
						int attrsLen = xpp.getAttributeCount();
						for (int n=0; n<attrsLen; n++) {
							String attrName = xpp.getAttributeName(n);
							String attrValue = xpp.getAttributeValue(n);
							node.setAttribute(attrName, attrValue);
						}
					
						if (currNode != null)
							currNode.addNode(node);
						currNode = node;
						if (rootNode == null)
							rootNode = node;
					}
					break;
				case XmlPullParser.TEXT:
					{
						String value = xpp.getText();
						if (currNode != null)
							currNode.setValue(value);
					}
					break;
				case XmlPullParser.END_TAG:
					{
						currNode = currNode.getParentNode();
					}
					break;
				}
				eventType = xpp.next();
			}
		}
		catch (Exception e) {
			throw new ParserException(e);
		}
		
		return rootNode;
	}
	
}

