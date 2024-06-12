import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class Config {
    private static Map<String, GameObject> content = new HashMap<String,GameObject>();

    private MainGrid grid;

    private Point player = null;
    private Point goal = null;
    private Integer moves;
    private Point door_point = new Point(0, 0);
    private Point key_point = new Point(0, 0);

    private boolean key_status = false;

    static {
        content.put("#", new GameObject("border","#"));
        content.put("@", new GameObject("block","@"));
        content.put("^", new GameObject("spike","^"));
        content.put(".", new GameObject("space","."));
        content.put("$", new GameObject("player","$"));
        content.put("!", new GameObject("finish","!"));
        content.put("*", new GameObject("enemy","*"));
        content.put("?", new GameObject("key","?"));
    }

 

    Config(String file_name){
        try {
            File tes = new File("test/" + file_name);
            Scanner reader = new Scanner(tes);
            
            boolean first = true;

            Integer column = -1;
            Integer row = -1;

            Integer i = 1;

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String parsed[] = data.trim().split("\\s+");

                if(first){
                    column = Integer.parseInt(parsed[0]);
                    row = Integer.parseInt(parsed[1]);
                    moves = Integer.parseInt(parsed[2]);
                    first = false;
                    grid = new MainGrid(column + 2, row + 2);
                    continue;
                }

                for(int j = 0 ; j < column ; j++){
                    if(parsed[j].equals("&")){
                        grid.addGameObject(new Point(j + 1, i), content.get("^"));
                        grid.addGameObject(new Point(j + 1, i), content.get("@"));
                        continue;
                        
                    }
                    if(parsed[j].equals("?")){
                        key_status = true;
                        key_point.x = j+1;
                        key_point.y = i;

                    }
                    if(parsed[j].equals("+")){
                        grid.addGameObject(new Point(j + 1, i), content.get("#"));
                        door_point.x = j+1;
                        door_point.y = i;
                        continue;
                    }
                    if(!parsed[j].equals(".")){
                        grid.addGameObject(new Point(j + 1, i), content.get(parsed[j]));
                    }
                    if(parsed[j].equals("$")){
                        player = new Point(j+1, i);
                    }
                    if(parsed[j].equals("!")){
                        goal = new Point(j+1, i);
                    }
                    
                    
                }
                i++;
            }

            reader.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    GameObject getObject(String s){
        return content.get(s);
    }

    public MainGrid getGrid() {
        return grid;
    }

    public Point getGoal() {
        return goal;
    }

    public Point getPlayer() {
        return player;
    }

    public Point getDoor_point() {
        return door_point;
    }

    public Point getKey_point() {
        return key_point;
    }

    public void printState(){
        grid.printContainer();
    }

    public boolean getKey_status(){
        return key_status;
    }

    public Integer getMoves() {
        return moves;
    }

    public void setMoves(Integer moves) {
        this.moves = moves;
    }
}
