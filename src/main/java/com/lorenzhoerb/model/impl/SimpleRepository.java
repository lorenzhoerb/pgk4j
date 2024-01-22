package com.lorenzhoerb.model.impl;

import com.lorenzhoerb.exception.PackageNotFoundException;
import com.lorenzhoerb.model.IRepository;
import com.lorenzhoerb.model.Package;
import com.lorenzhoerb.model.Version;
import com.lorenzhoerb.model.VersionDetails;

import java.util.*;

public class SimpleRepository implements IRepository {

    private final Map<String, Package> packages = new HashMap<>();

    @Override
    public void addPackageVersion(String packageName, String versionNumber, VersionDetails versionDetails) {
        packages.computeIfAbsent(packageName, k -> new Package(packageName));
        Package packageObj = packages.get(packageName);
        packageObj.addVersion(versionNumber, versionDetails);
    }

    @Override
    public Package getPackage(String packageName) {
        return packages.getOrDefault(packageName, null);
    }

    @Override
    public Version getPackageVersion(String packageName, String versionNumber) {
        if (!packages.containsKey(packageName))
            return null;
        Package pkg = packages.get(packageName);
        return pkg.getVersion(versionNumber);
    }

    @Override
    public List<Package> getAllPackages() {
        return List.copyOf(packages.values());
    }

    @Override
    public List<Version> getAllVersions(String packageName) {
        if (packages.containsKey(packageName))
            return packages.get(packageName).getAllVersions();
        return Collections.emptyList();
    }

    @Override
    public Version getLatestVersion(String packageName) throws PackageNotFoundException {
        if (!packages.containsKey(packageName))
            throw new PackageNotFoundException(packageName);
        return packages.get(packageName).getLatestVersion();
    }

    @Override
    public boolean hasPackageVersion(String packageName, String versionNumber) {
        if (!packages.containsKey(packageName))
            return false;
        Package pkg = packages.get(packageName);
        return pkg.hasVersion(versionNumber);
    }
}
