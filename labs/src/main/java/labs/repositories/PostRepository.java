package labs.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import labs.models.Comments;
import labs.models.Post;
import labs.models.User;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByAuthor(User u); 
    Post findById(Long id);
}
