package urjc.com.wayfindingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import urjc.com.wayfindingapp.Model.I18n.GuideMultiString;

public class Guide implements Serializable {

    @SerializedName("idGuide")
    @Expose
    private Integer idGuide;
    @SerializedName("nameDef")
    @Expose
    private String nameDef;
    @SerializedName("guideMultiString")
    @Expose
    private List<GuideMultiString> guideMultiString = null;
    @SerializedName("indoorGuide")
    @Expose
    private List<IndoorGuide> indoorGuide = null;
    @SerializedName("outdoorGuide")
    @Expose
    private List<OutdoorGuide> outdoorGuide = null;

    public Integer getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(Integer idGuide) {
        this.idGuide = idGuide;
    }

    public String getNameDef() {
        return nameDef;
    }

    public void setNameDef(String nameDef) {
        this.nameDef = nameDef;
    }

    public List<GuideMultiString> getGuideMultiString() {
        return guideMultiString;
    }

    public void setGuideMultiString(List<GuideMultiString> guideMultiString) {
        this.guideMultiString = guideMultiString;
    }

    public List<IndoorGuide> getIndoorGuide() {
        return indoorGuide;
    }

    public void setIndoorGuide(List<IndoorGuide> indoorGuide) {
        this.indoorGuide = indoorGuide;
    }

    public List<OutdoorGuide> getOutdoorGuide() {
        return outdoorGuide;
    }

    public void setOuterGuide(List<OutdoorGuide> outdoorGuide) {
        this.outdoorGuide = outdoorGuide;
    }

    @Override
    public String toString() {
        return " " + this.nameDef;
    }


}
