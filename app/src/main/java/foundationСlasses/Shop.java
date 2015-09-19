package foundationСlasses;

/**
 * Created by Sergei on 9/14/2015.
 */
public class Shop {

    String _id;
    String cityId;
    String shopCenterId;
    String floorId;
    String name;



    public Shop(String _id,String cityId, String shopCenterId, String floorId,  String name) {
        this._id = _id;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        this.floorId = floorId;
        this.name = name;

    }
    public Shop(String cityId, String shopCenterId, String floorId, String name) {


        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        this.floorId = floorId;
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

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}
