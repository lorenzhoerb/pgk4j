package com.lorenzhoerb.exception;

public class PackageNotFoundException extends Exception {
    public PackageNotFoundException(String packageName) {
        super((String.format("Package not found: %s@*", packageName)));

    }

    public PackageNotFoundException(String packageName, String versionNumber) {
        super(String.format("Package not found: %s@%s", packageName, versionNumber));
    }
}
