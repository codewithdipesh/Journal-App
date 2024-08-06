package com.cosmicdevdipesh.journal.App.services;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import com.cosmicdevdipesh.journal.App.entity.User;
import com.cosmicdevdipesh.journal.App.repository.JournalEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private  JournalEntityRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,ObjectId id) {
        User user = userService.getUserById(id).orElseThrow();
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalList().add(saved);
        userService.saveEntry(user);
        journalEntryRepository.save(journalEntry);
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    public boolean deleteJournalEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
        return true;
    }
}
