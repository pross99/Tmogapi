package dev.peterross.Ttracker2.Services;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import dev.peterross.Ttracker2.Entities.Item;
import dev.peterross.Ttracker2.Repositories.ItemRepository;
@Service
public class ItemService {



    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Item> allItems(ObjectId userId)  {
        List<Item> items = itemRepository.findByUserId(userId);

        if (items.isEmpty()) {
            System.out.println("No items found for userId: " + userId);
        } else {
            System.out.println("Items found: " + items);
        }

        return items;
    }


    public Optional<Item> singleItem(String wowheadId) {
        
        return itemRepository.findItemByWowheadId(wowheadId);
    
    }


    public boolean deleteById(String wowheadId) {
        Optional<Item> item = itemRepository.findItemByWowheadId(wowheadId);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return true; // Successfully deleted
        }
        return false; // Item not found, couldn't delete
    }

    public Item createItem(Item item) throws Exception {
        // CHECK IF AN ITEM WITH THE SAME WOWHEAD ID ALREADY EXISTS
        // NEED TO ADD FUNTCION TO CHECK IF THE WOWHEAD ID ALREADY EXISTS ONLY ON USERID
        Optional<Item> existingItem = itemRepository.findByUserIdAndWowheadId(item.getUserId(), item.getWowheadId());

        if(existingItem.isPresent()) {
            throw new Exception("An item with this wowhead ID already exists.");

        }

        return itemRepository.save(item);
    }

    public Optional<Item> updateByWowheadId(String wowheadId, Item newItem) {

        return itemRepository.findItemByWowheadId(wowheadId).map(item-> {
            item.setItemName(newItem.getItemName());
            item.setSlot(newItem.getSlot());
            item.setWowHeadLink(newItem.getWowHeadLink());
            item.setExpansion(newItem.getExpansion());
            item.setLocation(newItem.getLocation());
            item.setBackdrops(newItem.getBackdrops());
            item.setUserId(newItem.getUserId());
            item.setCompleted(newItem.getCompleted());
            return itemRepository.save(item);

        });
    }



}
