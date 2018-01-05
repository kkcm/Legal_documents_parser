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

    public void setUp(IPart part){
        this.up = part;
    }

    public void setDown(IPart part){
        this.down = part;
    }

    public String toString() {
        return "tymczasowe body: "+ this.body;
    }

    public IPart getUp (){
        return this.up;
    }

    public IPart getDown(){
        return  this.down;
    }

}
