package com.chacon.apioptimization.dataTransferObject.backPack.request;


import java.util.List;

import com.chacon.apioptimization.dataTransferObject.backPack.dataDefault.ObjectDefault;

public class DataBackPackRequest {
    private int maximumCapacity;
    private List<ObjectDefault> objects;

    public List<ObjectDefault> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectDefault> objects) {
        this.objects = objects;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }
}