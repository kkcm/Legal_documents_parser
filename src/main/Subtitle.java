package main;

public class Subtitle extends AbstractPart implements IPart{

    public Subtitle (){
        this.description = "stworzyłem rozdział";
    }

    public void saveLine (String in){
        this.body = in;
    }


    public String toString() {
        return "tymczasowe body: "+ this.body;
    }
}
