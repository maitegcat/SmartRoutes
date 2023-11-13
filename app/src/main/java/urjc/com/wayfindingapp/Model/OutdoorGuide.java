package urjc.com.wayfindingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import urjc.com.wayfindingapp.Model.I18n.OutdoorRouteMultiString;

public class OutdoorGuide implements Serializable {

    @SerializedName("idOutdoor")
    @Expose
    private Integer idOutdoor;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("outdoorRouteMultiString")
    @Expose
    private List<OutdoorRouteMultiString> outdoorRouteMultiString = null;
    @SerializedName("placeOrigin")
    @Expose
    private Place placeOrigin;
    @SerializedName("placeDestination")
    @Expose
    private Place placeDestination;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("pathInd")
    @Expose
    private String pathInd;
    @SerializedName("accesible")
    @Expose
    private Boolean accesible;

    public Integer getIdOutdoor() {
        return idOutdoor;
    }

    public void setIdOutdoor(Integer idOutdoor) {
        this.idOutdoor = idOutdoor;
    }

    public String getNameDef() {
        return nameDef;
    }

    public void setNameDef(String nameDef) {
        this.nameDef = nameDef;
    }

    public List<OutdoorRouteMultiString> getOutdoorRouteMultiString() {
        return outdoorRouteMultiString;
    }

    public void setOutdoorRouteMultiString(List<OutdoorRouteMultiString> outdoorRouteMultiString) {
        this.outdoorRouteMultiString = outdoorRouteMultiString;
    }

    public Place getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(Place placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public Place getPlaceDestination() {
        return placeDestination;
    }

    public void setPlaceDestination(Place placeDestination) {
        this.placeDestination = placeDestination;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathInd() {
        return pathInd;
    }

    public void setPathInd(String pathInd) {
        this.pathInd = pathInd;
    }

    public Boolean getAccesible() {
        return accesible;
    }

    public void setAccesible(Boolean accesible) {
        this.accesible = accesible;
    }

    @Override
    public String toString() {
        return "Outdoor: " + this.nameDef;
    }

}
