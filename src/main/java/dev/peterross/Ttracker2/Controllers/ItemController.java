package dev.peterross.Ttracker2.Controllers;
import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.peterross.Ttracker2.Entities.Item;
import dev.peterross.Ttracker2.Services.ItemService;


@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "http://localhost:3000")
@Async
public class ItemController {
String errorMessage = "WOWHEAD ID already exists";
    @Autowired
    private ItemService itemService;




    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllItems(@PathVariable String userId) {

     try {
            // Convert String to ObjectId, will throw IllegalArgumentException if invalid
            ObjectId userObjectId = new ObjectId(userId);
            
            // Fetch items for the user
            List<Item> items = itemService.allItems(userObjectId);


            return new ResponseEntity<>(items, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // Return bad request if userId is not a valid ObjectId
            return new ResponseEntity<>("Invalid userId format", HttpStatus.BAD_REQUEST);
        }
        
       
    }

    @GetMapping("/{wowheadId}")
    public ResponseEntity<Optional<Item>> getSingleItem(@PathVariable String wowheadId) {
        return new ResponseEntity<Optional<Item>>(itemService.singleItem(wowheadId), HttpStatus.OK);
    }


    @PostMapping("/post")
    public ResponseEntity<Item> createItem(@RequestBody Item item) throws Exception {
        try {
            Item createdItem = itemService.createItem(item);
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        } catch (Exception ex) {
            // Handle the duplicate item exception
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict
        }
    }
    

    @DeleteMapping("/{wowheadId}")
    public ResponseEntity<Void> deleteItem(@PathVariable String wowheadId) {
        boolean isDeleted = itemService.deleteById(wowheadId);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
            
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
       
    }

    @PutMapping("/{wowheadId}")
    public ResponseEntity<Item> updateByWowheadId(@PathVariable String wowheadId, @RequestBody Item newItem) {
        Optional<Item> updatedItem = itemService.updateByWowheadId(wowheadId, newItem);
        
   
         return updatedItem.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());

    }
}