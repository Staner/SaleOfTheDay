package devil.saleoftheday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import foundationСlasses.City;
import foundationСlasses.Coordinates;
import foundationСlasses.Floor;
import foundationСlasses.ParseData;
import foundationСlasses.Shop;
import foundationСlasses.ShopCenter;

public class WelcomeActivity extends AppCompatActivity {


    public static ParseData PARSE_DATA;

    Bitmap centerImage;
    Bitmap floorMapImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "eOBp2JZ5GHRCXHJ9A91Y7wYNE6Iimjv61WQNprdM", "FcgiYI8pGWlEtUFCusXWDpbosDjCmWTdhHffjdUz");
        PARSE_DATA = new ParseData();



        loadDataFromParse();
        setTimer();




    }



    private void setTimer() {

        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            public void run() {
                WelcomeActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        addNewView();
                    }
                });
            }
        }, 5000);
    }

    private void addNewView() {

        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void loadDataFromParse() {



        ParseQuery<ParseObject> CityQuery = new ParseQuery<ParseObject>("City");

        CityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null && parseObjects != null) {
                    for (ParseObject parseObject : parseObjects) {

                    City city = new City(
                            parseObject.getObjectId(),
                            parseObject.getString("name"),
                            new Coordinates(
                                    parseObject.getDouble("latMin"),
                                    parseObject.getDouble("latMax"),
                                    parseObject.getDouble("lonMin"),
                                    parseObject.getDouble("lonMax")));

                    PARSE_DATA.addCity(city);


                    }
                  nextloadShopCenters();
                }
            }

            private void nextloadShopCenters() {

                ParseQuery<ParseObject> ShopCenterQuery = new ParseQuery<ParseObject>("ShopCenter");

                ShopCenterQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(final List<ParseObject> parseObjects, ParseException e) {
                        if (e == null && parseObjects != null) {

                            Log.d("city:", PARSE_DATA.getData().size() + "");
                            for (final ParseObject parseObject : parseObjects) {

                                ParseFile imageFile = (ParseFile) parseObject.get("centerImage");
                                imageFile.getDataInBackground(new GetDataCallback() {
                                    public void done(byte[] data, ParseException e) {
                                        if (e == null) {
                                            // data has the bytes for the image
                                            centerImage = BitmapFactory.decodeByteArray(data, 0, data.length);

                                            Log.d("parseObjects ccenter:", parseObjects.size() + " shopCenters");

                                            ShopCenter shopCenter = new ShopCenter(
                                                    parseObject.getObjectId(),
                                                    parseObject.getString("cityId"),
                                                    parseObject.getString("name"),
                                                    new Coordinates(
                                                            parseObject.getDouble("latMin"),
                                                            parseObject.getDouble("latMax"),
                                                            parseObject.getDouble("lonMin"),
                                                            parseObject.getDouble("lonMax")),
                                                    centerImage);


                                            for (City city : PARSE_DATA.getData()) {

                                                if (city.get_id().equals(shopCenter.getCityId())) {
                                                    city.addShopCenter(shopCenter);
                                                    Log.d("city add center:", "city add center++");
                                                }
                                            }
                                        } else {
                                            // something went wrong


                                        }
                                    }
                                });

                            }

                            nextloadFloors();
                        }
                    }

                    private void nextloadFloors() {

                        ParseQuery<ParseObject> FloorShopCenterQuery = new ParseQuery<ParseObject>("Floor");

                        FloorShopCenterQuery.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(final List<ParseObject> parseObjects, ParseException e) {
                                if (e == null && parseObjects != null) {
                                    for (final ParseObject parseObject : parseObjects) {

                                        ParseFile imageFile = (ParseFile) parseObject.get("floorMapImage");
                                        imageFile.getDataInBackground(new GetDataCallback() {
                                            public void done(byte[] data, ParseException e) {
                                                if (e == null) {
                                                    // data has the bytes for the image
                                                    floorMapImage = BitmapFactory.decodeByteArray(data, 0, data.length);

                                                    Log.d("parseObjects floor:", parseObjects.size() + " floors");

                                                    Floor floor = new Floor(
                                                            parseObject.getObjectId(),
                                                            parseObject.getString("name"),
                                                            parseObject.getString("cityId"),
                                                            parseObject.getString("shopCenterId"),
                                                            floorMapImage
                                                    );


                                                    for (City city : PARSE_DATA.getData()) {

                                                        Log.d("city.get_id():", city.get_id());
                                                        for (ShopCenter shopCenter : city.getShopCenters())

                                                            if (shopCenter.get_id().equals(floor.getShopCenterId())) {
                                                                shopCenter.addFloor(floor);
                                                                Log.d("floor:", "floor++");
                                                            }
                                                    }
                                                } else {
                                                    // something went wrong


                                                }
                                            }
                                        });

                                    }
                                    nextloadShops();

                                }
                            }

                            private void nextloadShops() {

                                ParseQuery<ParseObject> ShopQuery = new ParseQuery<ParseObject>("Shop");

                                ShopQuery.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> parseObjects, ParseException e) {
                                        if (e == null && parseObjects != null) {
                                            for (ParseObject parseObject : parseObjects) {

                                                Log.d("parseObjects shop:", parseObjects.size() + " shops");

                                                Shop shop = new Shop(
                                                        parseObject.getObjectId(),
                                                        parseObject.getString("cityId"),
                                                        parseObject.getString("shopCenterId"),
                                                        parseObject.getString("floorId"),
                                                        parseObject.getString("name"));


                                                for (City city : PARSE_DATA.getData()) {
                                                    if (city.get_id().equals(shop.getCityId())) {
                                                        for (ShopCenter shopCenter : city.getShopCenters()) {
                                                            if (shopCenter.get_id().equals(shop.getShopCenterId())) {
                                                                for (Floor floor : shopCenter.getFloors()) {
                                                                    Log.d("floor:", floor.get_id());
                                                                    Log.d("shop floor id:", shop.getFloorId());

                                                                    if (floor.get_id().equals(shop.getFloorId())) {
                                                                        floor.addShop(shop);
                                                                        Log.d("shop:", "shop++");


                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }


                                            }
                                        }
                                    }

                                });


                            }

                        });


                    }

                });

            }


        });


    }


}
