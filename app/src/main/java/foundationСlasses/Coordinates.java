package foundationСlasses;

/**
 * Created by Sergei on 9/14/2015.
 */
public class Coordinates {

    double latMin;
    double latMax;
    double lonMin;
    double lonMax;

    public Coordinates(double latMin, double latMax, double lonMin, double lonMax) {
        this.latMin = latMin;
        this.latMax = latMax;
        this.lonMin = lonMin;
        this.lonMax = lonMax;
    }

    public double getLatMin() {
        return latMin;
    }

    public void setLatMin(double latMin) {
        this.latMin = latMin;
    }

    public double getLatMax() {
        return latMax;
    }

    public void setLatMax(double latMax) {
        this.latMax = latMax;
    }

    public double getLonMin() {
        return lonMin;
    }

    public void setLonMin(double lonMin) {
        this.lonMin = lonMin;
    }

    public double getLonMax() {
        return lonMax;
    }

    public void setLonMax(double lonMax) {
        this.lonMax = lonMax;
    }
}
