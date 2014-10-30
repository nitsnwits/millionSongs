/*package amazon.hibernate.app;

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

import amazon.hibernate.data.DBTable;
import amazon.hibernate.util.GenerateReview;
import amazon.hibernate.util.HibernateUtil;

public class Test {

	static long count = 0;
	Session session;
	Transaction tx;
	ArrayList<DBTable> dbArrayList = new ArrayList<DBTable>();

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test test = new Test();
		InputStream fileStream;
		try {	
			GenerateReview generateReview = new GenerateReview();
			fileStream = new FileInputStream("/home/abhinav/Downloads/Movies_&_TV.txt.gz");
			InputStream gzipStream = new GZIPInputStream(fileStream);
			Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
			BufferedReader buffered = new BufferedReader(decoder);
			String line = null;
			HashMap<String, String> reviews = new HashMap<>();
			long date1 = System.currentTimeMillis();
			System.out.println("Start time: "+ date1);
			while((line=buffered.readLine())!=null){
				if(line.length()!=0){
					String[] string = line.split(":");
					String[] string1 = string[0].split("/");
					reviews.put(string1[1], string[1].trim());
				}else{
					try {				
						//DBTable dbTable = generateReview.createReview(reviews);
						DBTable dbTable = new DBTable();
						dbTable.setId(count);
						count++;
						dbTable.setProduct_id(reviews.get("productId"));
						dbTable.setHelpfulness(reviews.get("helpfulness"));
						dbTable.setProfile_name(reviews.get("profileName"));
						dbTable.setReview_summary(reviews.get("summary"));
						dbTable.setReview_text(reviews.get("text"));
						if(reviews.get("time") != null){
							dbTable.setReview_time(Integer.parseInt(reviews.get("time")));	
						}
						dbTable.setReview_time(reviews.get("time"));
						if(reviews.get("score")!=null){
							dbTable.setScore(Float.parseFloat(reviews.get("score")));	
						}
						dbTable.setScore(reviews.get("score"));
						dbTable.setPrice(reviews.get("price"));
						dbTable.setTitle(reviews.get("title"));
						dbTable.setUser_id(reviews.get("userId"));
						test.dbArrayList.add(dbTable);
						if(test.dbArrayList.size()==200000){
							=======
									fileStream = new FileInputStream(
											"/Users/neerajsharma/Downloads/amazonData/Arts.txt.gz");
							InputStream gzipStream = new GZIPInputStream(fileStream);
							Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
							BufferedReader buffered = new BufferedReader(decoder);

							long date1 = System.currentTimeMillis();
							System.out.println("Start time: " + date1);
							String line = null;
							HashMap<String, String> reviews = new HashMap<String, String>();

							while ((line = buffered.readLine()) != null) {

								if (line.length() != 0) {

									if (line.startsWith("product")) {

										String[] string = line.split(":");
										String[] string1 = string[0].split("/");
										reviews.put(string1[1], string[1].trim());
									}
								} else {

									try {
										DBTable dbTable = generateReview.createReview(reviews);
										test.dbArrayList.add(dbTable);
										if (test.dbArrayList.size() == 1000) {
											>>>>>>> 4167d260e2c5c781cb856a40ed8982d2cd126a61
											test.commitMethod(test.dbArrayList);
											test.dbArrayList = new ArrayList<DBTable>();
										}
									} finally {
										// session.close();
									}
									reviews = new HashMap<String, String>();
								}
							}
							long date2 = System.currentTimeMillis();
							<<<<<<< HEAD
							System.out.println("End time: "+ date2);
							System.out.println((date2-date1)+"  milli seconds");
							System.out.println((date2-date1)/1000+"  seconds");
							System.out.println((date2-date1)/(1000*60)+"  minutes");

							=======
									System.out.println("End time: " + date2);
							System.out.println((date2 - date1) + "  milli seconds");
							System.out.println((date2 - date1) / 1000 + "  seconds");
							>>>>>>> 4167d260e2c5c781cb856a40ed8982d2cd126a61
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new Workers("" + i);
			executor.execute(worker);
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");
						//test.commitMethod(null);

					}

					private void commitMethod(ArrayList<DBTable> dbArrayList) {
						// TODO Auto-generated method stub
						Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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

				
				 * if(string1[1].equals("productId")){ reviews.put("productId",
				 * string[1].trim()); }else if(string1[1].equals("title")){ reviews.put("title",
				 * string[1].trim()); }else if(string1[1].equals("price")){ reviews.put("price",
				 * string[1].trim()); } }else if(line.startsWith("review")){
				 * 
				 * String[] string = line.split(":"); String[] string1 = string[0].split("/");
				 * 
				 * 
				 * //if(string1[1].equals("userId")){ //reviews.put("userId", string[1].trim());
				 * //}else if(string1[1].equals("profileName")){ //reviews.put("profileName",
				 * string[1].trim()); //}else if(string1[1].equals("helpfulness")){
				 * //reviews.put("helpfulness", string[1].trim()); //}else
				 * if(string1[1].equals("score")){ //reviews.put("score", string[1].trim());
				 * //}else if(string1[1].equals("time")){ //reviews.put("time",
				 * string[1].trim()); //}else if(string1[1].equals("summary")){
				 * //reviews.put("summary", string[1].trim()); //}else
				 * if(string1[1].equals("text")){ //reviews.put("text", string[1].trim()); //}
				 

*/