package blog.controller;

import blog.model.Post;
import blog.model.User;
import blog.service.PostService;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		if (user != null) {
			model.addAttribute("auth", user);
		}
		
		List<Post> latest5Posts = postService.findTop5OrderByDate();
		model.addAttribute("latest5posts", latest5Posts);
		
		List<Post> latest10Posts = postService.findTop10OrderByDate();
		model.addAttribute("latest10posts", latest10Posts);
		
		return "index";
	}
	
	@RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
	public String admin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("allPosts", postService.getAllOrderByDateDesc());
		return "admin/panel";
	}
	
	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
	public String editPost(@Valid Post post, BindingResult bindingResult,
	                       RedirectAttributes redirectAttributes, Model model) {
		String message = "Пост изменён";
		if (bindingResult.hasErrors()) {
			return "redirect:/admin/panel";
		} else {
			Post postToEdit = postService.findById(post.getId());
			postToEdit.setBody(post.getBody());
			postToEdit.setTitle(post.getTitle());
			postToEdit.setDate(System.currentTimeMillis());
			
			postService.save(postToEdit);
			redirectAttributes.addFlashAttribute("successMessage", message);
			return "redirect:/admin/panel";
		}
	}
	
	@RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable Long id, Model model) {
		Post post = postService.findById(id);
		model.addAttribute("post", post);
		return "admin/edit";
	}
	
	@RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
	public String removePost(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		Post post = postService.findById(id);
		String message = "Пост удалён";
		
		postService.delete(post);
		redirectAttributes.addFlashAttribute("successMessage", message);
		return "redirect:/admin/panel";
	}
	
	@RequestMapping(value = "/admin/new", method = RequestMethod.GET)
	public String addPost(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "admin/new";
	}
	
	@RequestMapping(value = "/admin/new", method = RequestMethod.POST)
	public String addPost(@Valid Post post, BindingResult bindingResult,
	                      RedirectAttributes redirectAttributes, Model model) {
		String message = "Новый пост создан";
		if (bindingResult.hasErrors()) {
			return "redirect:/admin/panel";
		} else {
			post.setAuthor(userService.findByUsername("igoslav"));
			post.setDate(System.currentTimeMillis());
			postService.save(post);
			redirectAttributes.addFlashAttribute("successMessage", message);
			model.addAttribute("post", new Post());
			return "redirect:/admin/panel";
		}
	}
}
