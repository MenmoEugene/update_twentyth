class Ticket implements Runnable
{
	private int tick = 100;
	Object obj = new Object();
	boolean flag = true;
	public void run()
	{
		if(flag)
		{
			while(true)
			{
				synchronized(obj)
				{
					show();
				}
			}
		}else
			while(true)
				show();
	}
	public synchronized void show()
	{
		synchronized(obj)
		{
			if(tick>0)
			{
				try{Thread.sleep(20);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName()+"code:"+tick--);
			}
		}
	}
}

class Deadlock
{
	public static void main(String[] args)
	{
		Ticket t = new Ticket();
		
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		try{Thread.sleep(10);}catch(Exception e){}
		t.flag = false;
		t2.start();
	}
}