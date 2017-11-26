package anaconda;

public class Body extends SnakePart{
    
    public Body (int y, int x){
        super(y, x);
    }

    @Override
    public void moveBody(SnakePart snakePart) {
        x = snakePart.x;
        y = snakePart.y;
    }
}