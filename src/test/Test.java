package test;

import readwrite.Read;

public class Test {

	public static void main(String[] args){
		Read r=new Read();
		String word="LOGGER";
		String rooot="C:\\wgit_pyme1\\webpymes\\pyme_2_0\\pyme-backend";
		System.out.println("branch");
		System.out.println("feture f2");
		try {
			r.listFiles(rooot, word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
