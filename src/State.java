public class State implements Comparable<State>{
    public State prev_state;

    private String prev_move;

    private MainGrid grid_state;

    private Point player_coordinate;
    private Point goal_coordinate;
    private Point key_point = null;
    private Point door_point = null;

    private Integer moves_left;

    private Boolean key_status;

    public State(MainGrid grid, Point player, Point goal, String move, State state, Integer moves, Boolean status, Point door, Point key){
        grid_state = grid;
        player_coordinate = player;
        goal_coordinate = goal;
        prev_move = move;
        prev_state = state;
        moves_left = moves;
        key_status = status;
        door_point = door;
        key_point = key;
    }

    @Override
    public int compareTo(State e){
        Integer player_distance = player_coordinate.getDistance(goal_coordinate) - moves_left;
        Integer player_other = e.getPlayer_coordinate().getDistance(e.getKey_point()) - e.moves_left;
        Integer key_distance_player = player_coordinate.getDistance(key_point) - moves_left;
        Integer key_distance_other = e.getPlayer_coordinate().getDistance(e.key_point) - e.moves_left;
        if(!key_status){
            return player_distance.compareTo(player_other);
        } else {
            return key_distance_player.compareTo(key_distance_other);
        }
    }

    public String getPrev_move() {
        return prev_move;
    }

    public void setPrev_move(String prev_move) {
        this.prev_move = prev_move;
    }

    public MainGrid getGrid_state() {
        return grid_state;
    }

    public void setGrid_state(MainGrid grid_state) {
        this.grid_state = grid_state;
    }

    public Point getPlayer_coordinate() {
        return player_coordinate;
    }

    public Point getGoal_coordinate() {
        return goal_coordinate;
    }

    public State getPrev_state() {
        return prev_state;
    }

    public Integer getMoves_left() {
        return moves_left;
    }

    public Point getDoor_point() {
        return door_point;
    }

    public Point getKey_point() {
        return key_point;
    }

    public void setDoor_point(Point door_point) {
        this.door_point = door_point;
    }

    public void setKey_point(Point key_point) {
        this.key_point = key_point;
    }

    public State moveTo(String command) throws Exception{
        if(moves_left <= 0){
            throw new Exception("Ran out of moves");
        }

        if(command.equals("up")){
            return moveTo(new Point(player_coordinate.x, player_coordinate.y-1), command);
        } else if (command.equals("down")){
            return moveTo(new Point(player_coordinate.x, player_coordinate.y+1), command);
        } else if (command.equals("left")){
            return moveTo(new Point(player_coordinate.x-1, player_coordinate.y), command);
        } else if (command.equals("right")){
            return moveTo(new Point(player_coordinate.x+1, player_coordinate.y), command);
        } else {
            throw new Exception("Invalid command");
        }
    }

    public State moveTo(Point goal, String command) throws Exception{
        MainGrid ret = new MainGrid(grid_state);

        String current = grid_state.getGameObject(goal).getType();
        if(current.equals("border")){
            throw new Exception("Illegal move");
        }

        if(current.equals("block")){
            String target = null;
            Point p_new = null;

            if(command.equals("up")){
                p_new = new Point(goal.x, goal.y - 1);
            }

            if(command.equals("down")){
                p_new = new Point(goal.x, goal.y + 1);
            }

            if(command.equals("left")){
                p_new = new Point(goal.x - 1, goal.y);
            }

            if(command.equals("right")){
                p_new = new Point(goal.x + 1, goal.y);
            }

            target = grid_state.getGameObject(p_new).getType();

            if(target.equals("space") || target.equals("spike") || target.equals("finish")){
                ret.moveObject(goal, p_new);
                
                return new State(ret, player_coordinate, goal_coordinate, command, this, moves_left-1, key_status, door_point, key_point);
            } else {
                throw new Exception("Illegal move");
            }
        }

        if(current.equals("enemy")){
            String target = null;
            Point p_new = null;

            if(command.equals("up")){
                p_new = new Point(goal.x, goal.y - 1);
            }

            if(command.equals("down")){
                p_new = new Point(goal.x, goal.y + 1);
            }

            if(command.equals("left")){
                p_new = new Point(goal.x - 1, goal.y);
            }

            if(command.equals("right")){
                p_new = new Point(goal.x + 1, goal.y);
            }

            target = grid_state.getGameObject(p_new).getType();
            if(target.equals("space") || target.equals("finish")){
                ret.moveObject(goal, p_new);
                if(grid_state.getUnderneath(player_coordinate).getType().equals("spike")){
                    return new State(ret, player_coordinate, goal_coordinate, command, this, moves_left-2, key_status, door_point, key_point);
                }
                return new State(ret, player_coordinate, goal_coordinate, command, this, moves_left-1, key_status, door_point, key_point);
            }
            else {
                ret.removeGameObject(goal);
                if(grid_state.getUnderneath(player_coordinate).getType().equals("spike")){
                    return new State(ret, player_coordinate, goal_coordinate, command, this, moves_left-2, key_status, door_point, key_point);
                }
                return new State(ret, player_coordinate, goal_coordinate, command, this, moves_left-1, key_status, door_point, key_point);
            }
        }

        if(current.equals("spike")){
            ret.moveObject(player_coordinate, goal);
            return new State(ret, goal, goal_coordinate, command, this, moves_left - 2, key_status, door_point, key_point);
        }

        if(current.equals("space")){
            ret.moveObject(player_coordinate, goal);
            return new State(ret, goal, goal_coordinate, command, this, moves_left - 1, key_status, door_point, key_point);
        }

        if(current.equals("finish")){
            ret.moveObject(player_coordinate, goal);

            return new State(ret, goal, goal_coordinate, command, this, moves_left - 1, key_status, door_point, key_point);
        }

        if(current.equals("key")){
            ret.removeGameObject(door_point);
            ret.removeGameObject(goal);
            ret.moveObject(player_coordinate, goal);

            return new State(ret, goal, goal_coordinate, command, this, moves_left - 1, false, door_point, key_point);
        }

        throw new Exception("Illegal move");
    }

    public String generateKey(){
        return grid_state.getArrayStr() + moves_left;
    }
}
