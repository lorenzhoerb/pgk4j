package com.lorenzhoerb.model;


import com.lorenzhoerb.exception.PackageNotFoundException;

import java.util.List;

public interface IRepository {

    void addPackageVersion(String packageName, String versionNumber, VersionDetails versionDetails);

    Package getPackage(String packageName);

    Version getPackageVersion(String packageName, String versionNumber) throws PackageNotFoundException;

    List<Package> getAllPackages();

    List<Version> getAllVersions(String packageName);

    Version getLatestVersion(String packageName) throws PackageNotFoundException;

    boolean hasPackageVersion(String packageName, String versionNumber);
}
