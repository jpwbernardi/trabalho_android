package ajs.teste;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceView;

/**
 * Created by User on 04/06/2015.
 */
public class Background {

    private Bitmap bg, cloud;
    private int x, dx;

    public Background(Bitmap bg, Bitmap cloud){
        this.bg = bg;
        this.cloud = cloud;
        x = GamePanel.WIDTH;
        dx = GamePanel.CLOUDSPEED;
    }

    public void update(){
        x += dx;
        if(x < -GamePanel.WIDTH) x = GamePanel.WIDTH;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bg, 0, 0, null);
        canvas.drawBitmap(cloud, x, 0, null);
    }

}
