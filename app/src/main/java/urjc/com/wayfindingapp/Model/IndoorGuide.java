package urjc.com.wayfindingapp.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import urjc.com.wayfindingapp.Model.I18n.IndoorRouteMultiString;

public class IndoorGuide implements Serializable {

    @SerializedName("idIndoor")
    @Expose
    private Integer idIndoor;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("indoorRouteMultiString")
    @Expose
    private List<IndoorRouteMultiString> indoorRouteMultiString = null;
    @SerializedName("routeBluetooth")
    @Expose
    private List<RouteBluetooth> routeBluetooth = null;

    public Integer getIdIndoor() {
        return idIndoor;
    }

    public void setIdIndoor(Integer idIndoor) {
        this.idIndoor = idIndoor;
    }

    public String getNameDef() {
        return nameDef;
    }

    public void setNameDef(String nameDef) {
        this.nameDef = nameDef;
    }

    public List<IndoorRouteMultiString> getIndoorRouteMultiString() {
        return indoorRouteMultiString;
    }

    public void setIndoorRouteMultiString(List<IndoorRouteMultiString> indoorRouteMultiString) {
        this.indoorRouteMultiString = indoorRouteMultiString;
    }

    public List<RouteBluetooth> getRouteBluetooth() {
        return routeBluetooth;
    }

    public void setRouteBluetooth(List<RouteBluetooth> routeBluetooth) {
        this.routeBluetooth = routeBluetooth;
    }

}
