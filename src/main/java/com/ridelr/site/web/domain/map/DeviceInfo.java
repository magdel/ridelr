package com.ridelr.site.web.domain.map;

public class DeviceInfo {
    private String id;
    private String name;
    private Double lat;
    private Double lon;
    private int alt;
    private short crs;
    private float spd;
    private long dttm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public short getCrs() {
        return crs;
    }

    public void setCrs(short crs) {
        this.crs = crs;
    }

    public float getSpd() {
        return spd;
    }

    public void setSpd(float spd) {
        this.spd = spd;
    }

    public long getDttm() {
        return dttm;
    }

    public void setDttm(long dttm) {
        this.dttm = dttm;
    }
}
