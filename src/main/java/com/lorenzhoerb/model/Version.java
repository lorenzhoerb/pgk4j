package com.lorenzhoerb.model;

public class Version {
    private final String versionNumber;
    private VersionDetails versionDetails;

    public Version(String versionNumber, VersionDetails versionDetails) {
        this.versionNumber = versionNumber;
        this.versionDetails = versionDetails;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public VersionDetails getVersionDetails() {
        return versionDetails;
    }

    @Override
    public String toString() {
        return versionNumber;
    }
}
