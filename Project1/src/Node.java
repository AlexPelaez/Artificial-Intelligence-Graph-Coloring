

import java.awt.*;
import java.awt.color.*;
public class Node {
    private float x;
    private float y;
    private Color c;

    public Node(float x, float y, Color c){
        this.x = x;
        this.y = y;
        this.c = c;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Color getC() {
        return c;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setC(Color c) {
        this.c = c;
    }
}
