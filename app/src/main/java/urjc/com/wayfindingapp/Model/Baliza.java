package urjc.com.wayfindingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Baliza implements Serializable {

    @SerializedName("idbaliza")
    @Expose
    private Long idbaliza;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("codletra")
    @Expose
    private String codletra;

    @SerializedName("coord_x")
    @Expose
    private Float coord_x;

    @SerializedName("coord_y")
    @Expose
    private Float coord_y;

    @SerializedName("idlugar")
    @Expose
    private Long idlugar;

    public Long getIdbaliza() {
        return idbaliza;
    }

    public void setIdbaliza(Long idbaliza) {
        this.idbaliza = idbaliza;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodLetra() {
        return codletra;
    }

    public void setCodLetra(String codletra) {
        this.codletra = codletra;
    }

    public Float getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(Float coord_x) {
        this.coord_x = coord_x;
    }

    public Float getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(Float coord_y) {
        this.coord_y = coord_y;
    }

    public Long getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(Long idlugar) {
        this.idlugar = idlugar;
    }
}
