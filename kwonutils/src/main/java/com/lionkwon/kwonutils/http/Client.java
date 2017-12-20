package com.lionkwon.kwonutils.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * httpclient 객체
 * @author lionkwon
 */
public class Client {
	public static HttpClient httpclient;
	private Client(){
	}
	public static synchronized HttpClient getInstance(){
		if(httpclient == null) httpclient =  new DefaultHttpClient();
		return httpclient;
	}
}
