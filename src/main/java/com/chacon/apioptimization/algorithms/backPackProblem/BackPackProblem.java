package com.chacon.apioptimization.algorithms.backPackProblem;

public class BackPackProblem {
    
    public String getHeuristic(){
        String a = this.solution()+" developing backpack problem ";
        return a;
    }
    private long solution(){
        return (20*100);
    }
}
