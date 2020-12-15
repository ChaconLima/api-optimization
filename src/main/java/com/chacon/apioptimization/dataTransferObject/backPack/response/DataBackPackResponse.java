package com.chacon.apioptimization.dataTransferObject.backPack.response;

import java.util.List;

import com.chacon.apioptimization.dataTransferObject.backPack.dataDefault.ObjectDefault;

public class DataBackPackResponse {
    private double objectFuncion;
    private List<ObjectDefault> objects;

    private double capacity;

    public double getObjectFuncion() {
        return objectFuncion;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<ObjectDefault> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectDefault> objects) {
        this.objects = objects;
    }

    public void setObjectFuncion(double objectFuncion) {
        this.objectFuncion = objectFuncion;
    }
}
