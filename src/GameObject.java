public class GameObject {
    private String type;
    private String symbol;

    GameObject(String type, String symbol){
        this.type = type;
        this.symbol = symbol;
    }

    GameObject(GameObject obj){
        this.type = obj.type;
        this.symbol = obj.symbol;
    }

    GameObject(){
        this.type = "space";
        this.symbol = ".";
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }
}