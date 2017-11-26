package anaconda;

public class Head extends SnakePart{
    
    public Head (int y, int x, int v, int h){
        super(y, x, v, h);
    }
    
    @Override
    public void moveHead(){
        y += v;
        x += h;
    }
    
    @Override
    public void setDirection(int v, int h){
        this.v = v;
        this.h = h;
    }
}