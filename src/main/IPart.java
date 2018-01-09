package main;

public interface IPart {

    void saveDescription(String in);

    void saveLine (String in);

    void addLine (String in);

    void setUp(IPart part);

    void setDown(IPart part);

    void setRight(IPart part);

    void setLeft(IPart part);

    String getDescription();

    String getBody();

    IPart getUp ();

    IPart getDown();

    IPart getRight();

    IPart getLeft();

    String toString();

}
