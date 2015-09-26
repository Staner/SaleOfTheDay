package foundationСlasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import devil.saleoftheday.R;

/**
 * Created by Sergei on 9/25/2015.
 */
public class TouchColor {

    public boolean imageCrosserMethod(Floor selectedFloor, MotionEvent ev, View v, Context context, int image_areas){


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

                        int touchColor = getHotspotColor(image_areas, v, evX, evY);


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
                        Log.d("touchColor",touchColor+"");
                        Log.d("colorShop",selectedFloor.getShops().get(action).getColorTouch()+"");
                        Log.d("colorShop",selectedFloor.getShops().get(action).getName()+"");
                        Log.d("tolerance",tolerance+"");


                        if (ct.closeMatch(Color.parseColor(selectedFloor.getShops().get(action).getColorTouch()), touchColor, tolerance)) {
                            //    if (ct.closeMatch(Color.parseColor(selectedFloor.getShops().get(action).getColorTouch()), touchColor, tolerance))
                            toast(selectedFloor.getShops().get(action).getName(), context);
                        } else {
                            toast("try another object", context);
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

    public int getHotspotColor (int hotspotId, View v, int x, int y) {
        ImageView img = (ImageView) v.findViewById (hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", "Hot spot image not found");
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
    public void toast (String msg, Context context)
    {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    } // end toast


}



   /* private void imageCrosserMethod() {

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {

                TouchColor touchColor = new TouchColor();
                return touchColor.imageCrosserMethod(selectedFloor,ev,v, MainActivity.context, R.id.image_areas);
            }
        });


    }*/

