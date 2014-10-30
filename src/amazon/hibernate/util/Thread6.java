package amazon.hibernate.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import amazon.hibernate.data.DBTable;


public class Thread6 extends Thread{

	Thread5 thread3;
	PrintWriter writer;

	public Thread6(Thread5 thread) {
		// TODO Auto-generated constructor stub
		thread3 = thread;
	}

	@Override
	public void run() {

		try {
			writer = new PrintWriter("amazon.txt", "UTF-8");
			while (true) {

				Vector<DBTable> message = thread3.getMessage();
				if(message!=null){
					for (int i = 0; i < message.size(); i++) {
						DBTable dbTable = (DBTable) message.get(i);
						//System.out.println(dbTable.getTitle());
						writer.append("\n"+dbTable.getTitle());
					}	
				}
				//System.out.println("***************"+message.size());


			}
		} catch(NullPointerException e){
			e.printStackTrace();
			writer.close();
			long date2 = System.currentTimeMillis();
			System.out.println("End time: "+ date2);
			System.out.println((date2-Thread3.date1)+"  milli seconds");
			System.out.println((date2-Thread3.date1)/1000+"  seconds");
			System.out.println((date2-Thread3.date1)/(1000*60)+"  minutes");
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
