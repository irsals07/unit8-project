import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String[][] maze = getMaze("src/maze");
        ArrayList<String> coordinates = new ArrayList<String>();
        boolean end = false;
        System.out.println();
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                System.out.print(maze[r][c] + " ");
            }
            System.out.println();
        }
        int r = 0;
        int c = 0;
        int rSafe = 0;
        int cSafe = 0;
        while(end==false){
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    System.out.print(maze[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
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
        System.out.println(coordinates);
    }
    public static boolean checkRight(int r, int c, String[][] maze){
        if(c+1<maze[0].length && maze[r][c+1].equals(".")){
            return true;
        }
        return false;
    }
    public static boolean checkDown(int r, int c, String[][] maze){
        if(r+1 <maze.length && maze[r+1][c].equals(".")){
            return true;
        }
        return false;
    }
    public static boolean checkLeft(int r, int c, String[][] maze){
        if(c-1 >= 0 && maze[r][c-1].equals(".")){
            return true;
        }
        return false;
    }
    public static boolean checkUp(int r, int c, String[][] maze){
        if(r-1 >= 0 && maze[r-1][c].equals(".")){
            return true;
        }
        return false;
    }
    public static boolean foundDeadend(int r, int c, String[][] maze){
        if(!checkUp(r,c,maze) && ! checkDown(r,c,maze) && !checkRight(r,c,maze) && !checkLeft(r,c,maze)){
            return true;
        }
        return false;
    }
    public static String[][] resetMaze(String[][] maze){
        for(int r = 0; r<maze.length; r++){
            for(int c = 0; c<maze[0].length; c++){
                if(maze[r][c].equals("v")){
                    maze[r][c] = ".";
                }
            }
        }
        return maze;
    }
    public static ArrayList<String> resetArrayList(ArrayList a){
        a = new ArrayList<String>();
        return a;
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }
}