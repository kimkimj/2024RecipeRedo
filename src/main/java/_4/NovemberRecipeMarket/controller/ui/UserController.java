package _4.NovemberRecipeMarket.controller.ui;

import _4.NovemberRecipeMarket.domain.dto.UserJoinRequest;
import _4.NovemberRecipeMarket.domain.dto.UserJoinResponse;
import _4.NovemberRecipeMarket.domain.dto.UserLoginRequest;
import _4.NovemberRecipeMarket.domain.dto.UserResponse;
import _4.NovemberRecipeMarket.exception.AppException;
import _4.NovemberRecipeMarket.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.CookieGenerator;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String chooseLoginType() {
        return "loginType";
    }

    @GetMapping("/join")
    public String chooseJoinType() {
        return "joinType";
    }

    @GetMapping("/users/join")
    public String joinForm(Model model) {
        model.addAttribute("userJoinRequest", new UserJoinRequest());
        return "/user/joinForm";
    }

    @PostMapping("/users/join")
    public String join(@Valid UserJoinRequest form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/joinForm";
        }
        model.addAttribute("userJoinReqeust", new UserJoinRequest());
        try {
            userService.join(form);
        } catch (AppException e) {
            model.addAttribute("e", e.getMessage());
        }
        return "redirect:/login";
    }

    // 로그인
    @GetMapping("/users/login")
    public String loginForm(Model model) {
        model.addAttribute("userLoginRequest", new UserLoginRequest());
        return "user/loginForm";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute UserLoginRequest userLoginRequest, BindingResult result,
                        HttpServletResponse response) {
        if (result.hasErrors()) {
            return "user/loginForm";
        }

        try {
            String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
            CookieGenerator cookieGenerator = new CookieGenerator();
            cookieGenerator.setCookieName("token");
            cookieGenerator.setCookieHttpOnly(true);
            cookieGenerator.setCookieMaxAge(60 * 60 * 2);
            cookieGenerator.addCookie(response, token);

        } catch (AppException e) {
            return "user/loginForm";
        }
        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletResponse response) {
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName("token");
        cookieGenerator.addCookie(response, "deleted");
        cookieGenerator.setCookieMaxAge(0);

        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/users/my")
    public String myPage(Model model, Authentication authentication) {
        UserResponse user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    // TODO: 회원정보 수정, 작성한 리뷰 목록, 리뷰 수정, 삭제
}
