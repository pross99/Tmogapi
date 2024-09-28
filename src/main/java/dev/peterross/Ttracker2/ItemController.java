package dev.peterross.Ttracker2;

import java.io.Console;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "http://localhost:3000")
@Async
public class ItemController {

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
    public ResponseEntity<Item> createItem(@RequestBody Item item)  {
        return new ResponseEntity<Item>(itemService.createItem(item),HttpStatus.CREATED);

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



}
