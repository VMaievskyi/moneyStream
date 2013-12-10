package com.piramida.controller.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemNavigationController {

    // Login form
    @RequestMapping("/login")
    public String login() {
	return "loginPage";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(final Model model) {
	model.addAttribute("loginError", true);
	return "loginPage";
    }
}
