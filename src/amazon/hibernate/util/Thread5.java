package amazon.hibernate.util;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import amazon.hibernate.data.DBTable;

public class Thread5 extends Thread{

	static final int MAXQUEUE = 100;
	Session session = HibernateUtil.getSessionFactory().openSession();
	static Long MAX = (long) 10000;
	static Vector<DBTable> messages = new Vector();
	long date1 = System.currentTimeMillis();

	@Override
	public void run() {
		try {
			while (true) {
				putMessage();
				//sleep(5000);
			}
		} catch (InterruptedException e) {
		}
	}

	private synchronized void putMessage() throws InterruptedException {

		System.out.println("Start time: "+ date1);
		Query query1 = session.createQuery("select count(*) from DBTable");
		List<?> list1 = query1.list();
		//MAX = (Long) list1.get(0);
		MAX = (long) 10000;
		Long min = (long) 0;
		Long max = (long) 100;

		while(true){

			while (messages.size() == MAXQUEUE) {
				wait();
				//System.out.println("Hi");
			}

			if(max != MAX){
				Query query = session.createQuery("from DBTable where id between :min and :max");
				query.setParameter("min", min);
				query.setParameter("max", max);
				//System.out.println("Min 2"+min);
				//System.out.println("Max 2"+max);
				List<?> list = query.list();	
				//System.out.println(list.size());
				for (int i = 0; i < list.size(); i++) {
					DBTable dbTable = (DBTable) list.get(i);
					messages.addElement(dbTable);
					//System.out.println(dbTable.getTitle()+i);
				}
			}else{
				messages = null;
				//break;
				//System.exit(0);
			}
			min = max+1;
			max = max + 100;
			//System.out.println("Min 1"+min);
			//System.out.println("Max 1"+max);
			if(max>=MAX){
				max = MAX;
			}
			//System.out.println("put message");
			notify();
		}
		//Later, when the necessary event happens, the thread that is running it calls notify() from a block synchronized on the same object.
	}

	// Called by Consumer
	public synchronized Vector<DBTable> getMessage() throws InterruptedException {
		notify();
		while (messages.size() == 0) {
			//System.out.println("Hi there");
			wait();//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.
		}
		Vector<DBTable> message = messages;
		messages=null;
		messages = new Vector<>();
		if(messages==null){
			return null;
		}
		return message;
	}

}
