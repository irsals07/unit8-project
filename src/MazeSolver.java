import java.util.ArrayList;

public class MazeSolver {

    private ArrayList<String> coordinates = new ArrayList<String>();
    int r = 0;
    int c = 0;
    private String[][] m;
    public MazeSolver(int r, int c, String[][] m){
        this.m = m;
        this.r = r;
        this.c = c;
    }
    public void runMaze(int r, int c, String[][]maze){
        boolean end = false;
        while(!end){
            if(c==maze[0].length-1 && r==maze.length-1){
                coordinates.add("(" + r+", " + c+ ")");
                end = true;
            }
            else if(foundDeadend(r, c, maze)){
                resetMaze(maze);
                maze[r][c] = "X";
                r = 0;
                c = 0;
                coordinates.clear();
            }
            else if(checkRight(r, c, maze)){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                c++;
            }
            else if(checkDown(r, c, maze)){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                r++;
            }
            else if(checkUp(r,c,maze)){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                r--;
            }
            else if(checkLeft(r,c, maze)) {
                maze[r][c] = "v";
                coordinates.add("(" + r + ", " + c + ")");
                c--;
            }
        }
    }

    public String getCoordinates() {
        String result = "";
        for(int i = 0; i<coordinates.size(); i++){
            result+=coordinates.get(i) + "---> ";
        }
        return result;
    }

    private static boolean checkRight(int r, int c, String[][] maze){
        if(c+1<maze[0].length && maze[r][c+1].equals(".")){
            return true;
        }
        return false;
    }
    private static boolean checkDown(int r, int c, String[][] maze){
        if(r+1 <maze.length && maze[r+1][c].equals(".")){
            return true;
        }
        return false;
    }
    private static boolean checkLeft(int r, int c, String[][] maze){
        if(c-1 >= 0 && maze[r][c-1].equals(".")){
            return true;
        }
        return false;
    }
    private static boolean checkUp(int r, int c, String[][] maze){
        if(r-1 >= 0 && maze[r-1][c].equals(".")){
            return true;
        }
        return false;
    }
    private static boolean foundDeadend(int r, int c, String[][] maze){
        if(!checkUp(r,c,maze) && ! checkDown(r,c,maze) && !checkRight(r,c,maze) && !checkLeft(r,c,maze)){
            return true;
        }
        return false;
    }
    private static String[][] resetMaze(String[][] maze){
        for(int r = 0; r<maze.length; r++){
            for(int c = 0; c<maze[0].length; c++){
                if(maze[r][c].equals("v")){
                    maze[r][c] = ".";
                }
            }
        }
        return maze;
    }
}
