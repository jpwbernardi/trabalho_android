package ajs.teste;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameControl {
    private Bitmap bowl;
    private Integer score;
    private int maxScore;
    private int life;

    public GameControl(Bitmap bowl){
        score = 0;
        maxScore = 0; //Load do arquivo... Como??
        life = 5;
        this.bowl = bowl;
    }

    public void addScore(){
        score++;
        if(score > maxScore) maxScore = score;
    }

    public void resetScore(){
        score = 0;
    }

    public boolean loseLife(){
        life--;
        if(life == 0) return true;
        return false;
    }

    public void draw(Canvas canvas, float scale){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(23);
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        Rect rectText = new Rect();
        paint.getTextBounds(score.toString(), 0, score.toString().length(), rectText);

        canvas.drawText(score.toString(), GamePanel.WIDTH - 45, GamePanel.HEIGHT - rectText.height(), paint);
        canvas.drawBitmap(bowl, GamePanel.WIDTH - bowl.getWidth() - 50, GamePanel.HEIGHT - bowl.getHeight() - 10, null);
    }


}
