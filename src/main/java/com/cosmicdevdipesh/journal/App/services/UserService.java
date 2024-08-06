package com.cosmicdevdipesh.journal.App.services;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import com.cosmicdevdipesh.journal.App.entity.User;
import com.cosmicdevdipesh.journal.App.repository.JournalEntityRepository;
import com.cosmicdevdipesh.journal.App.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void saveEntry(User user) {
        userRepository.save(user)
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(ObjectId id) {

        return userRepository.findById(id);
    }
    public boolean deleteUser(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }
}
