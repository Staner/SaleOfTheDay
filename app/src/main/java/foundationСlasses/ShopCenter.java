package foundationСlasses;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/14/2015.
 */
public class ShopCenter {

    int _id;
    String cityId;
    String name;
    Coordinates coordinates;
    ArrayList<Shop> shops;

    public ShopCenter( String cityId, String name, Coordinates coordinates) {

        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;

    }

    public ShopCenter( int _id, String cityId, String name, Coordinates coordinates) {

        this._id = _id;
        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;
        shops = new ArrayList<Shop>();
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }
}
