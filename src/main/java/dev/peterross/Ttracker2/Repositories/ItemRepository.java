package dev.peterross.Ttracker2.Repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.peterross.Ttracker2.Entities.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    Optional<Item> findItemByWowheadId(String wowheadId);
    Optional<Item> deleteById(String wowheadId);



}
