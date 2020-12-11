package com.chacon.apioptimization.controllers;

import com.chacon.apioptimization.algorithms.backPackProblem.BackPackProblem;
import com.chacon.apioptimization.algorithms.travelingCashmere.TravelingCashmere;
import com.chacon.apioptimization.dataTransferObject.BackPackDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemsController {
    
    BackPackProblem backPackProblem = new BackPackProblem();

    @PostMapping(value = "back-pack")
    public String getSolutionBackPack(@RequestBody BackPackDTO backPackDTO){
        return this.backPackProblem.getHeuristic(backPackDTO);
    }

    TravelingCashmere travelingCashmere = new TravelingCashmere();

    @GetMapping(value = "traveling-cashmere")
    public String getSolutionTravelingCashmere(){
        return this.travelingCashmere.getHeuristic();
    }
}
