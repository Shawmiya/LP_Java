package com.virtusa_2.Project_2;

import com.virtusa.HelloSpringApp.HelloSpring;

public class ContinueSpring {
	
	public static void main(String[] args) {
		System.out.println("Your Spring code is working");
		callMethod();
	}
	
	public static void callMethod(){
		HelloSpring helloSpring = new HelloSpring();
		helloSpring.printMethod();
	}
}
