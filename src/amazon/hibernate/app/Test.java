package amazon.hibernate.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import amazom.hibernate.util.GenerateReview;
import amazom.hibernate.util.HibernateUtil;
import amazon.hibernate.data.DBTable;

public class Test {

	int count = 0;
	Session session;
	Transaction tx; 
	ArrayList<DBTable> dbArrayList = new ArrayList<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream fileStream;

		try {
			Test test = new Test();
			GenerateReview generateReview = new GenerateReview();
			fileStream = new FileInputStream("/home/abhinav/Downloads/Arts.txt.gz");
			InputStream gzipStream = new GZIPInputStream(fileStream);
			Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
			BufferedReader buffered = new BufferedReader(decoder);

			long date1 = System.currentTimeMillis();
			System.out.println("Start time: "+ date1);
			String line = null;
			HashMap<String, String> reviews = new HashMap<>();

			while((line=buffered.readLine())!=null){

				if(line.length()!=0){

					if(line.startsWith("product")){

						String[] string = line.split(":");
						String[] string1 = string[0].split("/");
						reviews.put(string1[1], string[1].trim());
					}				
				}else{

					try {				
						DBTable dbTable = generateReview.createReview(reviews);
						test.dbArrayList.add(dbTable);
						if(test.dbArrayList.size()==1000){
							test.commitMethod(test.dbArrayList);
							test.dbArrayList = new ArrayList<>();
						}

					} finally {
						//session.close();
					}
					reviews = new HashMap<>();
				}
			}
			long date2 = System.currentTimeMillis();
			System.out.println("End time: "+ date2);
			System.out.println((date2-date1)+"  milli seconds");
			System.out.println((date2-date1)/1000+"  seconds");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void commitMethod(ArrayList<DBTable> dbArrayList) {
		// TODO Auto-generated method stub
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		for (int i = 0; i < dbArrayList.size(); i++) {
			session.save(dbArrayList.get(i));
			if (i % 100 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}

}

/*if(string1[1].equals("productId")){
reviews.put("productId", string[1].trim());
}else if(string1[1].equals("title")){
reviews.put("title", string[1].trim());
}else if(string1[1].equals("price")){
reviews.put("price", string[1].trim());
}
}else if(line.startsWith("review")){

String[] string = line.split(":");
String[] string1 = string[0].split("/");


//if(string1[1].equals("userId")){
//reviews.put("userId", string[1].trim());
//}else if(string1[1].equals("profileName")){
//reviews.put("profileName", string[1].trim());
//}else if(string1[1].equals("helpfulness")){
//reviews.put("helpfulness", string[1].trim());
//}else if(string1[1].equals("score")){
//reviews.put("score", string[1].trim());
//}else if(string1[1].equals("time")){
//reviews.put("time", string[1].trim());
//}else if(string1[1].equals("summary")){
//reviews.put("summary", string[1].trim());
//}else if(string1[1].equals("text")){
//reviews.put("text", string[1].trim());
//}
 */					

