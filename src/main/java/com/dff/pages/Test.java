package com.dff.pages;

public class Test {

	public static void main(String[] args) {
		String S1 = "Vinay";
		String S2 = "Vinay";
		String S3 = new String("Vinay");
		String S4 = new String("Vinay");
		
		System.out.println(S1 == S2);
		System.out.println(S4 == S3);
		System.out.println(S1 == S3);
				
		System.out.println(S1.equals(S3));
		System.out.println(S4.equals(S3));

		/*
		
		try {
			int i = 5/0;
		}catch (ArithmeticException e) {
			System.out.println("ArithmeticException");
		}catch (Throwable e) {
			System.out.println("Throwable");
		}catch (Exception e) {
			System.out.println("Exception");
		}
		
		 */
	}

	/*	
	 
	Test obj = new Test();
	obj.sum(20,20);
	
	void sum(int a,long b){System.out.println("method 1 invoked");}  
	void sum(long a,int b){System.out.println("method 2 invoked");}  

	public static void main(String[] args){System.out.println("main with String[]");}  
	public static void main(String args){System.out.println("main with String");}  
	public static void main(){System.out.println("main without args");}  
*/	
	  // big black bug bit a big black dog on his big black nose  
}
