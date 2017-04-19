package com.ridelr.site.web.domain.map;

import java.util.ArrayList;

/**
 * Created by rfk on 08.01.2016.
 */
public class DeviceMapInfo {
    private ArrayList<DeviceInfo> devices;
    private String updateToken;
    private String userId;

    public ArrayList<DeviceInfo> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<DeviceInfo> devices) {
        this.devices = devices;
    }

    public String getUpdateToken() {
        return updateToken;
    }

    public void setUpdateToken(String updateToken) {
        this.updateToken = updateToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
