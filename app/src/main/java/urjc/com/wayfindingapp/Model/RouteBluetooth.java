package urjc.com.wayfindingapp.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import urjc.com.wayfindingapp.Model.I18n.RouteBluetoothMultiString;

public class RouteBluetooth implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("routeBluetoothMultiString")
    @Expose
    private List<RouteBluetoothMultiString> routeBluetoothMultiString = null;
    @SerializedName("wayPoint")
    @Expose
    private List<WayPoint> wayPoint = null;
    @SerializedName("floor")
    @Expose
    private Floor floor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDef() {
        return nameDef;
    }

    public void setNameDef(String nameDef) {
        this.nameDef = nameDef;
    }

    public List<RouteBluetoothMultiString> getRouteBluetoothMultiString() {
        return routeBluetoothMultiString;
    }

    public void setRouteBluetoothMultiString(List<RouteBluetoothMultiString> routeBluetoothMultiString) {
        this.routeBluetoothMultiString = routeBluetoothMultiString;
    }

    public List<WayPoint> getWayPoint() {
        return wayPoint;
    }

    public void setWayPoint(List<WayPoint> wayPoint) {
        this.wayPoint = wayPoint;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

}
