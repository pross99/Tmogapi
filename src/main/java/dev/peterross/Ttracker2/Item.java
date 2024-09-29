package dev.peterross.Ttracker2;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Item {
    @Id
    private ObjectId id;

    private String wowheadId;
    private String itemName;
    private String slot;
    private String wowHeadLink;
    private String expansion;
    private String location;
    private String backdrops;

    public Item(String itemName, String wowheadId, String slot, String wowHeadLink, String location, String backdrops, String expansion) {
        this.wowheadId = wowheadId;

        this.itemName = itemName;

        this.slot = slot;
        this.wowHeadLink = wowHeadLink;
        this.expansion = expansion;
        this.location = location;
        this.backdrops = backdrops;
    }

    



}
