package com.lionkwon.kwonutils.observer;

public class kwonObserverGenerator {
	private volatile static kwonObserverGenerator observer;
	private final kwonObserver logObserver;

	private kwonObserverGenerator(){
		logObserver = new kwonReqestInfomation();
	}

	public static kwonObserverGenerator getInstance(){
		if(observer == null){
			synchronized (kwonObserverGenerator.class) {
				if(observer == null)
					observer = new kwonObserverGenerator();
			}
		}
		return observer;
	}


	public boolean getIsAddObserver(String key) {
		return logObserver.getIsObserver(key);
	}
	public void addListener(String key, kwonObserverListener listener) {
		logObserver.addObserver(key,listener);
	}

	public void removeListener(String key){
		logObserver.deleteObserver(key);
	}

	public void setRequest(String key, Object message) {
		logObserver.setInterFace(key);
		logObserver.setParameter(message);
		logObserver.excute();
	}

	public Object setRequestWithResponse(String key, Object message) {
		logObserver.setInterFace(key);
		logObserver.setParameter(message);
		return logObserver.excuteResponse();
	}
}
