package foundationСlasses;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/18/2015.
 */
public class Floor  {

    String name;
    String _id;
    String cityId;
    String shopCenterId;
    ArrayList<Shop> shops;
    Bitmap floorMapImage;

    public Floor(String _id, String name, String cityId, String shopCenterId,Bitmap floorMapImage) {
        this._id = _id;
        this.name = name;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        shops = new ArrayList<Shop>();
        this.floorMapImage = floorMapImage;
    }

    public Floor( String name, String cityId, String shopCenterId) {

        this.name = name;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;

    }

    public Bitmap getFloorMapImage() {
        return floorMapImage;
    }

    public void setFloorMapImage(Bitmap floorMapImage) {
        this.floorMapImage = floorMapImage;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getShopCenterId() {
        return shopCenterId;
    }

    public void setShopCenterId(String shopCenterId) {
        this.shopCenterId = shopCenterId;
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
