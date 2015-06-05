package ajs.teste;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Random;

public class Cookie extends GameObject{

    public final int COUNTMAX = 10;
    public final float degree15 = -0.39269908169872415480783042290994f * 3;
    //private GameControl gameControl;
    private Bitmap image;
    private Matrix position;
    private float currentAngle, angle, r;
    private int count;


    public Cookie(Bitmap cookie){

        image = cookie;
        width = image.getWidth();
        height = image.getHeight();

        newPos();
    }

    public void rotateImage(float angle){
        Matrix newPosition = new Matrix();
        newPosition.postRotate(angle, this.width / 2, this.height / 2);
        newPosition.postTranslate(this.x, this.y);
        this.position.set(newPosition);
    }

    public boolean update(){
        //Retorna true se algum cookie passou

        this.rotateImage(currentAngle);

        currentAngle += angle;
        x += dx;
        y += dy;

        if(x > GamePanel.WIDTH) x = 0;
        else if(x + this.width < 0) x = GamePanel.WIDTH;

        //Sfó perde vida quando cookie chega no chão
        if(y > GamePanel.HEIGHT){
            newPos();
            return true;
        }

        return false;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.image, this.position, null);
    }

    public void newPos(){
        count++; //Quantas vezes foi chamada a função. Quanto mais vezes foi chamada, mais "dificil" é o cookie
        Random rand = new Random();

        this.x = 100 + rand.nextInt(GamePanel.WIDTH - this.width - 100); //podem ser "spawnadas no intervalo [100, WIDTH - 100 - this.width]
        this.y = -1 * rand.nextInt(5)*100 - height; //(-height, contaLouca)

        this.position = new Matrix();
        this.position.postTranslate(this.x, this.y);

        dx = rand.nextInt()% 5 / 10f;
        if(dx < 0) dx--;
        else dx++;
        dy = 5 + rand.nextInt(1 + count);
        System.out.println("dx: " + dx + " dy: " + dy);

        //if(rand.nextInt() % 2 == 0) dxa *= -1;

        angle = degree15 * (1 + rand.nextInt() % 5);

        setRaio();

        if(count > COUNTMAX) count = COUNTMAX;
    }

    public void setRaio(){
        r = width / 2f;
    }

    public boolean collided(double x, double y){
       // double tmp = (x - getCenterX()) * (x - getCenterX()) + (y - getCenterY()) * (y - getCenterY());
        //System.out.println("Temp => " + tmp + "  Raio ^ 2: " + r * r + "   x: " + x + " " + this.x + "  y: " + y + " " + this.y);
        if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height){
            //gameControl.addScore();
            newPos();
            return true;
        }

        return false;
    }

    public double getCenterX(){
        return x + width / 2f;
    }

    public double getCenterY(){
        return y + height / 2f;
    }


}
