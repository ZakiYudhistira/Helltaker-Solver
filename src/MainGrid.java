import java.util.ArrayList;

public class MainGrid {
    public ArrayList<ArrayList<GridContainer>> content = new ArrayList<>();

    public MainGrid(Integer x_size, Integer y_size){
        this.content = new ArrayList<>();
        for(int i = 0 ; i < y_size ; i++){
            ArrayList<GridContainer> temp = new ArrayList<>(); 
            for(int j = 0 ; j < x_size ; j++){
                GridContainer grid = new GridContainer();
                grid.setCoordinate(i, j);
                
                if(i == 0 || j == 0 || j == x_size-1 || i == y_size - 1){
                    grid.addGameObject(new GameObject("border", "#"));
                }
                temp.add(grid);
            }
            this.content.add(temp);
        }
    }

    public MainGrid(MainGrid m){
        this.content = new ArrayList<>();
        Integer y_size = m.content.size();
        Integer x_size = m.content.get(0).size();
        for(int i = 0 ; i < y_size ; i++){
            ArrayList<GridContainer> temp = new ArrayList<>(); 
            for(int j = 0 ; j < x_size ; j++){
                GridContainer grid = new GridContainer(m.getGridContainer(new Point(j, i)));
                temp.add(grid);
            }
            this.content.add(temp);
        }
    }

    public GameObject getGameObject(Point point){
        return content.get(point.y).get(point.x).getGameObject();
    }

    public void addGameObject(Point point, GameObject object){
        content.get(point.y).get(point.x).addGameObject(object);
    }

    public void removeGameObject(Point point){
        content.get(point.y).get(point.x).removeGameObject();
    }

    public GameObject peekGameObject(Point point){
        return content.get(point.y).get(point.x).getGameObject();
    }

    public GameObject getUnderneath(Point point){
        GameObject temp = getGameObject(point);
        removeGameObject(point);
        GameObject ret = getGameObject(point);
        addGameObject(point, temp);
        return ret;
    }

    public void moveObject(Point p1, Point p2){
        addGameObject(p2, peekGameObject(p1));
        removeGameObject(p1);
    }

    public GridContainer getGridContainer(Point p){
        return content.get(p.y).get(p.x);
    }

    public void printContainer(){
        for(ArrayList<GridContainer> g : content){
            for(GridContainer gc : g){
                System.out.print(gc.getGameObject().getSymbol() + " ");
            }
            System.out.println("");
        }
    }

    public String getArrayStr(){
        String ret = "";
        for(int i = 1 ; i < content.size() - 1 ; i++){
            for(int j = 1 ; j < content.get(0).size() - 1 ; j++){
                ret += content.get(i).get(j).getGameObject().getSymbol();
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        String ret = "";
        for(int i = 0 ; i < content.size()  ; i++){
            for(int j = 0 ; j < content.get(0).size() ; j++){
                ret += content.get(i).get(j).getGameObject().getSymbol() + " ";
            }
            ret += "\n";
        }
        return ret;
    }
}
