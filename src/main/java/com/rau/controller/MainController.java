package com.rau.controller;

import com.rau.model.User;
import com.rau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}

    @RequestMapping(value={"/surf"}, method = RequestMethod.GET)
    public ModelAndView surf(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("surf");
        return modelAndView;
    }

	@RequestMapping(value={"/street"}, method = RequestMethod.GET)
	public ModelAndView street(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("street");
		return modelAndView;
	}

	@RequestMapping(value={"/bmx"}, method = RequestMethod.GET)
	public ModelAndView bmx(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bmx");
		return modelAndView;
	}

	@RequestMapping(value={"/mtb"}, method = RequestMethod.GET)
	public ModelAndView mtb(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mtb");
		return modelAndView;
	}

	@RequestMapping(value={"/wake"}, method = RequestMethod.GET)
	public ModelAndView wake(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("wake");
		return modelAndView;
	}

	@RequestMapping(value={"/snow"}, method = RequestMethod.GET)
	public ModelAndView snow(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("snow");
		return modelAndView;
	}

	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("loginMessage","You have successfully logged in!");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = "admin/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome to the test page " + user.getName() + " " + user.getLastName());
		modelAndView.setViewName("admin/test");
		return modelAndView;

	}
	

}
