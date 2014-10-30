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

import amazon.hibernate.data.DBTable;
import amazon.hibernate.schemas.Combined;
import amazon.hibernate.schemas.Product;
import amazon.hibernate.schemas.Review;
import amazon.hibernate.util.GenerateReview;
import amazon.hibernate.util.HibernateUtil;

public class Test1 {

	static long count = 0;
	Session session;
	Transaction tx; 
	ArrayList<DBTable> dbArrayList = new ArrayList<>();
	ArrayList<Product> prArrayList = new ArrayList<>();
	ArrayList<Review> reArrayList = new ArrayList<>();
	ArrayList<Combined> coArrayList = new ArrayList<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test1 test = new Test1();
		InputStream fileStream;
		try {	
			
			GenerateReview generateReview = new GenerateReview();
			fileStream = new FileInputStream("/home/abhinav/Downloads/Arts.txt.gz");
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
						//System.out.println("Ranganath");
						//DBTable dbTable = generateReview.createReview(reviews);
						Product product = new Product();
						product.setId(count);
						product.setPrice(reviews.get("price"));
						product.setProduct_id(reviews.get("productId"));
						product.setTitle(reviews.get("title"));
						
						Review review = new Review();
						review.setId(count);
						review.setProfile_name(reviews.get("profileName"));
						review.setHelpfulness(reviews.get("helpfulness"));
						review.setReview_text(reviews.get("text"));
						review.setReview_summary(reviews.get("summary"));
						review.setScore(reviews.get("score"));
						review.setUser_id(reviews.get("userId"));
						review.setReview_time(reviews.get("time"));
						
						Combined combined = new Combined();
						combined.setProduct_id(reviews.get("productId"));
						combined.setReview_id(count);
						combined.setId(count);
						count++;
						
						test.prArrayList.add(product);
						test.coArrayList.add(combined);
						test.reArrayList.add(review);
						if(test.prArrayList.size()==10000){
							test.commitMethod(test.prArrayList, test.reArrayList, test.coArrayList);
							test.dbArrayList = new ArrayList<>();
						}
					} finally {
						//session.close();
					}
					reviews.clear();
					reviews =null;
					reviews = new HashMap<>();
				}
			}
			long date2 = System.currentTimeMillis();
			System.out.println("End time: "+ date2);
			System.out.println((date2-date1)+"  milli seconds");
			System.out.println((date2-date1)/1000+"  seconds");
			System.out.println((date2-date1)/(1000*60)+"  minutes");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new Workers("" + i);
			executor.execute(worker);
		}
		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");*/
		//test.commitMethod(null);

	}

	private void commitMethod(ArrayList<Product> prArrayList, ArrayList<Review> reArrayList, ArrayList<Combined> coArrayList) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		for (int i = 0; i < prArrayList.size(); i++) {
			session.save(prArrayList.get(i));
			session.save(reArrayList.get(i));
			session.save(coArrayList.get(i));
			if (i % 100 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}


}
