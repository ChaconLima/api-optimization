# Api Optimization
The purpose of this API is to develop a set of optimization algorithms for different problems. Using classic algorithms, such as:
SimulateAnneling, Genetic Algorithms, Ant colonies and others

## Endpoint - Knapsack problem

The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items. The problem often arises in resource allocation where the decision makers have to choose from a set of non-divisible projects or tasks under a fixed budget or time constraint, respectively.

In this problem, the SimulateAnneling algorithm was used in conjunction with the concept of 1Opt exchanges.

endpoint used receives an application in json with the data from the knapsack problem, and returns a document in json, with optimized result of the problem.

- Usage Endpoint:

    ```
        http://localhost/knapsack
    ```

    Example of json that should be consumed by api:

        {
            "maximumCapacity":23,
            "objects":[
                {
                    "name":"1",
                    "weight":4,
                    "benefit":2
                },
                {
                    "name":"2",
                    "weight":5,
                    "benefit":2
                },
                {
                    "name":"3",
                    "weight":7,
                    "benefit":3
                },
                {
                    "name":"4",
                    "weight":9,
                    "benefit":4
                },
                {
                    "name":"5",
                    "weight":6,
                    "benefit":4
                }
            ]
        }

    Example of return in json, created by api

        {
            "objectFuncion": x,
            "objects": [
               
            "capacity": x
        }

## Next Endpoits

- [ ] Knapsack Problem
- [ ] Traveling Cashew
- [ ] Cutting Stock Problem
- [ ] Job Shop Problem
- [ ] Bin Packing Problem
