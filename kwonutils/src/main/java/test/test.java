package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lionkwon.kwonutils.http.RESTHttpConnection;
import com.lionkwon.kwonutils.log.Logger;
import com.lionkwon.kwonutils.xml.Node;
import com.lionkwon.kwonutils.xml.kXML2Parser;

public class test {

	public static void main(String[] args) throws Exception {

		String result="";
		String result_msg="";
		
		RESTHttpConnection restCon = new RESTHttpConnection("http://");
		restCon.setTimeOut(0);
		restCon.setMethod("POST");
		restCon.setInOutput(true, true);
		restCon.setHttpHeader("Content-Type", "application/x-www-form-urlencoded");
		restCon.requestData("device_uuid=3");
		restCon.sendExecute();
		String responseString = restCon.responseDataEncoding();
		Logger.debug(responseString);

		if(restCon.getStatus()==200){
			InputStream in = null;
			try{
				in = new ByteArrayInputStream(responseString.getBytes("UTF-8"));
				Node node = new kXML2Parser().parse(in);
				for (int i = 0; i < node.getNNodes(); i++) {
					Node n = node.getNode(i);
					if (n.getName().equals("result")) {
						result = n.getValue();
					}else if(n.getName().equals("result_msg")){
						result_msg = n.getValue();
					}
				}
			} catch (Exception e) {
				Logger.error(e);
				result = "fail";
				result_msg = "잠시 후 다시 시도해주세요";
			} finally{
				if(in != null) try {in.close();} catch (Exception e) {}
			}
		}else{
			result = "fail";
		} 
	}

}
