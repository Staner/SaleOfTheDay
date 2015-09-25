package foundationСlasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

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
    Bitmap floorMaskImage;

    public Floor(String _id, String name, String cityId, String shopCenterId) {
        this._id = _id;
        this.name = name;
        this.cityId = cityId;
        this.shopCenterId = shopCenterId;
        shops = new ArrayList<Shop>();

    }

    public Floor() {
    }

    public Bitmap getFloorMaskImage() {
        return floorMaskImage;
    }

    public void setFloorMaskImage(Bitmap floorMaskImage) {
        this.floorMaskImage = floorMaskImage;
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

    public void addMapImage(ParseObject parseObject, final Floor floor){


        final ParseFile image = (ParseFile) parseObject.get("floorMapImage");
        image.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    // data has the bytes for the image
                    Bitmap thisImage = BitmapFactory.decodeByteArray(data, 0, data.length);
                    floor.setFloorMapImage(thisImage);

                }
            }

        });


    }
    public void addMaskImage(ParseObject parseObject, final Floor floor) {


        final ParseFile image = (ParseFile) parseObject.get("floorMaskImage");
        image.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    // data has the bytes for the image

                    Log.d("set image", "set in mask");
                    Bitmap thisImage = BitmapFactory.decodeByteArray(data, 0, data.length);
                    floor.setFloorMaskImage(thisImage);

                }
            }

        });

    }

}
