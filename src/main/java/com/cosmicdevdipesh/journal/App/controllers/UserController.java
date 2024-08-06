package com.cosmicdevdipesh.journal.App.controllers;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import com.cosmicdevdipesh.journal.App.entity.User;
import com.cosmicdevdipesh.journal.App.services.JournalEntryService;
import com.cosmicdevdipesh.journal.App.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }
    @PutMapping("id")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable ObjectId id) {
        User userdb = userService.getUserById(id).orElse(null);
        if(userdb != null) {
            userdb.setUsername(user.getUsername());
            userdb.setPassword(user.getPassword());

            userService.saveEntry(userdb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
