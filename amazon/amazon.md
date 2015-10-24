## Amazon SDE Interview preparation



# Amazon OA2:

1. Find Path in 2D matrix

> Maze Problem:
>
>Input: two dimension integer array. 0-walls, 1-open areas, 9-targets(may be more than one). Initial point is (0,0) ( further problem: any points within the given array).
Output: ture if 9 can be found through a valid path. Otherwise return false.

Example: 

1  | 1 | 0 | 0  | 1 | 0 
---  | --- | --- | ---  | --- | --- 
0  | 1 | 1 | 0  | 1 | 0 
0  | 1 | 0 | 1  | 1 | 0 
0  | 1 | 0 | 9  | 1 | 1 
9  | 0 | 0 | 1  | 0 | 0 
0  | 1 | 1 | 1  | 0 | 0 

Return: true.

1  | 1 | 0 | 0  | 1 | 0 
---  | --- | --- | ---  | --- | --- 
0  | 1 | 1 | 0  | 1 | 0 
0  | 1 | 0 | 0  | 0 | 0 
0  | 1 | 0 | 9  | 1 | 1 
9  | 0 | 0 | 1  | 0 | 0 
0  | 1 | 1 | 1  | 0 | 0 

Return: false.

> DFS-Solution: Time complexity: O(N^2)
> Run a DFS starting from point (0,0). At each point, we can go either down or right by one step. Create a visited array can reduce the tim complexity, which guarantees that each point is visited only once. Extra pace: O(n^2) or change the point value to be 0 if allowed. DFS is probably better than BFS since if the array is huge then DFS may be better choice to store intermediate results. 