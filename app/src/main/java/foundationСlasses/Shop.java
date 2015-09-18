package foundationСlasses;

/**
 * Created by Sergei on 9/14/2015.
 */
public class Shop {

    String _id;
    String cityId;
    String shopCenterId;
    String name;
    String floor;


    public Shop(String _id,String cityId, String shopCenterId, String name, String floor) {
        this._id = _id;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        this.name = name;
        this.floor = floor;
    }
    public Shop(String cityId, String shopCenterId, String name, String floor) {

        this.floor = floor;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        this.name = name;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getShopCenterId() {
        return shopCenterId;
    }

    public void setShopCenterId(String shopCenterId) {
        this.shopCenterId = shopCenterId;
    }


}
