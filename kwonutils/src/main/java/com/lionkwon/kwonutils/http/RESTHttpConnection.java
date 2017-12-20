package com.lionkwon.kwonutils.http;

import java.io.*;
import java.net.ProtocolException;
import java.security.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.conn.scheme.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.cookie.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.params.*;
import org.apache.http.protocol.*;

import com.lionkwon.kwonutils.log.*;


/**
 * @class RESTHttpConnection
 * @brief REST 서버에 URLConnection으로 접속하여 요청\n
 *        응답 메시지를 처리하는 클래스(Get,Post,Put,Delete)
 * @author 
 *
 */
public class RESTHttpConnection {

	private BufferedInputStream bin = null; ///< inputstream
	private BufferedReader rin = null; ///< inputstream
	private HttpClient httpClient = null;
	private HttpResponse httpResponse = null;
	private HttpPost httpPost = null;
	private HttpPut httpPut = null;
	private HttpGet httpGet = null;
	private HttpDelete httpDelete = null;
	private String urlString = null;
	private long contentLength = 0;
	private final HttpContext httpcontext = new BasicHttpContext();
	/**
	 *
	 * 접속할 http url 정보를 인자로 받아 HttpURLConnection 객체를 생성하는\n
	 * RESTHttpConnection 생성자.
	 * @param urlString 접속할 http url 문자열
	 * @throws IOException 잘못된 URL, 서버와 접속을 못 했을 경우 예외발생
	 */
	public RESTHttpConnection(String urlString) throws Exception {
		this.urlString = urlString;
		if(urlString.startsWith("https")) {
			httpClient = new DefaultHttpClient();
//			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//	        trustStore.load(null, null);
//	        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
//	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//	        Scheme https = new Scheme("https", sf, 443);
//	        httpClient.getConnectionManager().getSchemeRegistry().register(https);
//
//	        httpcontext.setAttribute(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//	        httpcontext.setAttribute(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
//	        httpcontext.setAttribute(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HTTP.UTF_8);
	        Logger.debug("https 로 요청을 했네요!!!!!!!!");
		}else{
			httpClient = new DefaultHttpClient();
		}

	}

	/**
	 * HTTP METHOD를 설정하는 함수.
	 * @param method GET,POST,PUT,DELETE
	 * @throws ProtocolException 잘못된 메소드인 경우 예외 발생.
	 */
	public void setMethod(String method) throws ProtocolException {
		if(method.equals("DELETE")) {
			httpDelete = new HttpDelete(urlString);
		}else if(method.endsWith("POST")){
			httpPost = new HttpPost(urlString);
		}else if(method.equals("PUT")) {
			httpPut = new HttpPut(urlString);
		}
	}

	/**
	 * Connetion TimeOut을 설정하는 함수.
	 * @param time 대기시간(1000이 1초)
	 */
	public void setTimeOut(int time) {
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, time);
		HttpConnectionParams.setSoTimeout(params, time);
	}


	/**
	 * HTTP Header를 설정하는 함수.
	 * @param name HTTP Header 명
	 * @param value HTTP Header 값.
	 */
	public void setHttpHeader(String name,String value) {
		if(httpPost != null) {
			httpPost.addHeader(name, value);
		}else if(httpPut != null) {
			httpPut.addHeader(name, value);
		}else if(httpDelete != null) {
			httpDelete.addHeader(name, value);
		}else{
			if(httpGet == null){
				httpGet = new HttpGet(urlString);
			}
			httpGet.addHeader(name, value);
		}
	}

	/**
	 * URLConnection의 Stream 제어 여부를 설정하는 함수.
	 * @param input 입력스트림 제어 여부
	 * @param output 출력스트림 제어 여부
	 */
	@Deprecated
	public void setInOutput(boolean input, boolean output) {

	}

	public List<Cookie> getCookie(){
		return ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
	}

	public CookieStore getCookieStore(){
		return ((AbstractHttpClient) httpClient).getCookieStore();
	}

	/**
	 * 응답메시지의 Content 길이를 반환하는 함수.
	 * @return Content 길이 반환.
	 */
	public long getContentLength() {
		return contentLength;
	}

	private void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	/**
	 * HTTP Header Filed 를 반환하는 함수.
	 * @return Map<String,List<String>> Collection 반환.
	 */
	public Header[] getHttpHeader() {
		return httpResponse != null?httpResponse.getAllHeaders():null;
	}

	/**
	 * HTTP Header Value를 반환하는 함수.
	 * @param name 값을 가져올 HTTP Header Name
	 * @return HTTP Header Value 반환.
	 */
	public String getHttpHeader(String name) {
		return httpResponse != null?httpResponse.getFirstHeader(name).getValue():null;
	}

	public int getStatus() throws IOException {
		return httpResponse.getStatusLine().getStatusCode();
	}
	/**
	 * 서버로 Data를 보내는 함수(POST 방식)
	 * @param message 전송할 Data
	 * @throws IOException 서버와 접속이 끊어졌을 경우 예외발생.
	 */
	public void requestData(String message) throws Exception {
		//http contents 세팅
		ByteArrayEntity be = new ByteArrayEntity(message.getBytes());
		if(httpPost != null) {
			httpPost.setEntity(be);
		}else if(httpPut != null) {
			httpPut.setEntity(be);
		}
	}

	/**
	 * 서버로 Data를 보내는 함수(POST 방식)
	 * @param message 전송할 Data
	 * @throws IOException 서버와 접속이 끊어졌을 경우 예외발생.
	 */
	public void requestData(byte[] message) throws Exception {
		//http contents 세팅
		ByteArrayEntity be = new ByteArrayEntity(message);
		if(httpPost != null) {
			httpPost.setEntity(be);
		}else if(httpPut != null) {
			httpPut.setEntity(be);
		}
	}

	/**
	 * HTTP 데이타를 요청하는 함수
	 * @throws ClientProtocolException 잘못된 프로토콜일때 예외 발생
	 * @throws IOException 서버와 연결이 끊어지면 예외 발생
	 */
	public void sendExecute() throws ClientProtocolException, IOException {
		if(httpPost != null) {
			httpResponse = httpClient.execute(httpPost,httpcontext);
		}else if(httpPut != null) {
			httpResponse = httpClient.execute(httpPut,httpcontext);
		}else if(httpDelete != null) {
			httpResponse = httpClient.execute(httpDelete,httpcontext);
		}else {
			if(httpGet == null){
				httpGet = new HttpGet(urlString);
			}
			httpResponse = httpClient.execute(httpGet,httpcontext);
		}


	}

	/**
	 * BufferedInputStream을 가져오는 함수.
	 * @return BufferedInputStream 반환.
	 * @throws IOException
	 */
	public BufferedInputStream getBufferedInputStream() throws IOException {
		if(httpResponse == null) {
			return null;
		}
		HttpEntity entity = httpResponse.getEntity();

		setContentLength(entity.getContentLength());

		bin = new BufferedInputStream(entity.getContent());

		return bin;
	}
	/**
	 * 서버로 부터 데이터를 받는 함수(서버의 인코딩에 Type에 맞게 설정).
	 * @return 수신받은 데이타 반환.
	 * @throws IOException 서버와 접속이 끊어졌을 경우 예외발생.
	 */
	public String responseDataEncoding() throws Exception {
		return responseDataEncoding("UTF-8");
	}

	public String responseDataEncoding(String encoding) throws Exception {
		if(httpResponse == null) {
			return null;
		}

		String result = null;

		HttpEntity entity = httpResponse.getEntity();

		setContentLength(entity.getContentLength());

		/**
		 * 응답값 헤더 정보 확인
		 */
		Header[] headers = httpResponse.getAllHeaders();
		for(int i = 0 ; i < headers.length; i++){
//			Logger.debug("Header : "+headers[i].getName()+":"+headers[i].getValue());
		}

		if(entity != null) {
//			String contentType = httpResponse.getFirstHeader("Content-Type").getValue();
//			if(contentType == null || (contentType != null && contentType.toUpperCase().indexOf("UTF-8") != -1)) {
//				rin = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
//			}else{
//				rin = new BufferedReader(new InputStreamReader(entity.getContent(),"EUC-KR"));
//			}

			rin = new BufferedReader(new InputStreamReader(entity.getContent(),encoding));


			String line = "";
			StringBuffer sb = new StringBuffer();
			while((line=rin.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

			result = sb.toString();

			rin.close();
		}

		return result;

	}
}
