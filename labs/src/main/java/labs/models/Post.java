package labs.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue
    private Long id;
	
	@NotNull
	@ManyToOne
    private User author;
    

	
	@NotBlank
    @Size(min = 1, max = 2048)
    private String postImage;
	
	@OneToMany(mappedBy = "p")
	private List<Comments> comments = new ArrayList<>();
   
    
	public Post(User author,  String postImage) {
		this.author = author;
		this.postImage=postImage;
	}
	
	
		public Post(String author,  String postImage) {
			this.author.setLogin(author);
			this.postImage=postImage;
		}
	
	
	public Post() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}
    
	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
    
}
