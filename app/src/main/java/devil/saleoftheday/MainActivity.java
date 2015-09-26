package devil.saleoftheday;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import foundationСlasses.City;
import foundationСlasses.ColorTool;
import foundationСlasses.Floor;
import foundationСlasses.Shop;
import foundationСlasses.ShopCenter;

public class MainActivity extends Activity implements View.OnClickListener {


    Button city, center, floor;

    int counterEnter = 0;

    City selectedCity = new City();
    ShopCenter selectedShopCenter = new ShopCenter();
    Floor selectedFloor = new Floor();

    private LocationManager locationManager;

    ImageView iv;
    ImageView ivMask;


    ViewGroup vg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vg = (ViewGroup) findViewById(R.id.mainLayout);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        Log.d("shopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(0).getShopCenters().size() + "");
        Log.d("shopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(1).getShopCenters().size() + "");
        Log.d("parse size", WelcomeActivity.PARSE_DATA.getData().size() + "");

        city = (Button) findViewById(R.id.btncity);
        city.setOnClickListener(this);
        center = (Button) findViewById(R.id.btncenter);
        center.setOnClickListener(this);
        floor = (Button) findViewById(R.id.btnfloor);
        floor.setOnClickListener(this);

        iv = (ImageView) findViewById(R.id.image);
        ivMask = (ImageView) findViewById(R.id.image_areas);

    }


    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000 * 10, 5, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 5,
                locationListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

if (counterEnter==0) {
    counterEnter++;
    checkLocation(location);
    vg.invalidate();
}


        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    private void checkLocation(Location location){

        double latNow = location.getLatitude();
        double lonNow =  location.getLongitude();


        for (City thisCity: WelcomeActivity.PARSE_DATA.getData()){

            if ((thisCity.getCoordinates().getLatMin()<= latNow)&&(latNow<=thisCity.getCoordinates().getLatMax())&&
                    ((thisCity.getCoordinates().getLonMin()<=lonNow)&&(lonNow<=thisCity.getCoordinates().getLonMax())) ){

                city.setText(thisCity.getName());

                Log.d("name", thisCity.getName());
                Log.d("shop center size ", thisCity.getShopCenters().size() + "");

                if (thisCity.getShopCenters().size() > 0) {

                    center.setEnabled(true);

                    selectedCity.setName(thisCity.getName());
                    selectedCity.set_id(thisCity.get_id());
                    selectedCity.setCoordinates(thisCity.getCoordinates());
                    selectedCity.setShopCenters(thisCity.getShopCenters());

                    Log.d("selected name:", selectedCity.getName());
                    Log.d("ccity center:", selectedCity.getShopCenters().size() + "");


                } else {
                    center.setEnabled(false);
                    floor.setEnabled(false);
                }

                for (ShopCenter thisShopCenter: thisCity.getShopCenters()){

                    if ((thisShopCenter.getCoordinates().getLatMin()<= latNow)&&(latNow<=thisShopCenter.getCoordinates().getLatMax())&&
                            ((thisShopCenter.getCoordinates().getLonMin()<=lonNow)&&(lonNow<=thisShopCenter.getCoordinates().getLonMax())) ){

                        center.setText(thisShopCenter.getName());

                        if (thisShopCenter.getFloors().size() < 1) {

                            floor.setEnabled(false);

                            fillSelectedShopCenter(thisShopCenter);

                            iv.setImageBitmap(selectedShopCenter.getCenterImage());

                        } else {
                            floor.setEnabled(true);
                            fillSelectedShopCenter(thisShopCenter);
                            iv.setImageBitmap(selectedShopCenter.getCenterImage());

                        }
                    }
                    }
                }
            }
        }

    private void fillSelectedShopCenter(ShopCenter thisShopCenter) {
        selectedShopCenter.set_id(thisShopCenter.get_id());
        selectedShopCenter.setName(thisShopCenter.getName());
        selectedShopCenter.setCityId(thisShopCenter.getCityId());
        selectedShopCenter.setCoordinates(thisShopCenter.getCoordinates());
        selectedShopCenter.setCenterImage(thisShopCenter.getCenterImage());
        selectedShopCenter.setFloors(thisShopCenter.getFloors());

    }



    private void imageCrosserMethod(){

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {

                final int action = ev.getAction();
                boolean handledHere = false;

                final int evX = (int) ev.getX();
                final int evY = (int) ev.getY();

                int nextImage = -1;

                ImageView imageView = (ImageView) v.findViewById(R.id.image);
                if (imageView == null) return false;

                Integer tagNum = (Integer) imageView.getTag();

                //Context context = getApplicationContext();
                //Drawable drawable = context.getResources().getDrawable(R.drawable.my_image);
                // convert drawable to bitmap
                // Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                // convert bitmap to drawable

                Drawable d = new BitmapDrawable(selectedFloor.getFloorMapImage());

                int currentResource = (tagNum == null) ? d.getLevel() : tagNum.intValue();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (currentResource == d.getLevel()) {
                            nextImage = d.getLevel();
                            handledHere = true;
                        } else handledHere = true;
                        break;

                    case MotionEvent.ACTION_UP:


                        int touchColor = getHotspotColor(R.id.image_areas, evX, evY);
                        ColorTool ct = new ColorTool();
                        int tolerance = 25;


                        for (Shop shop : selectedFloor.getShops()) {

                            Log.d("count shops in floor", "+1");
/*
                    Log.d("touchColor", touchColor + "");
                    Log.d("colorShop", shop.getColorTouch() + "");
                    Log.d("colorShop", shop.getName() + "");
                    Log.d("tolerance", tolerance + "");*/

                            if (ct.closeMatch(Color.parseColor(shop.getColorTouch()), touchColor, tolerance)) {
                                Log.d("colorShop", shop.getName() + "");
                                toast(shop.getName());
                                return true;
                            }
                        }
                        if (currentResource == nextImage) {
                            nextImage = d.getLevel();
                        }
                        handledHere = true;
                        break;

                    default:
                        handledHere = false;
                }


                if (handledHere) {

                    if (nextImage > 0) {
                        imageView.setImageResource(nextImage);
                        imageView.setTag(nextImage);
                    }
                }
                return handledHere;

            }
        });


    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d ("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }
    public void toast (String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    } // end toast


    @Override
    public void onClick(View v) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                MainActivity.this);

        switch (v.getId()) {
            case R.id.btncity:

                builderSingle.setTitle("נא לבחור עיר:");
                final ArrayAdapter<String> cityArrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.select_dialog_singlechoice);
                for (City city : WelcomeActivity.PARSE_DATA.getData()) {
                    cityArrayAdapter.add(city.getName());
                }

                builderSingle.setAdapter(cityArrayAdapter,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                city.setText(WelcomeActivity.PARSE_DATA.getData().get(which).getName());

                                Log.d("name", WelcomeActivity.PARSE_DATA.getData().get(which).getName());
                                Log.d("shop center size ", WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters().size() + "");

                                if (WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters().size() > 0) {

                                    center.setEnabled(true);

                                    selectedCity.setName(WelcomeActivity.PARSE_DATA.getData().get(which).getName());
                                    selectedCity.set_id(WelcomeActivity.PARSE_DATA.getData().get(which).get_id());
                                    selectedCity.setCoordinates(WelcomeActivity.PARSE_DATA.getData().get(which).getCoordinates());
                                    selectedCity.setShopCenters(WelcomeActivity.PARSE_DATA.getData().get(which).getShopCenters());

                                    Log.d("selected name:", selectedCity.getName());
                                    Log.d("ccity center:", selectedCity.getShopCenters().size() + "");
                                } else {
                                    center.setEnabled(false);
                                    floor.setEnabled(false);
                                }

                            }
                        });
                builderSingle.show();


                break;
            case R.id.btncenter:

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

                                center.setText(selectedCity.getShopCenters().get(which).getName());

                                if (selectedCity.getShopCenters().get(which).getFloors().size() < 1) {

                                    floor.setEnabled(false);

                                    fillSelectedShopCenter(which);

                                    iv.setImageBitmap(selectedShopCenter.getCenterImage());

                                } else {
                                    floor.setEnabled(true);
                                    fillSelectedShopCenter(which);
                                    iv.setImageBitmap(selectedShopCenter.getCenterImage());

                                }


                            }

                            private void fillSelectedShopCenter(int which) {
                                selectedShopCenter.set_id(selectedCity.getShopCenters().get(which).get_id());
                                selectedShopCenter.setName(selectedCity.getShopCenters().get(which).getName());
                                selectedShopCenter.setCityId(selectedCity.getShopCenters().get(which).getCityId());
                                selectedShopCenter.setCoordinates(selectedCity.getShopCenters().get(which).getCoordinates());
                                selectedShopCenter.setCenterImage(selectedCity.getShopCenters().get(which).getCenterImage());
                                selectedShopCenter.setFloors(selectedCity.getShopCenters().get(which).getFloors());


                            }
                        });
                builderSingle.show();


                break;
            case R.id.btnfloor:

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


                                selectedFloor.set_id(selectedShopCenter.getFloors().get(which).get_id());
                                selectedFloor.setName(selectedShopCenter.getFloors().get(which).getName());
                                selectedFloor.setCityId(selectedShopCenter.getFloors().get(which).getCityId());
                                selectedFloor.setShopCenterId(selectedShopCenter.getFloors().get(which).getShopCenterId());
                                selectedFloor.setFloorMapImage(selectedShopCenter.getFloors().get(which).getFloorMapImage());
                                selectedFloor.setFloorMaskImage(selectedShopCenter.getFloors().get(which).getFloorMaskImage());
                                selectedFloor.setShops(selectedShopCenter.getFloors().get(which).getShops());

                                iv.setImageBitmap(selectedFloor.getFloorMapImage());
                                ivMask.setImageBitmap(selectedFloor.getFloorMaskImage());
                                floor.setText(selectedShopCenter.getFloors().get(which).getName());
                                imageCrosserMethod();


                            }
                        });
                builderSingle.show();


                break;
        }



    }
}

