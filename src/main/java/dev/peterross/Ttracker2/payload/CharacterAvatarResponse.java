package dev.peterross.Ttracker2.payload;

import java.util.List;

public class CharacterAvatarResponse {

    private List<Asset> assets;
    
    public static class Asset {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

            
    }
    public List<Asset> getAssets() {return assets;}
    public void setAssets(List<Asset> assets) {this.assets = assets;}

    public String getMainRawUrl() {
        return assets.stream()
        .filter(asset -> "main-raw".equals(asset.getKey()))
        .map(Asset::getValue)
        .findFirst()
        .orElse(null);
    }
}
