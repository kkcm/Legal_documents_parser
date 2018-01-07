package main;

public class AbstractPart implements IPart {
    protected IPart up;
    protected String description;
    protected String body;
    protected IPart down;
    protected IPart right;
    protected IPart left;
//    private Body body;

    public void saveLine (String in){
        this.body = in;
    }

    public void addLine (String in){
        TextEditor textEditor = new TextEditor();
        this.body = textEditor.addText(this.body, in);
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

    public IPart getRight(){
        return  this.right;
    }

    public IPart getLeft(){
        return  this.left;
    }

    public String getBody(){
        return this.body;
    }

    public void setRight(IPart part){
        this.right = part;
    }

    public void setLeft(IPart part){
        this.left = part;
    }

}
