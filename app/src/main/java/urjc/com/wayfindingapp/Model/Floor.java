package urjc.com.wayfindingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Floor implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("endowments")
    @Expose
    private List<Object> endowments = null;
    @SerializedName("routeBluetooth")
    @Expose
    private List<Object> routeBluetooth = null;
    @SerializedName("routeQR")
    @Expose
    private List<Object> routeQR = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getEndowments() {
        return endowments;
    }

    public void setEndowments(List<Object> endowments) {
        this.endowments = endowments;
    }

    public List<Object> getRouteBluetooth() {
        return routeBluetooth;
    }

    public void setRouteBluetooth(List<Object> routeBluetooth) {
        this.routeBluetooth = routeBluetooth;
    }

    public List<Object> getRouteQR() {
        return routeQR;
    }

    public void setRouteQR(List<Object> routeQR) {
        this.routeQR = routeQR;
    }

}
