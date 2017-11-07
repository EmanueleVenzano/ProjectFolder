import java.util.ArrayList;

public class prove {
	public static void main (String[] args) {
		String a = "uno";
		String b = "due";
		ArrayList <String> c = new ArrayList <String>();
		c.add(a);
		c.add(b);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		invertString(a,b);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		invertArr(c);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
	
	public static void invertArr (ArrayList<String> f) {
		invertString(f.get(0),f.get(1));
	}
	
	public static void invertString (String a, String b) {
		String temp;
		temp=a;
		a=b;
		b=temp;
	}

}