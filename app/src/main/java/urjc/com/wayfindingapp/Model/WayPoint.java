package urjc.com.wayfindingapp.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WayPoint  implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("origin")
    @Expose
    private Integer origin;
    @SerializedName("name")
    @Expose
    private String name;
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
    @SerializedName("way")
    @Expose
    private List<Way> way = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Way> getWay() {
        return way;
    }

    public void setWay(List<Way> way) {
        this.way = way;
    }

}
