package main;

public class TextEditor {
    private StringBuilder builder;

    public TextEditor(){
        this.builder = new StringBuilder();
    }

    public String addText(String in1, String in2){
        if (in1 != null && in1.length() > 0 && in1.charAt(in1.length() - 1) == '-') {
            System.out.println("tutaj jest myślnik");
            in1 = in1.substring(0, in1.length() - 1);
            builder.append(in1).append(in2);
        }else{
            System.out.println("tutaj NIE ma myślnika");
            builder.append(in1).append(' ').append(in2);
        }
        return this.builder.toString();
    }

}
