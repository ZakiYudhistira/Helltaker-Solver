import java.util.List;
import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the level file:");
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();

        Config con = new Config(file);
        con.getGrid().printContainer();
        System.out.println("Player : " + con.getPlayer());
        System.out.println("Goal : " + con.getGoal());
        
        State start = new State(con.getGrid(), con.getPlayer(), con.getGoal(), "start", null, con.getMoves(), true, con.getDoor_point(), con.getKey_point());

        try {
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        if(con.getKey_status()){
            start.setDoor_point(con.getDoor_point());
            start.setKey_point(con.getKey_point());
            System.out.println("Key : " + con.getKey_point());
            System.out.println("Door : " + con.getDoor_point());
        }
        Algorithm gbfs = new Algorithm(start);
        try {
            List<String> res = gbfs.beginProcess();
            System.out.println(res);
            gbfs.printAnswer();
            System.out.println(res.size());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        scan.close();
    }
}
