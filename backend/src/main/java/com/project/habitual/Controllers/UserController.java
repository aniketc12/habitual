package com.project.habitual.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.habitual.Database.UserAdapter;

@RestController
@Component
public class UserController {
    private UserAdapter userAdapter;
    public UserController(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }
  
  @PostMapping("/user/signup")
  public ResponseEntity<?> signup(@RequestBody Map<String, Object> data) {
    String email = String.valueOf(data.get("email"));
    if(email.equals("null") || email.trim().isEmpty()) {
        return new ResponseEntity<>("Please provide a user email", HttpStatus.BAD_REQUEST);
    }
    String password = String.valueOf(data.get("password"));
    if(password.equals("null")|| password.trim().isEmpty()) {
        return new ResponseEntity<>("Please provide a user password", HttpStatus.BAD_REQUEST);
    }

    String status = this.userAdapter.insertUser(email, password).toLowerCase();
    if (status.equals("ok")) {
      Map<String, Object> resonse = new HashMap<String, Object>();
      resonse.put("email", email);
      resonse.put("password", password);
      return new ResponseEntity<>(resonse, HttpStatus.OK);
    } else if (status.contains("duplicate entry")) {
      return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
  }
}
