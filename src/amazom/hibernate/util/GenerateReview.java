package amazom.hibernate.util;

import java.util.HashMap;

import amazon.hibernate.data.DBTable;

public class GenerateReview {

	public DBTable createReview(HashMap<String, String> reviews){
		DBTable dbTable = new DBTable();
		dbTable.setProduct_id(reviews.get("productId"));
		dbTable.setHelpfulness(reviews.get("helpfulness"));
		dbTable.setProfile_name(reviews.get("profileName"));
		dbTable.setReview_summary(reviews.get("summary"));
		dbTable.setReview_text(reviews.get("text"));
		if(reviews.get("time") != null){
			dbTable.setReview_time(Integer.parseInt(reviews.get("time")));	
		}
		if(reviews.get("score")!=null){
			dbTable.setScore(Float.parseFloat(reviews.get("score")));	
		}
		dbTable.setPrice(reviews.get("price"));
		dbTable.setTitle(reviews.get("title"));
		dbTable.setUser_id(reviews.get("userId"));
		return dbTable;
	}

}
