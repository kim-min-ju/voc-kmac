package kr.co.kmac.login.controller;

import kr.co.kmac.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 ViewController 클래스
 * 
 * @ClassName AuthViewController.java
 * @Description 메인 ViewController 클래스
 * @author mjkim
 * @since 2023. 9. 18.
 *
 */
@Controller
@RequestMapping("")
public class MainViewController {
    @Autowired
    private MenuService service;

    // 헤더화면 오픈
    @GetMapping("/main")
    public String view(Model model) {
        model.addAttribute("menuList", service.getHeaderMenuList());

        return "pages/main";
    }

    @GetMapping("/main/menu")
    public String getMenu(Model model) {
        model.addAttribute("menuList", service.getHeaderMenuList());

        return "fragments/header :: headerMenu";
    }

    // 로그인화면 오픈
    @GetMapping("/login")
    public String login(Model model) {
        return "pages/login";
    }
}
