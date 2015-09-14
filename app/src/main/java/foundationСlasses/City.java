package foundationСlasses;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/14/2015.
 */
public class City {

    int _id;
    String name;
    Coordinates coordinates;
    ArrayList<ShopCenter> shopCenters;

    public City(int _id, String name, Coordinates coordinates) {
        this._id = _id;
        this.name = name;
        this.coordinates = coordinates;

        shopCenters = new ArrayList<ShopCenter>();
    }


    // create city constructor
    public City( String name, Coordinates coordinates) {

        this.name = name;
        this.coordinates = coordinates;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public ArrayList<ShopCenter> getShopCenters() {
        return shopCenters;
    }

    public void setShopCenters(ArrayList<ShopCenter> shopCenters) {
        this.shopCenters = shopCenters;
    }
}
