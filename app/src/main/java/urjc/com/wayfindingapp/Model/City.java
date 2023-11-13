package urjc.com.wayfindingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import urjc.com.wayfindingapp.Model.I18n.CityMultiString;

public class City implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("cityMultiString")
    @Expose
    private List<CityMultiString> cityMultiString = null;
    @SerializedName("state")
    @Expose
    private State state;

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

    public List<CityMultiString> getCityMultiString() {
        return cityMultiString;
    }

    public void setCityMultiString(List<CityMultiString> cityMultiString) {
        this.cityMultiString = cityMultiString;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
