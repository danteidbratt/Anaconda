
package anaconda;

abstract public class SnakePart {
    
    int y;
    int x;
    int v;
    int h;
    String d;

    public SnakePart(int y, int x, int v, int h) {
        this.y = y;
        this.x = x;
        this.v = v;
        this.h = h;
    }
    
    public SnakePart(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    public void moveHead(){}
    
    public void setDirection(int v, int h){}
    
    public void moveBody(SnakePart snakePart){}
}