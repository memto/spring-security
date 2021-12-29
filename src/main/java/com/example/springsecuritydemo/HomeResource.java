package com.example.springsecuritydemo;

import com.example.springsecuritydemo.models.AuthenReq;
import com.example.springsecuritydemo.models.AuthenRes;
import com.example.springsecuritydemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome</h1>";
    }

//    @PostMapping("/authen")
    @RequestMapping(value = "/authen", method = RequestMethod.POST)
    public ResponseEntity<?> authen(@RequestBody AuthenReq authenReq) throws Exception {
        try {
             Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenReq.getUsername(), authenReq.getPassword())
             );

             final String jwt = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
             return ResponseEntity.ok(new AuthenRes(jwt));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenReq.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenRes(jwt));
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>Welcome user</h1>";
    }
    @GetMapping("/admin")
    public String admin() {
        return "<h1>Welcome admin</h1>";
    }
}
