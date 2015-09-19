package devil.saleoftheday;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import foundationСlasses.City;
import foundationСlasses.Floor;
import foundationСlasses.Shop;
import foundationСlasses.ShopCenter;

public class MainActivity extends Activity {


    Button city, center, floor;

    City selectedCity;
    ShopCenter selectedShopCenter;
    Floor selectedFloor;





    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("sshopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(0).getShopCenters().size() + "");
        Log.d("shopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(1).getShopCenters().size() + "");
        Log.d("parse size", WelcomeActivity.PARSE_DATA.getData().size() + "");

        city = (Button) findViewById(R.id.btncity);
        center = (Button) findViewById(R.id.btncenter);
        floor = (Button) findViewById(R.id.btnfloor);


        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                        MainActivity.this);

                builderSingle.setTitle("נא לבחור עיר:");
                final ArrayAdapter<String> cityArrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.select_dialog_singlechoice);
                for (City city: WelcomeActivity.PARSE_DATA.getData()){
                    cityArrayAdapter.add(city.getName());
                }

                builderSingle.setAdapter(cityArrayAdapter,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                center.setEnabled(true);


                                Log.d("name", WelcomeActivity.PARSE_DATA.getData().get(which).getName());
                                Log.d("shop center size ", WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters().size() + "");

                                if (WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters().size() > 0) {

                                    center.setEnabled(true);


                                    selectedCity = new City(
                                            WelcomeActivity.PARSE_DATA.getData().get(which).get_id(),
                                            WelcomeActivity.PARSE_DATA.getData().get(which).getName(),
                                            WelcomeActivity.PARSE_DATA.getData().get(which).getCoordinates());
                                    selectedCity.setShopCenters(WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters());
                                    //  Log.d("centers", WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters().size()+"");
                                    Log.d("selected name:", selectedCity.getName());
                                    Log.d("ccity center:", selectedCity.getShopCenters().size()+"");
                                }

                                else{
                                    center.setEnabled(false);
                                    floor.setEnabled(false);
                                }

                            }
                        });
                builderSingle.show();


            }
        });


        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                        MainActivity.this);

                builderSingle.setTitle("נא לבחור מרכז קניות");
                final ArrayAdapter<String> shopCenterArrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.select_dialog_singlechoice);
                for (ShopCenter shopCenter : selectedCity.getShopCenters()) {
                    shopCenterArrayAdapter.add(shopCenter.getName());
                }

                builderSingle.setAdapter(shopCenterArrayAdapter,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                if ( selectedCity.getShopCenters().get(which).getFloors().size() < 1){

                                    floor.setEnabled(false);
                                    selectedShopCenter = new ShopCenter(
                                            selectedCity.getShopCenters().get(which).get_id(),
                                            selectedCity.getShopCenters().get(which).getCityId(),
                                            selectedCity.getShopCenters().get(which).getName(),
                                            selectedCity.getShopCenters().get(which).getCoordinates());
                                    selectedShopCenter.setFloors(selectedCity.getShopCenters().get(which).getFloors());
                                }

                                else   floor.setEnabled(true);
                                selectedShopCenter = new ShopCenter(
                                        selectedCity.getShopCenters().get(which).get_id(),
                                        selectedCity.getShopCenters().get(which).getCityId(),
                                        selectedCity.getShopCenters().get(which).getName(),
                                        selectedCity.getShopCenters().get(which).getCoordinates());
                                selectedShopCenter.setFloors(selectedCity.getShopCenters().get(which).getFloors());




                            }
                        });
                builderSingle.show();


            }
        });

        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                        MainActivity.this);

                builderSingle.setTitle("floors:");
                final ArrayAdapter<String> floorsArrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.select_dialog_singlechoice);
                for (Floor floor : selectedShopCenter.getFloors()) {
                    floorsArrayAdapter.add(floor.getName());
                }

                builderSingle.setAdapter(floorsArrayAdapter,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                selectedFloor = new Floor(
                                        selectedShopCenter.getFloors().get(which).get_id(),
                                        selectedShopCenter.getFloors().get(which).getName(),
                                        selectedShopCenter.getFloors().get(which).getCityId(),
                                        selectedShopCenter.getFloors().get(which).getShopCenterId());
                                selectedFloor.setShops(selectedShopCenter.getFloors().get(which).getShops());

                                TextView textView = (TextView) findViewById(R.id.textView);
                                String shops = "";
                                for (Shop shop: selectedFloor.getShops()){
                                   shops = shops + shop.getName();
                                }
                                textView.setText(shops);


                            }
                        });
                builderSingle.show();


            }
        });




    }


}