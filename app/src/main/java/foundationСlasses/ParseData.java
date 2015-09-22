package foundationСlasses;

import java.util.ArrayList;

/**
 * Created by Sergei on 9/15/2015.
 */
public class ParseData {

    ArrayList<City> data;

    public ParseData() {
        data = new ArrayList<City>();
    }

    public ArrayList<City> getData() {
        return data;
    }

    public void setData(ArrayList<City> data) {
        this.data = data;
    }


    public void addCity(City city) {

    data.add(city);


    }
}
