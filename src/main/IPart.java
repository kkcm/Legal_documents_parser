package main;

public interface IPart {

    void saveLine (String in);

    void setUp(IPart part);

    void setDown(IPart part);

    void setRight(IPart part);

    void setLeft(IPart part);

    IPart getRight();

    IPart getLeft();

    String toString();

    IPart getUp ();

    IPart getDown();

}
