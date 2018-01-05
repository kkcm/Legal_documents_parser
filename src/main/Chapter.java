package main;

public class Chapter extends AbstractPart implements IPart{

    public Chapter (){
        this.description = "stworzyłem rozdział";
        this.up = null;
        this.down = null;
    }


    public void setDown(IPart o){
        this.down = o;
    }
}
