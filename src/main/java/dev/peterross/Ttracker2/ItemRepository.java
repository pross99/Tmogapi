package dev.peterross.Ttracker2;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    Optional<Item> findItemBywowheadId(String wowheadId);
    Optional<Item> deleteById(String wowheadId);



}
