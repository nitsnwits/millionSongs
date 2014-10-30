package amazon.hibernate.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;

import amazon.hibernate.data.DBTable;

public class Thread2 extends Thread{

	static int count = 0;
	Thread1 thread1 = null;
	PrintWriter writer;
	public Thread2(Thread1 thread1) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated constructor stub
		this.thread1= thread1;
		System.out.println("This is thread 2");
		writer= new PrintWriter("amazon.txt", "UTF-8");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			writeMessage();
			System.out.println("Ranganath");
			writer.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void writeMessage() throws InterruptedException{
		Vector<DBTable> list = Thread1.dbTables;
		notifyAll();
		while(true){
			if(list!=null){
				while(list.size()==0){
					wait();
				}
				for (int i = 0; i < list.size(); i++) {
					count++;
					DBTable dbTable = (DBTable) list.get(i);
					System.out.println(dbTable.getTitle());
					writer.append("\n"+dbTable.getTitle());
				}	
			}
		} 
	}

}
