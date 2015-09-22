package devil.saleoftheday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import foundationСlasses.Floor;


// here you can create and push to Parse city or shop center or shop

// before run, change in AndroidManifest  that this activity be first in application

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "eOBp2JZ5GHRCXHJ9A91Y7wYNE6Iimjv61WQNprdM", "FcgiYI8pGWlEtUFCusXWDpbosDjCmWTdhHffjdUz");


       /* // create city
        City city = new City("באר שבע",new Coordinates(31.213764,31.299099,34.742583,34.839550));

        ParseObject createCity = new ParseObject("City");
        createCity.put("name", city.getName());
        createCity.put("latMin", city.getCoordinates().getLatMin());
        createCity.put("latMax", city.getCoordinates().getLatMax());
        createCity.put("lonMin", city.getCoordinates().getLonMin());
        createCity.put("lonMax", city.getCoordinates().getLonMax());

        createCity.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });*/




      /*  // create shop center

        ShopCenter shopCenter = new ShopCenter("TUNYw94TcM", "וואן פלאזה", new Coordinates(31.245505,31.248313,34.810872,34.813362),"1");

        ParseObject createShopCenter = new ParseObject("ShopCenter");
        createShopCenter.put("name", shopCenter.getName());
        createShopCenter.put("cityId", shopCenter.getCityId());
        createShopCenter.put("latMin", shopCenter.getCoordinates().getLatMin());
        createShopCenter.put("latMax", shopCenter.getCoordinates().getLatMax());
        createShopCenter.put("lonMin", shopCenter.getCoordinates().getLonMin());
        createShopCenter.put("lonMax", shopCenter.getCoordinates().getLonMax());

        createShopCenter.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }


        });

*/


         // create floor
        Floor floor = new Floor("2","TUNYw94TcM","UWl0xm702O");

        ParseObject createFloor = new ParseObject("Floor");
        createFloor.put("name", floor.getName());
        createFloor.put("cityId", floor.getCityId());
        createFloor.put("shopCenterId", floor.getShopCenterId());


        createFloor.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });


/*
        // create shop

        Shop shop = new Shop("TUNYw94TcM","UWl0xm702O", "H&M", "1");

        ParseObject createShop = new ParseObject("Shop");
        createShop.put("name", shop.getName());
        createShop.put("cityId", shop.getCityId());
        createShop.put("shopCenterId", shop.getShopCenterId());


        createShop.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }


        });*/
    }
}
