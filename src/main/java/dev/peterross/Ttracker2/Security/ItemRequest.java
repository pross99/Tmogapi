package dev.peterross.Ttracker2.Security;

public class ItemRequest {
    private String wowheadId;
    private String itemName;
    private String slot;
    private String wowHeadLink;
    private String expansion;
    private String location;
    private String backdrops;
    private String userId;
    private Boolean completed;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
