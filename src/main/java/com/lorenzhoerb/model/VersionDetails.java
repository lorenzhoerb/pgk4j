package com.lorenzhoerb.model;

import java.util.HashMap;
import java.util.Map;

public class VersionDetails {
    private final Map<String, String> dependencies = new HashMap<>();


    public void requires(String packageName) {
        dependencies.put(packageName, null);
    }

    public void requires(String packageName, String versionNumber) {
        dependencies.put(packageName, versionNumber);
    }

    public Map<String, String> getDependencies() {
        return dependencies;
    }
}
