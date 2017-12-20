package com.lionkwon.kwonutils.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class HttpPostCookie {
	
	public Context context;
	public CookieManager cookieManager;
	public String domain = "192.169.11.11";
	public String cookiestring="";
	public Cookie sessionCookie;
	public org.apache.http.client.CookieStore sessionCookieStore;
	
	
	public HttpPostCookie(Context context) {
		this.context = context;
		CookieSyncManager.createInstance(context);
		cookieManager = CookieManager.getInstance();
		CookieSyncManager.getInstance().startSync();
	}
	
	public void start() throws ClientProtocolException, IOException{
		
		String result = "";
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
		nameValuePairs.add(new BasicNameValuePair("josso_cmd", "login"));
		nameValuePairs.add(new BasicNameValuePair("josso_back_to", "http://192.168.0.1/webservice/302/A/01/01"));
		nameValuePairs.add(new BasicNameValuePair("josso_username","id"));
		nameValuePairs.add(new BasicNameValuePair("josso_password", "pw"));
		HttpParams params = new BasicHttpParams();
		HttpPost post = new HttpPost("http://192.168.0.1/josso/signon/usernamePasswordLogin.do");
		post.setParams(params);
		UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
		post.setEntity(ent);
		HttpResponse response = Client.getInstance().execute(post);
		HttpEntity resEntity = response.getEntity();
		if(resEntity!=null){
			//						Logger.error(""+EntityUtils.toString(resEntity));
		}

		// 쿠키 설정
		List<Cookie> cookies = ((AbstractHttpClient) Client.getInstance()).getCookieStore().getCookies();
		sessionCookieStore =  ((AbstractHttpClient) Client.getInstance()).getCookieStore();

		ArrayList<String> temp = new ArrayList<String>();
		if (!cookies.isEmpty()) {
			Cookie sessionInfo = null;
			for (Cookie cookie : cookies) {
				sessionInfo = cookie;
				if(sessionInfo!=null){

					if(sessionInfo.getName().equals("JSESSIONID")||sessionInfo.getName().equals("JOSSO_SESSIONID")){
						String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; path="+ sessionInfo.getPath();
						//									Logger.error("cookieString ---> "+cookieString);
						cookieManager.setCookie(sessionInfo.getDomain(),cookieString);
						CookieSyncManager.getInstance().sync();
						temp.add(cookieString);
						cookiestring += cookieString;
					}
					if(sessionInfo.getName().equals("JOSSO_SESSIONID")){
						result = "success";
					}
				}
			}
		}
	}
	
	public void fail(){
		if (CookieSyncManager.getInstance() != null) {
			//					CookieSyncManager.getInstance().stopSync();
			cookieManager.removeAllCookie();
			cookieManager.removeSessionCookie();
		}

	}
}
