package urjc.com.wayfindingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Way implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("destination")
    @Expose
    private Endowment destination;
    @SerializedName("bt1")
    @Expose
    private BalizaBT bt1;
    @SerializedName("bt2")
    @Expose
    private BalizaBT bt2;
    @SerializedName("rssi1")
    @Expose
    private Integer rssi1;
    @SerializedName("rssi2")
    @Expose
    private Integer rssi2;
    @SerializedName("peso")
    @Expose
    private Integer peso;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("puerta")
    @Expose
    private String puerta;
    @SerializedName("giro")
    @Expose
    private String giro;
    @SerializedName("orient")
    @Expose
    private String orient;
    @SerializedName("walk")
    @Expose
    private String walk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endowment getDestination() {
        return destination;
    }

    public void setDestination(Endowment destination) {
        this.destination = destination;
    }

    public BalizaBT getBt1() {
        return bt1;
    }

    public void setBt1(BalizaBT bt1) {
        this.bt1 = bt1;
    }

    public BalizaBT getBt2() {
        return bt2;
    }

    public void setBt2(BalizaBT bt2) {
        this.bt2 = bt2;
    }

    public Integer getRssi1() {
        return rssi1;
    }

    public void setRssi1(Integer rssi1) {
        this.rssi1 = rssi1;
    }

    public Integer getRssi2() {
        return rssi2;
    }

    public void setRssi2(Integer rssi2) {
        this.rssi2 = rssi2;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

}
