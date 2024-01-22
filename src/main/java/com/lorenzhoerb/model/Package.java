package com.lorenzhoerb.model;

import java.util.HashMap;
import java.util.List;

public class Package {

    private final String name;
    private final HashMap<String, Version> versions = new HashMap<>();

    public Package(String name) {
        this.name = name;
    }

    public void addVersion(String versionNumber, VersionDetails versionDetails) {
        Version version = new Version(versionNumber, versionDetails);
        versions.put(versionNumber, version);
    }

    public Version getLatestVersion() {
        if (versions.isEmpty()) return null;
        return versions.get(versions.keySet().stream().max(String::compareTo).get());
    }

    public Version getVersion(String versionNumber) {
        return versions.getOrDefault(versionNumber, null);
    }

    public List<Version> getAllVersions() {
        return List.copyOf(versions.values());
    }

    public boolean hasVersion(String version) {
        return versions.containsKey(version);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
