package urjc.com.wayfindingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import urjc.com.wayfindingapp.Model.I18n.CountryMultiString;

public class Country implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("countryMultiString")
    @Expose
    private List<CountryMultiString> countryMultiString = null;

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

    public List<CountryMultiString> getCountryMultiString() {
        return countryMultiString;
    }

    public void setCountryMultiString(List<CountryMultiString> countryMultiString) {
        this.countryMultiString = countryMultiString;
    }

}
