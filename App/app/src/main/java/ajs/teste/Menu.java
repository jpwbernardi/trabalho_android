package ajs.teste;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by User on 19/06/2015.
 */
public class Menu extends GameObject{
    Bitmap image;

    public Menu(Bitmap x){
        image = x;
    }

    public int collided(float x, float y){
        if(y > 279 && y < 330 && x > 235 && x < 385){
            return 1;
        }/*else if(y > 349 && y < 400 && x > 235 && x < 385){
            return 2;
        }*/

        return 0;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, 0, 0, null);
    }

}
