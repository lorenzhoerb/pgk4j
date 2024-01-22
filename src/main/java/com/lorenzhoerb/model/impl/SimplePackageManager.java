package com.lorenzhoerb.model.impl;

import com.lorenzhoerb.exception.DependencyResolutionException;
import com.lorenzhoerb.exception.PackageNotFoundException;
import com.lorenzhoerb.model.IPackageManager;
import com.lorenzhoerb.model.IRepository;
import com.lorenzhoerb.model.Version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePackageManager implements IPackageManager {
    private final Map<String, Version> installedPackages = new HashMap<>();
    private final List<IRepository> repositories = new ArrayList<>();

    public SimplePackageManager(IRepository repository) {
        repositories.add(repository);
    }

    public SimplePackageManager(List<IRepository> repositories) {
        this.repositories.addAll(repositories);
    }

    @Override
    public void installPackage(String packageName, String versionNumber) throws PackageNotFoundException {
        for (IRepository repository : repositories) {
            boolean success = tryInstallPackage(repository, packageName, versionNumber);
            if (success) return;
        }
        throw new PackageNotFoundException(packageName, versionNumber);
    }

    @Override
    public void installPackage(String packageName) throws PackageNotFoundException {
        for (IRepository repository : repositories) {
            boolean success = tryInstallPackage(repository, packageName);
            if (success) return;
        }
        throw new PackageNotFoundException(packageName);
    }

    @Override
    public boolean isPackageInstalled(String packageName) {
        return installedPackages.containsKey(packageName);
    }

    @Override
    public String getInstalledVersions(String packageName) {
        if (!installedPackages.containsKey(packageName)) {
            return null;
        }

        return installedPackages.get(packageName).getVersionNumber();
    }

    @Override
    public void resolveDependencies() throws DependencyResolutionException {

    }

    @Override
    public void displayInstalledPackages() {
        System.out.println("Installed Packages:");
        installedPackages.forEach((key, value) -> System.out.printf("\t%s@%s%n", key, value));
    }

    private boolean tryInstallPackage(IRepository repository, String packageName, String versionNumber) {
        if (!repository.hasPackageVersion(packageName, versionNumber)) {
            return false;
        }
        try {
            Version version = repository.getPackageVersion(packageName, versionNumber);
            installedPackages.put(packageName, version);
        } catch (PackageNotFoundException ignored) {
            return false;
        }
        return true;
    }

    private boolean tryInstallPackage(IRepository repository, String packageName) {
        try {
            Version version = repository.getLatestVersion(packageName);
            installedPackages.put(packageName, version);
        } catch (PackageNotFoundException e) {
            return false;
        }
        return true;
    }
}

