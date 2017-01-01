package labs.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import labs.models.Comments;
import labs.models.Post;
import labs.models.User;
import labs.repositories.CommentsRepository;
import labs.repositories.PostRepository;
import labs.repositories.UserRepository;

@Service
public class PostsService {
	 @Autowired
	  private UserRepository usersRepo;
	  @Autowired
	  private PostRepository postsRepo;
	  @Autowired
	  private CommentsRepository commentRepo;
	  @Autowired
	  UserService us;
	  
	  
	        public Post findById(Long id){
	        	return postsRepo.findById(id);
	        }
	        
	        public List<Post> getRecentPosts() {
	        	User userAdmin=usersRepo.findByLogin("admin");
	        	List <Post> posts=postsRepo.findByAuthor(userAdmin); 
	        	for(int i=0; i<posts.size();i++){
	        		posts.get(i).setComments(commentRepo.findByP(posts.get(i)));
	        	}
	        	return posts;
	        }
	        
	        public List<Post> getRecentPostsByAuthor(String login) {
	        	User userAdmin=usersRepo.findByLogin(login);
	        	List <Post> posts=postsRepo.findByAuthor(userAdmin); 
	        	for(int i=0; i<posts.size();i++){
	        		posts.get(i).setComments(commentRepo.findByP(posts.get(i))); 
	        	}
	        	return posts;
	        }
	        
	        public Post getPost(int i) {
	        	User userAdmin=usersRepo.findByLogin("admin");
	            return userAdmin.getPosts().get(i);
	        }
	        
	        public void addPost(Post p) {
	            postsRepo.save(p);
	        }
	        
	        public void addComment(Long id, String commentAuthor, String comment) {
	            commentRepo.save(new Comments(postsRepo.findById(id), commentAuthor, comment));
	        }
	        
	        public void addComment2(String commentAuthor,String comment) {
	            commentRepo.save(new Comments(postsRepo.findById(1L),commentAuthor,comment));
	        }
	        
	        public List<Comments> getRecentPostComments() {
	        	Post p = postsRepo.findById(1L);
	            return p.getComments();
	        }
	        
	        @PostConstruct
	    	@Transactional
	        public void addPostUser() {
	        	User userAdmin=usersRepo.findByLogin("admin");
	            postsRepo.save(new Post(userAdmin,"images/amazing2.jpg"));
	            postsRepo.save(new Post(userAdmin,"images/amazing3.jpg"));
	            postsRepo.save(new Post(userAdmin,"images/amazing4.jpg"));
	            Post p=postsRepo.findById(1L);
	            commentRepo.save(new Comments(p,"Vasia","Daaaaa"));
	            commentRepo.save(new Comments(p,"Kesha","Da"));
	            commentRepo.save(new Comments(p,"Vas","Naaaaa"));
	            p=postsRepo.findById(2L);
	            commentRepo.save(new Comments(p,"Kes","Na"));
	            p=postsRepo.findById(3L);
	            commentRepo.save(new Comments(p,"Ky","Nyy"));
	            commentRepo.save(new Comments(p,"Via","WwWwW"));
	        }
	        
}