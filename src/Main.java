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
            System.out.println(coordinates);
            if(c==maze[0].length-1 && r==maze.length-1){
                coordinates.add("(" + r+", " + c+ ")");
                end = true;
            }
            else if(c+1<maze[0].length && maze[r][c+1].equals(".")){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                c++;
            }
            else if(r+1 <maze.length && maze[r+1][c].equals(".")){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                r++;
            }
            else if(r-1 >= 0 && maze[r-1][c].equals(".")){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                r--;
            }
            else if(c-1 >= 0 && maze[r][c-1].equals(".")){
                maze[r][c] = "v";
                coordinates.add("(" + r+", " + c+ ")");
                c--;
            }
            //if there are multiple ways to go, it'll create a spawn point and follow the original algorithm
            boolean dnr = r+1 < maze.length && maze[r+1][c].equals(".") && c+1 <maze[0].length && maze[r][c+1].equals(".");
            boolean unr = r-1>=0 && maze[r-1][c].equals(".") && c+1 <maze[0].length && maze[r][c+1].equals(".");
            boolean dnl = r+1 < maze.length && maze[r+1][c].equals(".") && c-1 >=0 && maze[r][c-1].equals(".");
            boolean unl = r-1 >= 0 && maze[r-1][c].equals(".") && c-1 >=0 && maze[r][c-1].equals(".");
            if(dnr && unr && dnl && unl){
                rSafe = r;
                cSafe = c;
                int count = 0;
                if(c==maze[0].length-1 && r==maze.length-1){
                    coordinates.add("(" + r+", " + c+ ")");
                    end = true;
                }
                else if(c+1<maze[0].length && maze[r][c+1].equals(".")){
                    maze[r][c] = "v";
                    coordinates.add("(" + r+", " + c+ ")");
                    c++;
                    count++;
                }
                else if(r+1 <maze.length && maze[r+1][c].equals(".")){
                    maze[r][c] = "v";
                    coordinates.add("(" + r+", " + c+ ")");
                    r++;
                    count++;
                }
                else if(r-1 >= 0 && maze[r-1][c].equals(".")){
                    maze[r][c] = "v";
                    coordinates.add("(" + r+", " + c+ ")");
                    r--;
                    count++;
                }
                else if(c-1 >= 0 && maze[r][c-1].equals(".")){
                    maze[r][c] = "v";
                    coordinates.add("(" + r+", " + c+ ")");
                    c--;
                    count++;
                }
                // if there is a dead end, it will go back to the spawn point and delete the last added coordinates
                while(r-1>=0 && r+1<maze.length && c-1 >=0 && c+1<maze[0].length){
                    if(!maze[r+1][c].equals(".") && !maze[r-1][c].equals(".") && !maze[r][c+1].equals(".") && !maze[r][c-1].equals(".")){
                        r=rSafe;
                        c=cSafe;
                        for(int i = 0; i<count-1; i++){
                            coordinates.remove(coordinates.get(coordinates.size()-1));
                        }
                    }
                }
            }

        }
        System.out.println(coordinates);
        for (int i = 0; r < maze.length; r++) {
            for (int j = 0; c < maze[0].length; c++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
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