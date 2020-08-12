class thread 
{ 
	static void sleep(long millis) 
	{ 
		try
		{ 
			Thread.sleep(millis); 
		} 
		catch (InterruptedException e) 
		{ 
			e.printStackTrace(); 
		} 
	} 
} 

class Sha 
{ 
	synchronized void test1(Shared s2) 
	{ 
		System.out.println("First Begin"); 
		thread.sleep(1000); 
    	s2.test2(this); 
		System.out.println("First End"); 
	} 

	synchronized void test2(Shared s1) 
	{ 
		System.out.println("Second Begin"); 
		thread.sleep(1000); 

		s1.test1(this); 
		System.out.println("Second End"); 
	} 
} 


class Thread1 extends Thread 
{ 
	private Sha s1; 
	private Sha s2; 

	public Thread1(Sha s1, Sha s2) 
	{ 
		this.s1 = s1; 
		this.s2 = s2; 
	} 

	
	public void run() 
	{ 
		s1.test1(s2); 
	} 
} 


class Thread2 extends Thread 
{ 
	private Shared s1; 
	private Shared s2; 
    
    public Thread2(Sha s1, Sha s2) 
	{ 
		this.s1 = s1; 
		this.s2 = s2; 
	} 

	
	public void run() 
	{ 
		s2.test2(s1); 
	} 
} 


public class Main 
{ 
	public static void main(String[] args) 
	{ 
		Sha s1 = new Sha(); 

		Sha s2 = new Sha(); 

		Thread1 t1 = new Thread1(s1, s2); 
		t1.start(); 

		Thread2 t2 = new Thread2(s1, s2); 
		t2.start(); 

		thread.sleep(2000); 
	} 
}

