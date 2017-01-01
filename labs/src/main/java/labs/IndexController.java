package labs;


import labs.services.PostsService;
import labs.models.Comments;
import labs.models.Post;
import labs.repositories.PostRepository;

    import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

    @Controller
    public class IndexController {
    	
    	@Autowired
    	private PostsService postsService; // сервіси використовуємо в контролерах
    	
    	
    	
    	
    	@RequestMapping("/home")
        public String index(Model model) {
            model.addAttribute("posts", postsService.getRecentPosts());
            return "index";        
    }
    	
    	/*@RequestMapping(value = "/post", method = RequestMethod.POST)
        public String createPost(@RequestParam("idi") String idi, @RequestParam("str") String comment) {
        	//TODO
    		postsService.addComment(Long.valueOf(idi).longValue(),"Unknown",comment);
            return "redirect:home";
        }*/
    	
    	@ResponseBody
    	@RequestMapping(value = "/post", method = RequestMethod.POST)
        public Comments createPost(@RequestParam("commentAuthor") String auth, @RequestParam("comment") String com, @RequestParam("post_id") String postId) {
    		System.out.println(auth);
    		return new Comments(postsService.findById(Long.parseLong(postId)),auth,com);

        }
    	
    }