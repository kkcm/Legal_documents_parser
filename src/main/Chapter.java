package main;

public class Chapter extends AbstractPart implements IPart{

    public Chapter (){
        this.description = "stworzyłem rozdział";
    }

    public void saveLine (String in){
        this.body = in;
    }


    public String toString() {
        return "tymczasowe body: "+ this.body;
    }

    public void setDown(IPart o){
        this.down = o;
    }
}
