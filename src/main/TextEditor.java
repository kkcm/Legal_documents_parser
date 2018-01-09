package main;

public class TextEditor {
    private StringBuilder builder;

    public TextEditor(){
        this.builder = new StringBuilder();
    }

    public String addText(String in1, String in2){
        this.builder = new StringBuilder();
        if (in1 != null && in1.length() > 0 && in1.charAt(in1.length() - 1) == '-') {
            in1 = in1.substring(0, in1.length() - 1);
            builder.append(in1).append(in2);
        }else{
            builder.append(in1).append(' ').append(in2);
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
