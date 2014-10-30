package amazon.hibernate.app;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import amazon.hibernate.util.Thread5;
import amazon.hibernate.util.Thread6;


public class QueryClass {

	static List<?> list = null;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{

		/*Session session = HibernateUtil.getSessionFactory().openSession();
		//Query query = session.createQuery("from DBTable where id between 1 and 35");
		Query query = session.createQuery("from DBTable where id between :min and :max");
		Query query1 = session.createQuery("select count(*) from DBTable");
		List<?> list1 = query1.list();
		System.out.println(list1.get(0));
		query.setParameter("min", new Long(1));
		query.setParameter("max", new Long(35));
		List<?> list = query.list();
		DBTable dbTable = (DBTable)list.get(30);
		System.out.println(dbTable.getTitle());
		System.out.println(dbTable.getReview_time());*/

		/*ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Thread1());
		executor.execute(new Thread2());
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
		 */

		/*Thread1 thread1 = new Thread1();
		thread1.start();
		new Thread2(thread1).start();*/

		/*		Thread3 thread3 = new Thread3();
		thread3.start();
		new Thread4(thread3).start();
		 */	
		
		Thread5 thread3 = new Thread5();
		thread3.start();
		new Thread6(thread3).start();

	}

}
