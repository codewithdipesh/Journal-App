package com.cosmicdevdipesh.journal.App.repository;

import com.cosmicdevdipesh.journal.App.entity.JournalEntry;
import com.cosmicdevdipesh.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {


}
