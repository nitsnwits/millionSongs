package amazon.hibernate.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="amazon")
public class DBTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String product_id;
	private String user_id;
	private String profile_name;
	private String helpfulness;
	private float score;
	private int review_time;
	@Type(type = "text")
	private String review_summary;
	@Type(type = "text")
	private String review_text;
	private String title;
	private String price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getHelpfulness() {
		return helpfulness;
	}
	public void setHelpfulness(String helpfulness) {
		this.helpfulness = helpfulness;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getReview_time() {
		return review_time;
	}
	public void setReview_time(int review_time) {
		this.review_time = review_time;
	}
	public String getReview_summary() {
		return review_summary;
	}
	public void setReview_summary(String review_summary) {
		this.review_summary = review_summary;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

}
