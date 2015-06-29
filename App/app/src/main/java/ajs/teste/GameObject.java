package ajs.teste;

import android.graphics.Rect;

public abstract class GameObject {
    protected float x, y, dy, dx;

    protected int width;
    protected int height;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    //Vamos  tirar isso, ne?
    public Rect getRectange(){
        return new Rect((int)x,(int)y,(int) (x + width), (int)(y + height));
    }
}
