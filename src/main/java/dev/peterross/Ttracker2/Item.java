package dev.peterross.Ttracker2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private ObjectId id;

    private String wowheadId;
    private String itemName;
    private String slot;
    private String wowHeadLink;
    private String Expansion;
    private String location;


    public Item(String itemName, String wowheadId, String slot, String wowHeadLink, String location, String backdrops, String expansion) {
        this.wowheadId = wowheadId;

        this.itemName = itemName;

        this.slot = slot;
        this.wowHeadLink = wowHeadLink;
        this.Expansion = expansion;
        this.location = location;
        this.backdrops = backdrops;
    }

    private String backdrops;



}
