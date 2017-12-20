package com.lionkwon.kwonutils.http;

import com.lionkwon.kwonutils.http.HttpDownloaderImpl.DownloadFileCompleted;
import com.lionkwon.kwonutils.http.HttpDownloaderImpl.OnDownloadFileCompletedListener;
import com.lionkwon.kwonutils.http.HttpDownloaderImpl.OnDownloadProgressChangedListener;


public class httpDownloaderTest {
	public static void main(String[] args) {
		
		HttpDownloader downloader = new HttpDownloader();
		downloader.setOnDownloadFileCompletedListener(new OnDownloadFileCompletedListener() {
			@Override
			public void onDownloadFileCompleted(DownloadFileCompleted event) {
			}
		});

		downloader.setOnDownloadProgressChangedListener(new OnDownloadProgressChangedListener() {
			@Override
			public void onDownloadProgressChanged(long bytesReceived, long totalBytesReceived) {
			}
		});

		downloader.downloadFileAsync("url"	, "filename", null);
	}
}
