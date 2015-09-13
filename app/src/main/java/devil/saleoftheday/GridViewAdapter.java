package devil.saleoftheday;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Sergei on 9/5/2015.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;

    public GridViewAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.corner, R.drawable.shop_down,R.drawable.shop_down, R.drawable.shop_down, R.drawable.shop_down,R.drawable.shop_down, R.drawable.shop_down, R.drawable.shop_down,R.drawable.shop_down, R.drawable.shop_down,R.drawable.corner,
            R.drawable.shop_right,R.drawable.road_horizontal, R.drawable.road_horizontal, R.drawable.road_horizontal, R.drawable.shop_left,
            R.drawable.shop_right, R.drawable.road_vertical, R.drawable.shop_down,R.drawable.road_vertical, R.drawable.shop_left,
            R.drawable.shop_right, R.drawable.road_vertical, R.drawable.shop_down, R.drawable.road_vertical, R.drawable.shop_left,
            R.drawable.shop_right, R.drawable.road_vertical, R.drawable.shop_down,R.drawable.road_vertical, R.drawable.shop_left,
            R.drawable.shop_right, R.drawable.road_horizontal, R.drawable.road_horizontal, R.drawable.road_horizontal, R.drawable.shop_left,
            R.drawable.corner, R.drawable.shop_up,R.drawable.shop_up, R.drawable.shop_up, R.drawable.corner
    };
}