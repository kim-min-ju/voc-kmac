package kr.co.rmsoft.tms.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rmsoft.tms.coreframework.controller.BaseController;
import kr.co.rmsoft.tms.system.service.UserService;

/**
 * 사용자관리 ViewController 클래스
 * 
 * @ClassName UserViewController.java
 * @Description 사용자관리 ViewController 클래스
 * @author Minju Kim
 * @since 2021. 5. 28.
 *
 */
@Controller
@RequestMapping("/system/user")
public class UserViewController extends BaseController {

    @Autowired
    UserService service;

    // 화면 오픈
    @GetMapping
    public String view(Model model) {
        return "tms/system/user";
    }
}
