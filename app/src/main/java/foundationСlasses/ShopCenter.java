package foundationСlasses;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/14/2015.
 */
public class ShopCenter {

    String _id;
    String cityId;
    String name;
    Coordinates coordinates;
    ArrayList<Floor> floors;
    Bitmap centerImage;

    public ShopCenter( String cityId, String name, Coordinates coordinates) {

        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;

    }

    public ShopCenter( String _id, String cityId, String name, Coordinates coordinates,Bitmap centerImage) {

        this._id = _id;
        this.cityId = cityId;
        this.name = name;
        this.coordinates = coordinates;
        floors = new ArrayList<Floor>();
        this.centerImage = centerImage;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public String get_id() {
        return _id;

    }

    public Bitmap getCenterImage() {
        return centerImage;
    }

    public void setCenterImage(Bitmap centerImage) {
        this.centerImage = centerImage;
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


    public void addFloor(Floor floor) {

        floors.add(floor);

    }


}
