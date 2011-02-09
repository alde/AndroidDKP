
package com.unknown.entity;

public class CharacterItem {

        private int id;
        private String name;
        private double price;
        boolean heroic;
        private String quality;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setHeroic(boolean heroic) {
                this.heroic = heroic;
        }

        public boolean getHeroic() {
                return heroic;
        }

        public String getQuality() {
                return quality;
        }

        public void setQuality(String quality) {
                this.quality = quality;
        }
}
