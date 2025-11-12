package rjms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rjms.model.User;
import rjms.repository.UserRepository;
import rjms.security.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email required"));
        }
        if (userRepository.findByEmail(email) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }
        User u = new User();
        u.setName(payload.getOrDefault("name", ""));
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(payload.get("password")));
        u.setRole(payload.getOrDefault("role", "Author"));
        userRepository.save(u);
        return ResponseEntity.ok(Map.of("status", "registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String password = body.get("password");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User user = userRepository.findByEmail(email);
            if (user == null) return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
            String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), java.util.Collections.emptyList()));
            Map<String, Object> resp = new HashMap<>();
            resp.put("token", token);
            resp.put("user", Map.of("id", user.getId(), "name", user.getName(), "email", user.getEmail(), "role", user.getRole()));
            return ResponseEntity.ok(resp);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(org.springframework.security.core.Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthenticated"));
        }
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user == null) return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("id", user.getId());
        resp.put("name", user.getName());
        resp.put("email", user.getEmail());
        resp.put("role", user.getRole());
        return ResponseEntity.ok(resp);
    }
}
