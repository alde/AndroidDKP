package com.unknown.entity.json;

public class RaidItem {

        private boolean heroic;
        private int id;
        private String looter;
        private String name;
        private double price;
        private String quality;

        public RaidItem() {
        	
        }
        
        public RaidItem(String itemname, String looter, int itemid, double price, boolean isheroic, String quality) {
                this.name = itemname;
                this.looter = looter;
                this.id = itemid;
                this.price = price;
                this.heroic = isheroic;
                this.quality = quality;
        }

        public void setId(Integer integer) {
        }

        public boolean isHeroic() {
                return heroic;
        }

        public void setHeroic(boolean heroic) {
                this.heroic = heroic;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getLooter() {
                return looter;
        }

        public void setLooter(String looter) {
                this.looter = looter;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getQuality() {
                return quality;
        }

        public void setQuality(String quality) {
                this.quality = quality;
        }
}
