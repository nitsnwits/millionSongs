package amazon.hibernate.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import amazon.hibernate.data.DBTable;

public class Workers implements Runnable{

	String string = "";

	public Workers(String string) {
		// TODO Auto-generated constructor stub
		this.string = string;
	}

	private void processCommand() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from DBTable where id = 1 ");
		List<?> list = query.list();
		DBTable dbTable = (DBTable)list.get(0);
		System.out.println(dbTable.getTitle());
		System.out.println(dbTable.getReview_time());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("pool"+string);
		processCommand();
		System.out.println("end");
	}

}

/*try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	e.printStackTrace();
}*/