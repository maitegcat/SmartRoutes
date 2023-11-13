package urjc.com.wayfindingapp.Model;


import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import urjc.com.wayfindingapp.Model.I18n.TypeEndowmentMultiString;

public class TypeEndowment implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("typeEndowmentMultiString")
    @Expose
    private List<TypeEndowmentMultiString> typeEndowmentMultiString = null;
    @SerializedName("allFloors")
    @Expose
    private Boolean allFloors;
    @SerializedName("withName")
    @Expose
    private Boolean withName;

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

    public List<TypeEndowmentMultiString> getTypeEndowmentMultiString() {
        return typeEndowmentMultiString;
    }

    public void setTypeEndowmentMultiString(List<TypeEndowmentMultiString> typeEndowmentMultiString) {
        this.typeEndowmentMultiString = typeEndowmentMultiString;
    }

    public Boolean getAllFloors() {
        return allFloors;
    }

    public void setAllFloors(Boolean allFloors) {
        this.allFloors = allFloors;
    }

    public Boolean getWithName() {
        return withName;
    }

    public void setWithName(Boolean withName) {
        this.withName = withName;
    }

}
