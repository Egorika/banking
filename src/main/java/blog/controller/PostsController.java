package blog.controller;

import blog.model.Post;
import blog.model.User;
import blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostsController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/posts/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Post post = postService.findById(id);
		if (post == null) {
			return "redirect:/";
		}
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			model.addAttribute("auth", "auth");
		}
		model.addAttribute("post", post);
		model.addAttribute("latest10posts", postService.findTop10OrderByDate());
		return "posts/view";
	}
}
