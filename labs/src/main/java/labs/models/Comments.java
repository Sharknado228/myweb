package labs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import labs.repositories.PostRepository;

@Entity
@Table(name = "comments")
public class Comments{
	@Id
	@GeneratedValue
    private Long id;

	@NotNull
	@ManyToOne
	private Post p;
	
	
    @Size(min = 1, max = 2048)
	private String commentAuthor;
	
	
    @Size(min = 1, max = 2048)
	private String comment;
	
	public Comments (Post p, String commentAuthor,String comment) {
		this.p=p;
		this.commentAuthor = commentAuthor;
		this.comment = comment;
	}
	public Comments() {}
	
	public String getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setPost(Post post) {
		this.p = post;
	}
	
	public Post getPost() {
		return p;
	}
}
