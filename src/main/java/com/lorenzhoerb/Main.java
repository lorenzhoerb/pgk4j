package com.lorenzhoerb;

import com.lorenzhoerb.exception.PackageNotFoundException;
import com.lorenzhoerb.model.IPackageManager;
import com.lorenzhoerb.model.IRepository;
import com.lorenzhoerb.model.VersionDetails;
import com.lorenzhoerb.model.impl.SimplePackageManager;
import com.lorenzhoerb.model.impl.SimpleRepository;

public class Main {
    public static void main(String[] args) {
        // Create repository
        IRepository mainRepository = new SimpleRepository();

        var vs = new VersionDetails();
        vs.requires("nuxt");

        // Add packages
        mainRepository.addPackageVersion("nuxt", "1.0", new VersionDetails());
        mainRepository.addPackageVersion("nuxt", "1.1", new VersionDetails());

        mainRepository.addPackageVersion("alit", "1.0", new VersionDetails());
        mainRepository.addPackageVersion("pse", "1.0", new VersionDetails());

        // Create Package Manager
        IPackageManager packageManager = new SimplePackageManager(mainRepository);

        // Install packages
        try {
            packageManager.installPackage("nuxt");
            packageManager.installPackage("alit", "1.0");
        } catch (PackageNotFoundException e) {
            throw new RuntimeException(e);
        }

        // List packages
        packageManager.displayInstalledPackages();
    }
}