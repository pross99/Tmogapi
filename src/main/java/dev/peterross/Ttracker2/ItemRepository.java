package dev.peterross.Ttracker2;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    Optional<Item> findItemBywowheadId(String wowheadId);
}
