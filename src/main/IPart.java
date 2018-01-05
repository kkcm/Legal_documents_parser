package main;

public interface IPart {

    void saveLine (String in);

    void setUp(IPart part);

    void setDown(IPart part);

    String toString();

    IPart getUp ();

    IPart getDown();

}
