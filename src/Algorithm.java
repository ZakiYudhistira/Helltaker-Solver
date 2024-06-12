import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Algorithm {
    HashMap<String, Boolean> visited;
    PriorityQueue<State> main_queue;
    List<String> answer;
    List<String> test_purpose;

    public Algorithm(State start){
        main_queue = new PriorityQueue<>();
        visited = new HashMap<>();
        answer = new ArrayList<>();
        test_purpose = new ArrayList<>();

        main_queue.add(start);
    }

    public void getAvailableNodes(State state){
        try {
            State newState = state.moveTo("up");
            main_queue.add(newState);
        } catch (Exception e){
           
        }

        try {
            State newState = state.moveTo("down");
            main_queue.add(newState);
        } catch (Exception e){
            
        }

        try {
            State newState = state.moveTo("left");
            main_queue.add(newState);
        } catch (Exception e){

        }

        try {
            State newState = state.moveTo("right");
            main_queue.add(newState);
        } catch (Exception e){

        }
    }

    public List<String> beginProcess() throws Exception{
        while(!main_queue.isEmpty()){
            State proc = main_queue.poll();

            if(proc.getGoal_coordinate().equals(proc.getPlayer_coordinate())){
                updateAnswer(proc);

                return answer.reversed();
            }

            if(visited.containsKey(proc.generateKey())){
                continue;
            }
            
            visited.put(proc.generateKey(), true);
            getAvailableNodes(proc);
        }
        throw new Exception("No path is available");
    }

    public void updateAnswer(State state){
        while(!state.getPrev_move().equals("start")){
            answer.add(state.getPrev_move());
            // System.out.println(state.getMoves_left());
            test_purpose.add(state.getGrid_state().toString());
            if(state.getPrev_state() == null){
                break;
            }
            state = state.getPrev_state();
            
        }
        test_purpose = test_purpose.reversed();
    }

    public void printAnswer(){
        
        for(String s : test_purpose){
            System.out.println(s);
            try {

                Thread.sleep(300);
            } catch (Exception e){
    
            }
        }
    }

    
}
