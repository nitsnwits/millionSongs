package amazon.hibernate.util;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import amazon.hibernate.data.DBTable;

public class Thread1 extends Thread{

	Session session = HibernateUtil.getSessionFactory().openSession();
	static Long MAX = (long) 0;
	static final int MAXQUEUE = 10000;
	private volatile boolean isRunning = true;
	//private List<?> list = null;
	static Vector<DBTable> dbTables = new Vector<>();

	@Override
	public void run() {

		try {
			while(true){
				putMessage();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void putMessage() throws InterruptedException {
		Query query1 = session.createQuery("select count(*) from DBTable");
		List<?> list1 = query1.list();
		MAX = (Long) list1.get(0);

		Long min = (long) 0;
		Long max = (long) 10000;

		while(true){
			while (dbTables.size() == MAXQUEUE) {
				wait();
			}
			Query query = session.createQuery("from DBTable where id between :min and :max");
			query.setParameter("min", min);
			query.setParameter("max", max);
			System.out.println("Min 2"+min);
			System.out.println("Max 2"+max);

			List<?> list = query.list();	
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				DBTable dbTable = (DBTable) list.get(i);
				dbTables.add(dbTable);
				//System.out.println(dbTable.getTitle()+i);
			}
			if(max == MAX){
				break;
			}
			min = max+1;
			max = max + 10000;
			System.out.println("Min 1"+min);
			System.out.println("Max 1"+max);
			if(max>=MAX){
				max = MAX;
			}
		}
		notifyAll();
		/*while(max <= MAX){
		}*/

		//Later, when the necessary event happens, the thread that is running it calls notify() from a block synchronized on the same object.
	}

	/*public synchronized Vector<DBTable> getMessage() throws InterruptedException {
		notify();
		while (dbTables.size() == 0) {
			wait();//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.
		}
		Vector<DBTable> dbTables1 = dbTables;
		dbTables.clear();
		return dbTables1;
	}*/

	public void kill() {
		isRunning = false;
	}

}

/*for (int i = 0; i < list.size(); i++) {
DBTable dbTable = (DBTable)list.get(i);
System.out.println(dbTable.getTitle());
System.out.println(max);
writer.println(dbTable.getTitle());
}*/
