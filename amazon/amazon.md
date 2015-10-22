## Amazon SDE Interview preparation



# Amazon OA2:

1. Find Path in 2D matrix

> Maze Problem:
>
>Input: two dimension integer array. 0-wall, 1-walk through, 9-targets(may be more than one). Initial point is (0,0).
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

