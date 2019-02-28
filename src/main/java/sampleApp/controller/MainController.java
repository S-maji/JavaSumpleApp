package sampleApp.controller;

import sampleApp.form.AccountForm;
import sampleApp.form.PostForm;
import sampleApp.model.Account;
import sampleApp.model.FvUser;
import sampleApp.model.Post;
import sampleApp.repository.AccountRepository;
import sampleApp.repository.FvUserRepository;
import sampleApp.dao.FvUserDAO;
import sampleApp.repository.PostRepository;
import sampleApp.service.AccountService;
import sampleApp.service.FvUserService;
import sampleApp.service.PostService;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.persistence.EntityNotFoundException;
//import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PostService postService;

	@Autowired
	PostRepository postRepository;

	@Autowired
	FvUserRepository fvUserRepository;

	@Autowired
	FvUserService fvUserService;

	@Autowired
	FvUserDAO fvUserDao;

	@RequestMapping("/")
	public String topPage(Model model, Principal principal) {
		Account loginUser;
		try {
			loginUser = accountRepository.findByName(principal.getName());
		} catch(Exception e) {
			loginUser = null;
		}
		model.addAttribute("user", loginUser);
		return "topPage";
	}

	@RequestMapping(value="/signup")
	public String signupForm(@ModelAttribute AccountForm form, Model model) {
		return "signupForm";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String create(@ModelAttribute @Validated AccountForm form, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("登録失敗");
			return signupForm(form, model);
		}

		Account account = new Account();
		account.setName(form.getName());
		accountService.create(account, form.getPassword());
		System.out.println("登録成功");
		return "login";
	}

	@RequestMapping(value="/hello/{userId}")
	public String hello(@ModelAttribute PostForm postForm, @PathVariable("userId") int userId,
			Model model, Principal principal) {
		System.out.println("ゆーざーぺーじひょうじ");
        //Principalからログインユーザの情報を取得
		Account loginUser = accountRepository.findByName(principal.getName());
		try {
			List<Account> allUsers = loginUser.getFvusers();
			model.addAttribute("allUsers", allUsers);
	        Account user = accountRepository.findById(userId);
	        if(allUsers.contains(user)) {
	        	model.addAttribute("fvuser", user);
	        }
	        List<Post> usersPost = user.getPosts();
	        model.addAttribute("name", user.getName());
	        model.addAttribute("userid", user.getId());
	        model.addAttribute("usersPost", usersPost);
	        model.addAttribute("loginUser", loginUser);
	        model.addAttribute("user", user);
	        return "hello";
		} catch(EntityNotFoundException e) {
			return "redirect:/topPage";
		}
	}

	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(@ModelAttribute @Validated PostForm postForm, BindingResult bindingResult, Principal principal, Model model) {
		if(bindingResult.hasErrors()) {
			return "redirect:/hello";
		}

		Account loginUser = accountRepository.findByName(principal.getName());

		Post post = new Post();
		post.setText(postForm.getText());
		post.setUser(loginUser);
		//post.setUserId(postForm.getUserId());
		postService.create(post);
		return "redirect:/hello/" + (loginUser.getId());
	}

	@RequestMapping(value="/post/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") int id, Principal principal) {
		Account loginUser = accountRepository.findByName(principal.getName());
		postRepository.deleteById(id);
		return "redirect:/hello/" + (loginUser.getId());
	}

	@RequestMapping("postAll")
	public String postAll(Model model, Principal principal) {
		List<Post> postAll= postRepository.findAll();
		Account loginUser = accountRepository.findByName(principal.getName());
		//model.addAttribute("userName", user.getName());
		model.addAttribute("postAll", postAll);
		model.addAttribute("user", loginUser);
		return "postAll";
	}

	@RequestMapping(value="/follow/{userId}", method=RequestMethod.POST)
	public String follow(Model model, Principal principal, @PathVariable("userId") int userId) {

		try {
		Account loginUser = accountRepository.findByName(principal.getName());

		Account postUser = accountRepository.findById(userId);

		FvUser fvuser = new FvUser();
		fvuser.setUserId(loginUser.getId());
		fvuser.setFvUserId(postUser.getId());

		fvUserService.create(fvuser);
		return "redirect:/hello/" + (postUser.getId());
		} catch(Exception e) {
			return "redirect:/";
		}
 	}

	@RequestMapping(value="/follow/{userId}", method=RequestMethod.DELETE)
	public String unFollow(Model model, Principal principal, @PathVariable("userId") int userId) {
		Account loginUser = accountRepository.findByName(principal.getName());
		FvUser fvUser = fvUserDao.findUser(loginUser.getId(), userId);
		fvUserRepository.delete(fvUser);
		return "redirect:/hello/" + userId;
	}


	@RequestMapping("allUsers")
	public String allUsers(Model model) {
		List<Account> allUsers = accountRepository.findAll();
		model.addAttribute("allUsers", allUsers);
		return "allUsersPage";
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(Principal principal,@RequestParam("name") String name) {
		Account loginUser = accountRepository.findByName(principal.getName());
		System.out.println(name);
		Account user = accountRepository.findByName(name);
		if(user != null ) {
			return "redirect:/hello/" + user.getId();
		} else {
			System.out.println("見つかりません");
			return "redirect:/hello/" + loginUser.getId();
		}
	}
}
