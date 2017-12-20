package com.lionkwon.kwonutils.observer;


public class kwonTest implements kwonObserverListener{
	static kwonTest it;

	public static void main(String[] args) {
		it = new kwonTest();
		//받는애
		kwonObserverGenerator.getInstance().addListener("test", it); // 리스너 등록

		// 쏘는애
		kwonObserverGenerator.getInstance().setRequest("test", "쏘자"); // 옵저버 쏘기.

		// 쏘고 답까지받는애
		String temp = (String) kwonObserverGenerator.getInstance().setRequestWithResponse("test", "쏘고 받자");
		System.out.println(temp);
	}

	@Override
	public void request(kwonObserver request) {
		System.out.println("request 받음");
		if(request.getInterFace().equals("test")) {
			String message = request.getParameter()+"";
			System.out.println(message);
		}
	}

	@Override
	public String requestWithResponse(kwonObserver request) {
		if(request.getInterFace().equals("test")) {
			String message = request.getParameter()+"";
			System.out.println(message);
		}
		return "좋냐";
	}

}
