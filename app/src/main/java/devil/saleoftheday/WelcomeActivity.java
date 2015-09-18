package devil.saleoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import foundationСlasses.City;
import foundationСlasses.Coordinates;
import foundationСlasses.ParseData;
import foundationСlasses.Shop;
import foundationСlasses.ShopCenter;

public class WelcomeActivity extends AppCompatActivity {


    public static ParseData PARSE_DATA;

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
                }
            }

        });


        ParseQuery<ParseObject> ShopCenterQuery = new ParseQuery<ParseObject>("ShopCenter");

        ShopCenterQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null && parseObjects != null) {
                    for (ParseObject parseObject : parseObjects) {

                        Log.d("parseObjects:", parseObjects.size()+" shopCenters");

                        ShopCenter shopCenter = new ShopCenter(
                                parseObject.getObjectId(),
                                parseObject.getString("cityId"),
                                parseObject.getString("name"),
                                new Coordinates(
                                        parseObject.getDouble("latMin"),
                                        parseObject.getDouble("latMax"),
                                        parseObject.getDouble("lonMin"),
                                        parseObject.getDouble("lonMax")),
                                        parseObject.getString("floors"));

                        Log.d("parse_DATA:", PARSE_DATA.getData().size()+"");
                        for(City city: PARSE_DATA.getData()){

                            Log.d("city.get_id():", city.get_id());
                            Log.d("(shopCenter.getCityId", shopCenter.getCityId()+"");

                            if (city.get_id().equals(shopCenter.getCityId()))
                                city.addShopCenter(shopCenter);
                            Log.d("shopCenters:", "shopCenter++");
                        }


                    }


                }
            }

        });


        ParseQuery<ParseObject> ShopQuery = new ParseQuery<ParseObject>("Shop");

        ShopQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null && parseObjects != null) {
                    for (ParseObject parseObject : parseObjects) {

                        Log.d("parseObjects:", parseObjects.size()+" shops");

                        Shop shop = new Shop(
                                parseObject.getObjectId(),
                                parseObject.getString("cityId"),
                                parseObject.getString("shopCenterId"),
                                parseObject.getString("name"),
                                parseObject.getString("floor"));


                        for(City city: PARSE_DATA.getData()){

                            if (city.get_id().equals(shop.getCityId())){

                               for (ShopCenter shopCenter: city.getShopCenters()){

                                   if (shopCenter.get_id().equals(shop.getShopCenterId()))
                                       Log.d("shopCenters:", "shop++");
                                       shopCenter.addShop(shop);

                               }




                        }

                    }




                    }
                }
            }

        });



    }


}
