package main;

public class TextEditor {
    private StringBuilder builder;

    public TextEditor(){
        this.builder = new StringBuilder();
    }

    public String addText(String in1, String in2){
        this.builder = new StringBuilder();
        if (in1 != null && in1.length() > 0 && in1.charAt(in1.length() - 1) == '-') {

            String[] in2parts = this.cutLine(in2, " ");

            in1 = in1.substring(0, in1.length() - 1);
            builder.append(in1).append(in2parts[0]);

            String in11 = this.builder.toString();

            String in2again = this.concatTableElement(in2parts, " ", 1, in2parts.length -1);
            this.builder = new StringBuilder();

            builder.append(in11).append('\n').append(in2again);

        }else{
            builder.append(in1).append('\n').append(in2);
        }
        return this.builder.toString();
    }

    public String[] cutLine(String in, String pattern){
        String[] parts = in.split(pattern);
        return parts;
    }

    public String concatTableElement(String[] in, String pattern, Integer firstIndex, Integer lastIndex) {
        this.builder = new StringBuilder();
        Integer i;
        for (i=firstIndex; i <= lastIndex; i++) {
            this.builder.append(in[i]);
            this.builder.append(pattern);
        }
        String str =  this.builder.toString();
        if (str.length()-1 >=0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
