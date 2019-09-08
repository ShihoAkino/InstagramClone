package instagram.clone.beans;

import java.sql.Timestamp;
import java.util.Date;

public class Post {

	private int postId;
	private String description;
	private String author;
	private String pictureLink;
	private Timestamp postedDate;
	private String category;

	public Post() {
	}
	
	public Post(String description, String author, String pictureLink, String category) {
		this.description = description;
		this.author = author;
		this.pictureLink = pictureLink;
		this.category = category;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public Timestamp getPostedDate() {
		return postedDate;
	}

	// returns convert Timestamp to Date and returns Date datatype
	public Date getPostedDateAsDateType() {
		Date date = new Date(postedDate.getTime());

		return date;
	}


	public void setPostedDate(Timestamp postedDate) {
		this.postedDate = postedDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
