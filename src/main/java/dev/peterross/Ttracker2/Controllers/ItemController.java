package dev.peterross.Ttracker2.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import dev.peterross.Ttracker2.Services.ItemService;
import java.util.List;
import java.util.Optional;
import dev.peterross.Ttracker2.Entities.Item;


@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "http://localhost:3000")
@Async
public class ItemController {
String errorMessage = "WOWHEAD ID already exists";
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<List<Item>>(itemService.allItems(), HttpStatus.OK);
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