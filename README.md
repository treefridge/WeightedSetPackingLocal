# WeightedSetPackingLocal

Local Search: Attempt to replace a small subset of the solution by some collection of greater total weight that does not intersect the remainder of the solution. For such a search to be polynomially bounded, it has to be restricted in some way, such as that either the number of sets added or the number of sets removed should be constant.

Right now, the number of subsets to be replaced is defined by a constant representing the maximum number of them.

## Remaining:
- Optimize for bottlenecks if runtime is too lengthy (limit number of combinations tried, etc)
- Implement AnyImp 
- Add measurement code to compare the algorithms 
- IDEA: have multiple different starting solutions for basic Local search
- IDEA: have multiple different benchmark datasets 

