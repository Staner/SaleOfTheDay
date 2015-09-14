package foundationСlasses;

/**
 * Created by Sergei on 9/14/2015.
 */
public class Shop {

    int _id;
    String shopCenterId;
    String name;

    public Shop(int _id, String shopCenterId, String name) {
        this._id = _id;
        this.shopCenterId = shopCenterId;
        this.name = name;
    }
    public Shop( String shopCenterId, String name) {

        this.shopCenterId = shopCenterId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getShopCenterId() {
        return shopCenterId;
    }

    public void setShopCenterId(String shopCenterId) {
        this.shopCenterId = shopCenterId;
    }
}
