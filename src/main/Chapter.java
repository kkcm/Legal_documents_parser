package main;

public class Chapter extends AbstractPart implements IPart{

    public Chapter (){
        this.description = null;
        this.right = null;
        this.left = null;
        this.up = null;
        this.down = null;
    }

 /*   public String toString() {
        if(this.body != null && this.description != null){
            return this.description+'\n'+this.body;
        }else if (this.description != null){
            return this.description;
        }else if (this.body != null){
            return this.body;
        } else {
            return null;
        }
    }
*/
}
