package com.lionkwon.kwonutils.observer;

import java.util.HashMap;
import java.util.Map;
public abstract class kwonObserver {

	private final Map observers;
	// key :: interface,(address,byte[])

	public kwonObserver() {
		observers = new HashMap();
	}

	public void addObserver(String key,Object obj) {
		observers.put(key, obj);
	}

	public void deleteObserver(String key) {
		observers.remove(key);
	}

	public void notifyObservers(String key) {
		Object obj = observers.get(key);
		if(obj != null) {
			kwonObserverListener listener = (kwonObserverListener) observers.get(key);
			listener.request(this);
		}
	}

	public String notifyObserversResponse(String key) {
		Object obj = observers.get(key);
		if(obj != null) {
			kwonObserverListener listener = (kwonObserverListener) observers.get(key);
			return listener.requestWithResponse(this);
		}

		return null;
	}
	public boolean getIsObserver(String key) {
		Object obj = observers.get(key);
		return obj != null;
	}
	public abstract void setInterFace(String key);

	public abstract String getInterFace();

	public abstract void setParameter(Object message);

	public abstract Object getParameter();

	public abstract void excute();

	public abstract Object excuteResponse();
}
