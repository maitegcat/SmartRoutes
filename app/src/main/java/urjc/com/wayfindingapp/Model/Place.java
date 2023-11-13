package urjc.com.wayfindingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import urjc.com.wayfindingapp.Model.I18n.PlaceMultiString;

public class Place implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("placeMultiString")
    @Expose
    private List<PlaceMultiString> placeMultiString = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latLng")
    @Expose
    private String latLng;
    @SerializedName("floors")
    @Expose
    private List<Floor> floors = null;
    @SerializedName("city")
    @Expose
    private City city;


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

    public List<PlaceMultiString> getPlaceMultiString() {
        return placeMultiString;
    }

    public void setPlaceMultiString(List<PlaceMultiString> placeMultiString) {
        this.placeMultiString = placeMultiString;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

  }
