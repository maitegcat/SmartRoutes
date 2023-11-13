package urjc.com.wayfindingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TypeLugar implements Serializable {

    @SerializedName("idtypelugar")
    @Expose
    private Long idtypelugar;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Long getIdtypelugar() {
        return idtypelugar;
    }

    public void setIdtypelugar(Long idtypelugar) {
        this.idtypelugar = idtypelugar;
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
}
