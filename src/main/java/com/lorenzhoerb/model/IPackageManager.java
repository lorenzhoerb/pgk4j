package com.lorenzhoerb.model;

import com.lorenzhoerb.exception.DependencyResolutionException;
import com.lorenzhoerb.exception.PackageNotFoundException;

public interface IPackageManager {
    void installPackage(String packageName, String versionNumber) throws PackageNotFoundException;

    void installPackage(String packageName) throws PackageNotFoundException;

    boolean isPackageInstalled(String packageName);

    String getInstalledVersions(String packageName);

    void resolveDependencies() throws DependencyResolutionException;

    void displayInstalledPackages();
}
