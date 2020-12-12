package com.chacon.apioptimization.algorithms.backPackProblem;

import com.chacon.apioptimization.dataTransferObject.backPack.request.DataBackPackRequest;
import com.chacon.apioptimization.dataTransferObject.backPack.response.DataBackPackResponse;

public class BackPackProblem {
    
    
    public DataBackPackResponse getHeuristic(DataBackPackRequest dataBackPackRequest){

        DataBackPackResponse dataBackPakResponse = new DataBackPackResponse();

        dataBackPakResponse.setObjectFuncion(dataBackPackRequest.getMaximumCapacity());
        dataBackPakResponse.setObjects(dataBackPackRequest.getObjects());

        return dataBackPakResponse;
    }

}
