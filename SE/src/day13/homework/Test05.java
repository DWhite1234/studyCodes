package day13.homework;

import java.io.IOException;

public class Test05 {
	public static void main(String[] args) {
		int a = -1;
		try{
			if(a>0){
				throw new RuntimeException("");
			}else if(a<0){
				throw new IOException("");
			}else{
				return ;
			}
		}catch(IOException ioe){
			System.out.println("IOException");
		}catch(Throwable e){
			System.out.println("Throwable");
		}finally{
			System.out.println("finally");
		}
	}
}