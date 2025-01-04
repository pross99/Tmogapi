package dev.peterross.Ttracker2.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Item {
    @Id
    private ObjectId id;

    @Indexed
    private String wowheadId;
    private String itemName;
    private String slot;
    private String wowHeadLink;
    private String expansion;
    private String location;
    private String backdrops;
    @Field("userId")
    private ObjectId userId;
    private Boolean completed;

    

    public ObjectId getUserId() {
        return userId;
    }
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getWowheadId() {
        return wowheadId;
    }

    public void setWowheadId(String wowheadId) {
        this.wowheadId = wowheadId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getWowHeadLink() {
        return wowHeadLink;
    }

    public void setWowHeadLink(String wowHeadLink) {
        this.wowHeadLink = wowHeadLink;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(String backdrops) {
        this.backdrops = backdrops;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }



}
