package foundationСlasses;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/14/2015.
 */
public class ShopCenter {

    String _id;
    String cityId;
    String name;
    Coordinates coordinates;
    String floors;
    ArrayList<Shop> shops;

    public ShopCenter( String cityId, String name, Coordinates coordinates, String floors) {
        this.floors = floors;
        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;

    }

    public ShopCenter( String _id, String cityId, String name, Coordinates coordinates, String floors) {

        this._id = _id;
        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;
        this.floors = floors;
        shops = new ArrayList<Shop>();
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String get_id() {
        return _id;

    }

    public void set_id(String _id) {
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


    public void addShop(Shop shop) {

        shops.add(shop);


    }
}
