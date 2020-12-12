package com.chacon.apioptimization.dataTransferObject.backPack.dataDefault;

public class ObjectDefault {
    private String name;
    private double weight;
    private double benefit;

    public String getName() {
        return name;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }
}
