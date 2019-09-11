package Model;

public class Favourite_Model_Class {

    Integer favourite__field_icon;
    String favourite_field_title_text,location_text,location_city_text;

    public Favourite_Model_Class(Integer favourite__field_icon, String favourite_field_title_text, String location_text, String location_city_text) {
        this.favourite__field_icon = favourite__field_icon;
        this.favourite_field_title_text = favourite_field_title_text;
        this.location_text = location_text;
        this.location_city_text = location_city_text;
    }

    public Integer getFavourite__field_icon() {
        return favourite__field_icon;
    }

    public void setFavourite__field_icon(Integer favourite__field_icon) {
        this.favourite__field_icon = favourite__field_icon;
    }

    public String getFavourite_field_title_text() {
        return favourite_field_title_text;
    }

    public void setFavourite_field_title_text(String favourite_field_title_text) {
        this.favourite_field_title_text = favourite_field_title_text;
    }

    public String getLocation_text() {
        return location_text;
    }

    public void setLocation_text(String location_text) {
        this.location_text = location_text;
    }

    public String getLocation_city_text() {
        return location_city_text;
    }

    public void setLocation_city_text(String location_city_text) {
        this.location_city_text = location_city_text;
    }
}
