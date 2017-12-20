package com.lionkwon.kwonutils.observer;

public class kwonReqestInfomation extends kwonObserver{

	private String key ="";

	private Object obj = null;

	@Override
	public void setInterFace(String key) {
		this.key = key;
	}

	@Override
	public String getInterFace() {
		return key;
	}

	@Override
	public void excute() {
		notifyObservers(key);
	}

	@Override
	public Object getParameter() {
		return obj;
	}

	@Override
	public void setParameter(Object obj) {
		this.obj = obj;
	}

	@Override
	public String excuteResponse() {
		return notifyObserversResponse(key);
	}

}
