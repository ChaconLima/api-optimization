package com.chacon.apioptimization.controllers;

import com.chacon.apioptimization.algorithms.backPackProblem.BackPackProblem;
import com.chacon.apioptimization.algorithms.travelingCashmere.TravelingCashmere;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemsController {
    
    BackPackProblem backPackProblem = new BackPackProblem();

    @GetMapping(value = "back-pack")
    public String getSolutionBackPack(){
        return this.backPackProblem.getHeuristic();
    }

    TravelingCashmere travelingCashmere = new TravelingCashmere();

    @GetMapping(value = "traveling-cashmere")
    public String getSolutionTravelingCashmere(){
        return this.travelingCashmere.getHeuristic();
    }
}
