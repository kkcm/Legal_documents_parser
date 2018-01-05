package main;

public class AbstractPart implements IPart {
    protected IPart up;
    protected String description;
    protected String body;
    protected IPart down;
//    private Body body;

    public void saveLine (String in){
        this.body = in;
    }

    public String toString() {
        return "tymczasowe body: "+ this.body;
    }


}
