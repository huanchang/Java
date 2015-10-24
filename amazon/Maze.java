public class Maze{

    
    public static void main(String[] args) {
        // test main function
        int[][] maze1 = {{1,0,0,0,0},{1,1,1,1,1},{1,0,0,0,0},{0,0,9,0,0}};  
        int[][] maze2 = {{1,0,0,0,0},{1,1,1,1,1},{1,0,0,0,1},{0,0,9,1,1}};         
        int[][] maze3 = {{1,1,1,1},{1,0,0,0},{1,9,0,0}};  
        int[][] maze4 = {{9}};  
        int[][] maze5 ={{1,1,1,1,1,1},{1,1,1,1,0,0},{0,0,1,0,0,0},{1,1,1,1,1,1},{1,0,0,0,1,0},{1,1,1,0,9,0}}; 
        
        // Edge tests
        int[][] maze6 ={{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}}; 
        int[][] maze7 ={{0,0,0},{0,0,0},{0,0,0}}; 
        int[][] maze8 ={{9,9,9},{9,9,9},{9,9,9}}; 
        
        testMaze(maze1);
        testMaze(maze2);
        testMaze(maze3);
        testMaze(maze4);
        testMaze(maze5);
        testMaze(maze6);
        testMaze(maze7);
        testMaze(maze8);
        
        
    }
    
    
    public static void testMaze(int[][] maze) {
        
        RecursiveDFS dfsSolution = new RecursiveDFS(1,maze);
        
        dfsSolution.start(); 
        
        RecursiveDFSWithVisited dfsVisitedSolution = new RecursiveDFSWithVisited(2,maze);
        
        dfsVisitedSolution.start(); 
        
    }
    
    
    
    
}



abstract class MazeSolution extends Thread{
    protected final int threadID;
    protected final String description;
    
    protected int[][] maze;
    
    protected int timeCounter;
    
    public MazeSolution(final int threadID, final String s, int[][] maze) {
        this.threadID = threadID;
        this.description = s;
        this.maze = maze;
        timeCounter = 0;
    }
    
    @Override
    public void run() {
        resetTimeCounter();
        
        if (findTarget(maze)) {
            System.out.println(this + "Found.");
        } else {
            System.out.println(this + "Not found.");
        }
        
        displayTimeCounter();
        displayMaze();
    }
    
    public void resetTimeCounter() {
        timeCounter = 0;
    }
    
    public void addTimeCounter() {
        timeCounter++;
    }
    
    public void displayTimeCounter() {
        System.out.printf("\t Time Complexity: %d\n",timeCounter);
    }
    
    public String toString() {
        return "Thread-"+threadID+description;
    }
    
    public void displayMaze() {
        if (maze==null||maze.length==0||maze[0].length==0) {  
            return;
        }
        
        for (int i = 0; i<maze.length; ++i) {
            for(int j = 0; j <maze[0].length; ++j) {
                System.out.printf("%d  ",maze[i][j]);
            }
            System.out.printf("\n");
        }
    }
    
    
    abstract public boolean findTarget(int[][] maze);
}



class RecursiveDFS extends MazeSolution{
    
    public RecursiveDFS(final int threadID, int[][] maze) {
        super(threadID, "(Recursive DFS solution)", maze);
    }
    
    @Override
    public boolean findTarget(int[][] maze) {
        // Solution uses recursively depth-first search
        // This solution can be convert to an iterative solution by using a stack
        
        // Empty maze array or NULL pointer
        if (maze==null||maze.length==0||maze[0].length==0) {  
            return false;
        }
        
        // Start from the left-top point
        return dfsRecursiveMaze(maze, 0,0);
        
    }
    
    public boolean dfsRecursiveMaze(int[][] maze, int col, int row) {
        // Check the value at current point
        // Worst case: Time complexity is O(2^2n)
        // since there are two choices at each step and totally at most 2n steps to the right-bottom
        // Space complexity is O(1)
        
        // Analysis: paths may share parts, which can be reduced by using an visited array 
        boolean foundTarget = false;    // default as false
        addTimeCounter();
        
        switch(maze[col][row]) {
            case 0:
                // Wall, cannot pass
                break;
            case 1:
                // Hallway can walk through
                // Go along two directions: right and down
                
                // Go right first
                if (col+1<maze.length) {
                    foundTarget = dfsRecursiveMaze(maze, col+1, row);
                }
                
                // If not found a target along the right path, then go down
                if ( row + 1<maze.length && foundTarget == false) {
                    foundTarget = dfsRecursiveMaze(maze, col, row+1);
                }
                
                break;
            case 9:
                //Found a target
                foundTarget = true;
                break;
            default:
                System.out.println("Invalid maze value. Should be 0, 1 or 9.");
        }
        
        return foundTarget;
    }   
    
}


class RecursiveDFSWithVisited extends MazeSolution{
        
    public RecursiveDFSWithVisited(final int threadID, int[][] maze) {
        super(threadID, "(Recursive DFS solution with visited array)", maze);
    }
    
    @Override
    public boolean findTarget(int[][] maze) {
        // Solution uses recursively depth-first search
        // This solution can be convert to an iterative solution by using a stack
        
        // Empty maze array or NULL pointer
        if (maze==null||maze.length==0||maze[0].length==0) {  
            return false;
        }
        
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        
        // Start from the left-top point
        return dfsWithVisitedRecursion(maze, visited, 0,0);
        
    }
    
    public boolean dfsWithVisitedRecursion(int[][] maze, boolean[][] visited, int col, int row) {
        // Check the value at current point
        // Worst case: Time complexity is O(n^2)
        // By using visited array, it is guaranteed that each point is visited once
        // Space complexity is O(n^2)
        
        boolean foundTarget = false;    // default as false
        addTimeCounter();
        
        switch(maze[col][row]) {
            case 0:
                // Wall, cannot pass
                break;
            case 1:
                // Hallway can walk through
                // Go along two directions: right and down
                
                // Go right first
                if (col+1<maze.length&&visited[col+1][row]==false) {
                    foundTarget = dfsWithVisitedRecursion(maze, visited, col+1, row);
                }
                
                // If not found a target along the right path, then go down
                if ( row + 1<maze.length && foundTarget == false&& visited[col][row+1]==false) {
                    foundTarget = dfsWithVisitedRecursion(maze, visited, col, row+1);
                }
                
                break;
            case 9:
                //Found a target
                foundTarget = true;
                break;
            default:
                System.out.println("Invalid maze value. Should be 0, 1 or 9.");
        }
        
        visited[col][row] = true;
        
        return foundTarget;
    }
    
}