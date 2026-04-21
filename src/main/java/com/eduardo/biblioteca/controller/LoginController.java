package com.eduardo.biblioteca.controller;

import com.eduardo.biblioteca.dto.input.LoginRequest;
import com.eduardo.biblioteca.dto.output.LoginResponse;
import com.eduardo.biblioteca.service.LoginService;
import com.eduardo.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {



        @Autowired
        private LoginService service;

        @PostMapping("/login")
        public LoginResponse login(@RequestBody LoginRequest request) {
            String token = service.login(request);
            return new LoginResponse(token);
        }

        @GetMapping("/test")
        public String test() {
            return "test";
        }
}
