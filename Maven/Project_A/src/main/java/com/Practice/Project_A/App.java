package com.Practice.Project_A;

//import com.Practice.Project_B.Printer;

public class App 
{
	public static void main(String[] args) {
		System.out.println("Call print method in Project_B");
		callMethod();
	}
	
	public static void callMethod(){
		Printer printer = new Printer();
		printer.printMethod();
	}
}
