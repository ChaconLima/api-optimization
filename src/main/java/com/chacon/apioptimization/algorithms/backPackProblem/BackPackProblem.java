package com.chacon.apioptimization.algorithms.backPackProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chacon.apioptimization.dataTransferObject.backPack.dataDefault.ObjectDefault;
import com.chacon.apioptimization.dataTransferObject.backPack.request.DataBackPackRequest;
import com.chacon.apioptimization.dataTransferObject.backPack.response.DataBackPackResponse;

// import sun.tools.tree.DoubleExpression;

public class BackPackProblem {
    //===================================
    //calculation of objective function
    //===================================
    private DataBackPackResponse calculateFuncionObjetive( DataBackPackResponse dataBackPakResponse){
      
        List<ObjectDefault> objects = dataBackPakResponse.getObjects();

        double objectiveFuncion = 0;
        for( ObjectDefault object : objects)
        {
            objectiveFuncion+=object.getBenefit();
        }
        dataBackPakResponse.setObjectFuncion(objectiveFuncion);

        return dataBackPakResponse;
    }
    //==================================
    //calculation of weight restriction
    //==================================
    private double calculateTotalWeight(DataBackPackResponse dataBackPackResponse){

        List<ObjectDefault> objects = dataBackPackResponse.getObjects();

        double totalWeight = 0;
        for( ObjectDefault object : objects)
        {
            totalWeight+=object.getWeight();
        }

        return totalWeight;
    }
    //===================================
    //greedy constructive heurisitic
    //===================================
    private DataBackPackResponse greedyHeuristic(DataBackPackRequest dataBackPackRequest){
        DataBackPackResponse dataBackPackResponse = new DataBackPackResponse();

        List<ObjectDefault>objects =new ArrayList<ObjectDefault>();
        List<ObjectDefault>answers = new ArrayList<ObjectDefault>();

        objects= dataBackPackRequest.getObjects();

        Random generator = new Random();

        Boolean insert = true;
        double capacity = 0;
        while( insert == true){
            int index = generator.nextInt(objects.size());
            capacity+=objects.get(index).getWeight();
            answers.add(objects.get(index));
            if(capacity>dataBackPackRequest.getMaximumCapacity()){
                capacity-=objects.get(index).getWeight();
                answers.remove(objects.get(index));
                insert=false;
            }
        }
        dataBackPackResponse.setObjects(answers);
        return dataBackPackResponse;
    }
    //===================================
    //Swap 1 opt
    //===================================
    private DataBackPackResponse swapOpt(DataBackPackResponse dataBackPackResponse,
                                         DataBackPackRequest dataBackPackRequest){
        
        List<ObjectDefault>objectsRequest = new ArrayList<ObjectDefault>();
        List<ObjectDefault>objectsRespose = new ArrayList<ObjectDefault>();

        objectsRequest=dataBackPackRequest.getObjects();
        objectsRespose=dataBackPackResponse.getObjects();

        Random generator = new Random();
        int output_index=generator.nextInt(objectsRespose.size()), 
            input_index=generator.nextInt(objectsRequest.size());
        
        objectsRespose.remove(output_index);
        objectsRespose.add(objectsRequest.get(input_index));

        dataBackPackResponse.setObjects(objectsRespose);

        if(this.calculateTotalWeight(dataBackPackResponse)>dataBackPackRequest.getMaximumCapacity()){
            dataBackPackResponse.setObjectFuncion(100000);
        }
        else{
            dataBackPackResponse = this.calculateFuncionObjetive(dataBackPackResponse);
        }
        
        return dataBackPackResponse;
    }
    //==================================
    //Simulated Annealing
    //==================================
    private DataBackPackResponse simulateAnnealing(DataBackPackRequest dataBackPackRequest){
        DataBackPackResponse dataBackPakResponse = new DataBackPackResponse();

        return dataBackPakResponse;
    }

    //===================================
    //main
    //===================================
    public DataBackPackResponse getOptimizationBackPack(DataBackPackRequest dataBackPackRequest){

        DataBackPackResponse dataBackPakResponse = new DataBackPackResponse();
        dataBackPakResponse = this.calculateFuncionObjetive( this.greedyHeuristic(dataBackPackRequest));
        dataBackPakResponse.setCapacity(this.calculateTotalWeight(dataBackPakResponse));

        dataBackPakResponse =  this.swapOpt(dataBackPakResponse, dataBackPackRequest);
        dataBackPakResponse.setCapacity(this.calculateTotalWeight(dataBackPakResponse));

        return dataBackPakResponse;
    }

 

}
