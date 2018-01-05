package main;

public class Chapter extends AbstractPart implements IPart{

    public Chapter (){
        this.description = "stworzyłem rozdział";
    }


    public void setDown(IPart o){
        this.down = o;
    }
}
