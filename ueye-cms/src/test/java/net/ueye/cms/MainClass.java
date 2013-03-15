package net.ueye.cms;

/**
 * @author rubys
 * @since 2013-3-15
 */
public class MainClass {
	
	public static void main(String[] args) {
		
		String s = "s/";
		
		p(s.lastIndexOf("/"));
		
		p(s.charAt(s.length()-1));
		
	}
	
	static void p(Object value){
		System.out.println(value);
	}

}
