package com.beam.fileupload.account;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    public static final String SESSION_ACCOUNT = "session_account";

    private final AccountService accountService;

    @PostMapping("/register")
    public String register(@RequestBody Account account,
                            HttpSession session) {
        if (account != null){
            session.setAttribute("session",account);
        }
        assert account != null;
        accountService.register(account);
        return "Registered";
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account account,
                         HttpServletResponse response) {
        Account acc = accountService.login(account);
        Cookie cookie = new Cookie("cookie", acc.getId());
        response.addCookie(cookie);
        return acc;
    }
    @GetMapping("/allUsers")
    public List<Account> getUsers() {
        return accountService.getUsers();
    }
}
