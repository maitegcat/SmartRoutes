package urjc.com.wayfindingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lugar implements Serializable {

    @SerializedName("idlugar")
    @Expose
    private Long idlugar;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("coord_x")
    @Expose
    private Float coord_x;

    @SerializedName("coord_y")
    @Expose
    private Float coord_y;

    @SerializedName("imagen")
    @Expose
    private String imagen;

    @SerializedName("idlugarc")
    @Expose
    private Long idlugarc;

    @SerializedName("idtypelugar")
    @Expose
    private Long idtypelugar;


    public Lugar(String dato, String dato1) {

    }

    public Long getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(Long idlugar) {
        this.idlugar = idlugar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getIdlugarc() {
        return idlugarc;
    }

    public void setIdlugarc(Long idlugarc) {
        this.idlugarc = idlugarc;
    }

    public Long getIdtypelugar() {
        return idtypelugar;
    }

    public void setIdtypelugar(Long idtypelugar) {
        this.idtypelugar = idtypelugar;
    }
}
