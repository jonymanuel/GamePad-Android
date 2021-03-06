package com.gamepad.lib.update;

import java.io.Serializable;

public class AvailableGame implements Serializable
{
    private String name;
    private String versionName;
    private int version;
    private String downloadUrl;

    public AvailableGame(String name, String versionName, int version, String downloadUrl)
    {
        this.name = name;
        this.version = version;
        this.versionName = versionName;
        this.downloadUrl = downloadUrl;
    }

    public String getName()
    {
        return name;
    }

    public String getVersionName()
    {
        return versionName;
    }

    public int getVersion()
    {
        return version;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
