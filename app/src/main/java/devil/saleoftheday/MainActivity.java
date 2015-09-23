package devil.saleoftheday;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import foundationСlasses.City;
import foundationСlasses.ColorTool;
import foundationСlasses.Floor;
import foundationСlasses.Shop;
import foundationСlasses.ShopCenter;

public class MainActivity extends Activity {


    Button city, center, floor;

    City selectedCity;
    ShopCenter selectedShopCenter;
    Floor selectedFloor;

    ImageView iv;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("shopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(0).getShopCenters().size() + "");
        Log.d("shopCenetr:", WelcomeActivity.PARSE_DATA.getData().get(1).getShopCenters().size() + "");
        Log.d("parse size", WelcomeActivity.PARSE_DATA.getData().size() + "");

        city = (Button) findViewById(R.id.btncity);
        center = (Button) findViewById(R.id.btncenter);
        floor = (Button) findViewById(R.id.btnfloor);

        iv = (ImageView) findViewById(R.id.image);


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
                                city.setText(WelcomeActivity.PARSE_DATA.getData().get(which).getName());

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

                                center.setText(selectedCity.getShopCenters().get(which).getName());

                                if ( selectedCity.getShopCenters().get(which).getFloors().size() < 1){

                                    floor.setEnabled(false);
                                    selectedShopCenter = new ShopCenter(
                                            selectedCity.getShopCenters().get(which).get_id(),
                                            selectedCity.getShopCenters().get(which).getCityId(),
                                            selectedCity.getShopCenters().get(which).getName(),
                                            selectedCity.getShopCenters().get(which).getCoordinates(),
                                            selectedCity.getShopCenters().get(which).getCenterImage());
                                    selectedShopCenter.setFloors(selectedCity.getShopCenters().get(which).getFloors());

                                    iv.setImageBitmap(selectedShopCenter.getCenterImage());

                                }

                                else   floor.setEnabled(true);
                                selectedShopCenter = new ShopCenter(
                                        selectedCity.getShopCenters().get(which).get_id(),
                                        selectedCity.getShopCenters().get(which).getCityId(),
                                        selectedCity.getShopCenters().get(which).getName(),
                                        selectedCity.getShopCenters().get(which).getCoordinates(),
                                        selectedCity.getShopCenters().get(which).getCenterImage());
                                selectedShopCenter.setFloors(selectedCity.getShopCenters().get(which).getFloors());

                                iv.setImageBitmap(selectedShopCenter.getCenterImage());




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
                                        selectedShopCenter.getFloors().get(which).getShopCenterId(),
                                        selectedShopCenter.getFloors().get(which).getFloorMapImage());
                                selectedFloor.setShops(selectedShopCenter.getFloors().get(which).getShops());

                                iv.setImageBitmap(selectedFloor.getFloorMapImage());
                                floor.setText(selectedShopCenter.getFloors().get(which).getName());


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
    private void imageCrosserMethod(){

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                boolean handledHere = false;

                final int action = ev.getAction();

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

                          /*    selectedShop = new Shop(
                                selectedFloor.getShops().get(action).get_id(),
                                selectedFloor.getShops().get(action).getCityId(),
                                selectedFloor.getShops().get(action).getShopCenterId(),
                                selectedFloor.getShops().get(action).getFloorId(),
                                selectedFloor.getShops().get(action).getName(),
                                selectedFloor.getShops().get(action).getColorTouch());

                        selectedFloor.setShops(selectedShopCenter.getFloors().get(action).getShops());
                       // selectedShop.setColorTouch(selectedFloor.getShops().get(action).getColorTouch());

                           // selectedFloor.getShops().get(action).getColorTouch();   */


                        if (ct.closeMatch(Color.parseColor(selectedFloor.getShops().get(action).getColorTouch()), touchColor, tolerance)) {
                            //    if (ct.closeMatch(Color.parseColor(selectedFloor.getShops().get(action).getColorTouch()), touchColor, tolerance))
                            toast(selectedFloor.getShops().get(action).getName());
                        } else {
                            toast("try another object");
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


        toast("אנא בחר איזור");


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


}