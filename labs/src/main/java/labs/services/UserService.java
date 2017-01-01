package labs.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import labs.models.Post;
import labs.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import labs.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository usersRepo;
	
	private List<User> users = new ArrayList<User>();
	
	@PostConstruct
	@Transactional
	public void createAdminUser() {	
		User userAdmin=usersRepo.findByLogin("admin");
		if(userAdmin==null){
			register("admin", "admin@mail.com", "qwerty");
		}
	}
	
		
	public List<User> getUsers() {
		return (List<User>) usersRepo.findAll(); 
	}
	
	@Transactional
	public Boolean register(String login, String email, String pass) {
		String passHash = new BCryptPasswordEncoder().encode(pass);
		
		User u = new User(login, email.toLowerCase(), passHash);
		User saved = usersRepo.save(u);
		if(saved!=null){
			return true;
		}
		return false;
	}
	
	 public User getUserByLogin(String login) {
		 	User u=usersRepo.findByLogin(login);
		 	
		 	if(u!=null){return u;}
	    	
		 	return null;
	     }

	    public User login(String login, String password) {
	       
	    	User user=usersRepo.findByLogin(login);
	        if(user==null || !( new BCryptPasswordEncoder().matches(password, user.getPassword()) ) ){	 
	           	return null;
	        }
	            
	        return user;
	    }
	    
	    
	    

	

	
}