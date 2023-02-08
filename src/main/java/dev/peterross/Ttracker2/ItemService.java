package dev.peterross.Ttracker2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Item> allItems()  {return itemRepository.findAll();}


    public Optional<Item> singleItem(String wowheadId) {return itemRepository.findItemBywowheadId(wowheadId);}




    public Item createItem(Item item) {
        Item saved = itemRepository.save(item);
        return item;
    }
}
