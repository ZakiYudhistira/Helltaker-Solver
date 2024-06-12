import java.util.Stack;

public class GridContainer {
    public Stack<GameObject> object;
    public Point coordinate = new Point(-1, -1);

    public GridContainer(){
        this.object = new Stack<>();
        object.add(new GameObject());
    }

    public GridContainer(GridContainer gc) {
        this.coordinate = new Point(gc.coordinate.x, gc.coordinate.y);
        this.object = new Stack<>();
        for (GameObject obj : gc.object) {
            this.object.add(new GameObject(obj));
        }
    }

    public GridContainer(String type, String symbol){
        object.add(new GameObject());
        object.add(new GameObject(type, symbol));
    }

    public void setCoordinate(Integer x, Integer y){
        coordinate = new Point(x, y);
    }

    public void addGameObject(GameObject game){
        object.add(game);
    }

    public void removeGameObject(){
        object.pop();
    }

    public GameObject getGameObject(){
        return object.peek();
    }

    
}
