package kr.co.kmac.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 권한 ViewController 클래스
 * 
 * @ClassName AuthViewController.java
 * @Description 권한 ViewController 클래스
 * @author mjkim
 * @since 2023. 9. 18.
 *
 */
@Controller
@RequestMapping("/system/auth")
public class AuthViewController {

    // 화면 오픈
    @GetMapping
    public String view(Model model) {
        return "pages/system/auth";
    }
}
