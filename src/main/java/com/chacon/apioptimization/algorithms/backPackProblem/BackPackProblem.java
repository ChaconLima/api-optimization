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

        List<ObjectDefault>objects = new ArrayList<ObjectDefault>();
        List<ObjectDefault>answers = new ArrayList<ObjectDefault>();

        objects= dataBackPackRequest.getObjects();

        Random generator = new Random();

        Boolean insert = true;
        double capacity = 0;
        while( insert == true){

            int index = generator.nextInt(objects.size());
            Boolean accept=true;

            for(int i=0; i<answers.size();i++){

                if(objects.get(index) == answers.get(i)){
                    accept=false;
                }
            }
            if( accept==true){

                capacity+=objects.get(index).getWeight();
                answers.add(objects.get(index));
                
                if(capacity>dataBackPackRequest.getMaximumCapacity()){

                    capacity-=objects.get(index).getWeight();
                    answers.remove(objects.get(index));
                    insert=false;
                }
            }
        }
        dataBackPackResponse.setObjects(answers);
        dataBackPackResponse = this.calculateFuncionObjetive(dataBackPackResponse);
        dataBackPackResponse.setCapacity(this.calculateTotalWeight(dataBackPackResponse));

        return dataBackPackResponse;
    }
    //===================================
    //Swap 1 opt
    //===================================
    private DataBackPackResponse swapOpt(DataBackPackResponse dataBackPackResponse,
                                         DataBackPackRequest dataBackPackRequest){
        
        DataBackPackResponse dataResponse = new DataBackPackResponse();
        List<ObjectDefault>objectsRequest = new ArrayList<ObjectDefault>();
        List<ObjectDefault>objectsRespose = new ArrayList<ObjectDefault>();

        objectsRequest=dataBackPackRequest.getObjects();
        objectsRespose=dataBackPackResponse.getObjects();

        Random generator = new Random();
        int output_index=0, input_index=0;

        output_index=generator.nextInt(objectsRespose.size());
        
        Boolean insert = true; 
        while( insert == true)
        {
            input_index=generator.nextInt(objectsRequest.size());
            insert=false;
            for(int i=0; i<objectsRespose.size();i++){
                if( objectsRespose.get(i) == objectsRequest.get(input_index)){
                    insert = true;
                }
            }
        }

        objectsRespose.remove(output_index);
        objectsRespose.add(objectsRequest.get(input_index));

        dataResponse.setObjects(objectsRespose);

        if(this.calculateTotalWeight(dataResponse)>dataBackPackRequest.getMaximumCapacity()){
            dataResponse.setObjectFuncion(0);
        }
        else{
            dataResponse = this.calculateFuncionObjetive(dataResponse);
        }
        dataResponse.setCapacity(this.calculateTotalWeight(dataResponse));

        return dataResponse;
    }
    //==================================
    //Simulated Annealing
    //==================================
    private DataBackPackResponse simulateAnnealing(DataBackPackRequest dataBackPackRequest){

        DataBackPackResponse dataBackPakResponse = new DataBackPackResponse();
        DataBackPackResponse dataBackPakResponse_current = new DataBackPackResponse();
        DataBackPackResponse dataBackPakResponse_line = new DataBackPackResponse();

        dataBackPakResponse_current = this.greedyHeuristic(dataBackPackRequest);
        dataBackPakResponse = dataBackPakResponse_current;

        int IterT=0, SAmax=100;
        double T = 100, delta=0, accept = 0.85, e=2.718281828459045235360287, alfha = 0.5;
        while(T>0)
        {
            while(IterT<SAmax)
            {
                IterT=IterT+1;
                dataBackPakResponse_line=this.swapOpt(dataBackPakResponse_current, dataBackPackRequest);
                delta = dataBackPakResponse_line.getObjectFuncion() - dataBackPakResponse_current.getObjectFuncion();
               
                if(delta > 0){
                    dataBackPakResponse_current = dataBackPakResponse_line;
                    if(dataBackPakResponse.getObjectFuncion()<dataBackPakResponse_current.getObjectFuncion()){
                        dataBackPakResponse = dataBackPakResponse_current;
                    }
                }
                else{
                    if(accept<Math.pow(e,(delta/T))){
                        dataBackPakResponse_current = dataBackPakResponse_line;
                    }
                }
            }
            T = (1 - alfha)*T;
            IterT = 0;
        }
        return dataBackPakResponse;
    }

    //===================================
    //main
    //===================================
    public DataBackPackResponse getOptimizationBackPack(DataBackPackRequest dataBackPackRequest){
        return this.simulateAnnealing(dataBackPackRequest);
    }
}
