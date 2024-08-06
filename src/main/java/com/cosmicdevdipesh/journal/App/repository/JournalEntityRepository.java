package com.cosmicdevdipesh.journal.App.repository;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntityRepository extends MongoRepository<JournalEntry, ObjectId> {


}
