package com.cosmicdevdipesh.journal.App.controllers;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import com.cosmicdevdipesh.journal.App.entity.User;
import com.cosmicdevdipesh.journal.App.repository.JournalEntityRepository;
import com.cosmicdevdipesh.journal.App.services.JournalEntryService;
import com.cosmicdevdipesh.journal.App.services.UserService;
import org.apache.catalina.connector.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal/")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("id")
    public ResponseEntity<List<JournalEntry> > getAllJournalEntriesOfUser(@PathVariable ObjectId id){
        User user = userService.getUserById(id).orElseThrow();
        List<JournalEntry> journalEntries = user.getJournalList();
        return new ResponseEntity<>(journalEntries,HttpStatus.OK);
    }

    @PostMapping("id")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable ObjectId id){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry,id);
      return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journal = journalEntryService.getJournalEntryById(myId) ;
        if(journal.isPresent()){
            return new ResponseEntity<>(journal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        Optional<JournalEntry> old = journalEntryService.getJournalEntryById(myId);
        if(old.isPresent()){
            old.get().setTitle(myEntry.getTitle());
            old.get().setContent(myEntry.getContent());

            journalEntryService.saveEntry(old.get());
            return new ResponseEntity<>(old.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<Boolean> deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteJournalEntryById(myId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
