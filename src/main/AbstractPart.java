package main;

public class AbstractPart implements IPart {
    protected String description;
    protected String body;
    protected IPart up;
    protected IPart down;
    protected IPart right;
    protected IPart left;

    public void saveDescription(String in){
        this.description = in;
    }

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

    public void setRight(IPart part){
        this.right = part;
    }

    public void setLeft(IPart part){
        this.left = part;
    }

    public String getDescription(){
        return this.description;
    }

    public String getBody(){
        return this.body;
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

    public String toString() {
        if(this.body != null && this.description != null){
            return this.description + this.body;
        }else if (this.description != null){
            return this.description;
        }else if (this.body != null){
            return this.body;
        } else {
            return null;
        }
    }
}
