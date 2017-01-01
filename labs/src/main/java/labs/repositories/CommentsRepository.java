package labs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import labs.models.Comments;
import labs.models.Post;
import labs.models.User;

public interface CommentsRepository extends CrudRepository<Comments, Long>{
	 List<Comments> findByP(Post p); 

	
}
