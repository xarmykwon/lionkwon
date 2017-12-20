package com.lionkwon.kwonutils.http;

import android.os.AsyncTask;

import com.lionkwon.kwonutils.log.Logger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 
 * @FileName  : HttpDownloader.java
 * @Project     : KwonUtils
 * @Date         : 2014. 1. 3. 
 * @작성자      : lionkwon
 * @변경이력 :
 * @프로그램 설명 :
 */
public class HttpDownloader implements HttpDownloaderImpl {
	
	private OnDownloadFileCompletedListener mOnDownloadFileCompletedListener;
	private OnDownloadProgressChangedListener mOnDownloadProgressChangedListener;
	private OnDownloadStringCompletedListener mOnDownloadStringCompletedListener;

	@Override
	public void downloadFileAsync(String address, String fileName,
			ArrayList<NameValuePair> nameValuePairs) {
		new DownloadFileAsyncTask(nameValuePairs).execute(address , fileName);
	}

	private class DownloadFileAsyncTask extends AsyncTask<String , Long , DownloadFileCompleted> {

		static final int MAX_BUFFER_SIZE = 4096;
		private final WeakReference<ArrayList<NameValuePair>> nameValuePairs;

		DownloadFileAsyncTask(ArrayList<NameValuePair> nameValuePairs) {
			this.nameValuePairs = new WeakReference<ArrayList<NameValuePair>>(nameValuePairs);
		}

		@Override
		protected DownloadFileCompleted doInBackground(String... params) {
			Logger.error("Download doInBakground..");
			DownloadFileCompleted event = new DownloadFileCompleted();

			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				// org.apache.commons.httpclient.HttpClient

				// org.apache.http.client.HttpClient
				DefaultHttpClient client = new DefaultHttpClient();
				HttpResponse response;
				if (nameValuePairs.get() == null) {
					Logger.error("GET");
					HttpGet get = new HttpGet(params[0]);
					response = client.execute(get);
				} else {
					Logger.error("POST");
					HttpPost post = new HttpPost(params[0]);
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs.get()));
					response = client.execute(post);
				}

				Header[] headers  = response.getAllHeaders();
				for(int i = 0; i < headers.length ; i++){
					Logger.info("header => "+headers[i].getValue());
				}

				final HttpEntity entity = response.getEntity();

				bis = new BufferedInputStream( entity.getContent());

				File f = new File(params[1]);
//				Logger.error("file 만들기 ; "+f.getAbsolutePath());
//				Logger.error("file 만들기 ; "+f.getName());

				if (f.exists() == false)
					f.createNewFile();
				bos = new BufferedOutputStream(new FileOutputStream(f));

				long totalBytes = entity.getContentLength();
				long readBytes = 0;

				byte[] buffer = new byte[MAX_BUFFER_SIZE];
				while(totalBytes > readBytes) {
					int read = bis.read(buffer);
					readBytes += read;
					publishProgress(readBytes , totalBytes);
					bos.write(buffer, 0, read);
//					Logger.error("Download now downloading.."+read);
				}
				bos.flush();
				event.file = f;
			} catch (IOException e) {
				Logger.error(e);
				event.exception = e;
			} finally {
				try {
					if (bos != null) bos.close();
					if (bis != null) bis.close();
				} catch (IOException e) {}
			}
			return event;
		}

		@Override
		protected void onProgressUpdate(Long... values) {
			invokeOnDownloadProgressChangedListener(values[0] , values[1]);
		}

		@Override
		protected void onPostExecute(DownloadFileCompleted result) {
			Logger.error("Download onPostExecute..");
			invokeOnDownloadFileCompletedListener(result);
		}
	}

	@Override
	public String downloadString(String address,
			ArrayList<NameValuePair> nameValuePairs) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request;
		if (nameValuePairs == null) {
			request = new HttpGet(address);
		} else {
			HttpPost post = new HttpPost(address);
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			request = post;
		}
		ResponseHandler<String> handler = new BasicResponseHandler();
		return client.execute(request , handler);
	}

	@Override
	public void downloadStringAsync(String address,
			ArrayList<NameValuePair> nameValuePairs) {
		new DownloadStringAsyncTask (nameValuePairs).execute(address);
	}

	private class DownloadStringAsyncTask extends AsyncTask<String , Void , DownloadStringCompleted> {
		private final WeakReference<ArrayList<NameValuePair>> nameValuePairs;

		DownloadStringAsyncTask (ArrayList<NameValuePair> nameValuePairs) {
			this.nameValuePairs = new WeakReference<ArrayList<NameValuePair>>(nameValuePairs);
		}

		@Override
		protected DownloadStringCompleted doInBackground(String... params) {
			DownloadStringCompleted event = new DownloadStringCompleted();
			try {
				event.text = downloadString(params[0] , nameValuePairs.get());
			} catch (IOException e) {
				event.exception = e;
			}
			return event;
		}

		@Override
		protected void onPostExecute(DownloadStringCompleted result) {
			invokeOnDownloadStringCompletedListener(result);
		}
	}

	@Override
	public void setOnDownloadFileCompletedListener(
			OnDownloadFileCompletedListener listener) {
		mOnDownloadFileCompletedListener = listener;
	}

	void invokeOnDownloadFileCompletedListener(DownloadFileCompleted event) {
		if (mOnDownloadFileCompletedListener != null) {
			mOnDownloadFileCompletedListener.onDownloadFileCompleted(event);
		}
	}

	@Override
	public void setOnDownloadProgressChangedListener(
			OnDownloadProgressChangedListener listener) {
		mOnDownloadProgressChangedListener = listener;
	}

	void invokeOnDownloadProgressChangedListener(long bytesReceived , long totalBytesReceived) {
		if (mOnDownloadProgressChangedListener != null) {
			mOnDownloadProgressChangedListener.onDownloadProgressChanged(bytesReceived, totalBytesReceived);
		}
	}

	@Override
	public void setOnDownloadStringCompletedListener(
			OnDownloadStringCompletedListener listener) {
		mOnDownloadStringCompletedListener = listener;
	}

	void invokeOnDownloadStringCompletedListener(DownloadStringCompleted event) {
		if (mOnDownloadStringCompletedListener != null) {
			mOnDownloadStringCompletedListener.onDownloadStringCompleted(event);
		}
	}
}
