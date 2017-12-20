package com.lionkwon.kwonutils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.lionkwon.kwonutils.log.Logger;
import com.lionkwon.kwonutils.security.coder.BASE64Encoder;

/**
 * @class FileUtil
 * @brief 파일관련 함수를 모아 놓은 객체.
 *
 */
public final class FileUtil
{
	/**
	 * 파일을 byte(바이트)배열로 가져오는 함수
	 * @param fileName 절대경로를 포함한 파일명
	 * @return byte(바이트)배열 반환
	 */
	public final static byte[] load(String fileName)
	{
		try {	
			FileInputStream fin=new FileInputStream(fileName);
			return load(fin);
		}
		catch (Exception e) {
			Logger.error(e);
			return new byte[0];
		}
	}

	/**
	 * 파일을 byte(바이트)배열로 가져오는 함수
	 * @param file 파일 객체
	 * @return byte(바이트)배열 반환
	 */
	public final static byte[] load(File file)
	{
		try {	
			FileInputStream fin=new FileInputStream(file);
			return load(fin);
		}
		catch (Exception e) {
			Logger.error(e);
			return new byte[0];
		}
	}

	/**
	 * 파일 입력스트림으로 읽어들인 파일내용을 byte(바이트)배열로 가져오는 함수
	 * @param fin 파일 입력 스트림
	 * @return byte(바이트)배열 반환
	 */
	public final static byte[] load(FileInputStream fin)
	{
		byte readBuf[] = new byte[512*1024];
	
		try {	
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
			int readCnt = fin.read(readBuf);
			while (0 < readCnt) {
				bout.write(readBuf, 0, readCnt);
				readCnt = fin.read(readBuf);
			}
			
			fin.close();
			
			return bout.toByteArray();
		}
		catch (Exception e) {
			Logger.error(e);
			return new byte[0];
		}
	}
	/**
	 * 파일의 내용을 문자열로 가져오는 함수
	 * @param fileName 절대경로를 포함한 파일명
	 * @return 문자열 반환
	 */
	public final static String loadContent(String fileName) {
		String line = "";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.error(e);
		}catch (Exception e) {
			Logger.error(e);
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Logger.error(e);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public final static boolean isXMLFileName(String name)
	{
		if (name == null)
			return false;
		String lowerName = name.toLowerCase();
		return lowerName.endsWith("xml");
	}
	
	/**
	 * 디렉토리의 파일 리스트를 문자열 배열로 가져오는 함수
	 * @param path 절대경로(디렉토리)
	 * @return 파일 리스트를 담고있는 문자열 배열 반환.
	 */
	public static String[] getFileListString(String path) {
		File fdir = new File(path); //directory
		return fdir.list();
	}
	
	/**
	 * 디렉토리의 파일 리스트를 파일객체 배열로 가져오는 함수
	 * @param path 절대경로(디렉토리)
	 * @return 파일 객체 배열 반환
	 */
	public static File[] getFileList(String path) {
		File fdir = new File(path); //directory
		return fdir.listFiles();
	}
	
	/**
	 * 디렉토리의 파일 리스트에서 지정한 파일명 및 확장자를 가진 파일을 검색하는 함수
	 * @param dir 절대경로(디렉토리)
	 * @param name 검색할 파일명
	 * @param extendText 확장자
	 * @return 검색 조건에 일치하는 파일 반환
	 */
	public static File getFile(String dir,String name,String extendText) {
		File realFile = null;
		File fdir = new File(dir);
		if(fdir.isDirectory()) {
			for(File f : fdir.listFiles()){
				String[] fNames = f.getName().split("[.]");
				if(fNames[0].equalsIgnoreCase(name) && fNames[1].equalsIgnoreCase(extendText)) { //extendText --> default gif
					realFile = f; break;
				}
				
			}
		}
		
		return realFile;
	}
	
	/**
	 * 검색조건에 일치하는 파일을 BASE64로 인코딩하는 함수(부산은행)
	 * @param service GET 방식의 호출 URL 
	 * @param realPath 파일의 실제경로
	 * @return 검색조건에 일치하는 파일을 BASE64로 인코딩한 문자열 반환
	 */
	public static String[] getImage(String service,String realPath,String extendText){
		String message[] = new String[2];
		String name = "";
		String list[] = service.split("[/]");
		name = list[list.length-1];
		File fdir = new File(realPath+name+"."+extendText); //extendText --> default gif
		if(fdir != null && fdir.exists()){
			message[0] = name+"."+extendText;
			BASE64Encoder encoder = new BASE64Encoder();
			message[1] = encoder.encodeBuffer(load(fdir));
		}
		return message;
	}
	
	/**
	 * 지정한 디렉토리의 하위 디렉토리 까지 삭제하는 함수
	 * @param parentPath 절대경로(디렉토리)
	 */
	public static boolean deleteFolder(String parentPath) {
		boolean flag = false;
		try{
			File file = new File(parentPath);
			if(file != null && file.exists()) {
				String[] fnameList = file.list();
				int fcnt = fnameList.length;
				String childPath = "";
				for(int i=0; i < fcnt; i++) {
					childPath = parentPath+"/"+fnameList[i];
					File f = new File(childPath);
					if(!f.isDirectory()) {
						flag = f.delete();
					}else{
						deleteFolder(childPath);
					}
				}
				
				File f = new File(parentPath);
				flag = f.delete();
			}else{
				Logger.debug("deleteFolder NOT parentPath :: "+parentPath);
			}
		}catch(Exception e) {
			Logger.error(e);
		}
		
		return flag;
	}
	
	public static void main(String args[]) {
		String a = "D:/DEV/SOURCE/j2ee-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/bs-smartoffice/files/mail/kim";
		
		deleteFolder(a);
	}
}

