package ajs.teste;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by User on 19/06/2015.
 */
public class Fim extends GameObject{

    Integer score, maxScore;

    public Fim(){
        score = maxScore = 0;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        Rect rectText = new Rect();
        paint.getTextBounds("Score: " + score.toString(), 0, 7 + score.toString().length(), rectText);
        canvas.drawText("Score: " + score.toString(), GamePanel.WIDTH / 2 - rectText.width() / 2, GamePanel.HEIGHT / 2, paint);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        rectText = new Rect();
        paint.getTextBounds("Highscore: " + maxScore.toString(), 0, 11 + maxScore.toString().length(), rectText);
        canvas.drawText("Highscore: " + maxScore.toString(), GamePanel.WIDTH / 2 - rectText.width() / 2, GamePanel.HEIGHT / 1.6f, paint);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        rectText = new Rect();
        paint.getTextBounds("Toque para jogar novamente!", 0, 27, rectText);
        canvas.drawText("Toque para jogar novamente!", GamePanel.WIDTH / 2 - rectText.width() / 2, GamePanel.HEIGHT  - rectText.height() * 1.1f, paint);
    }

    public void setScore(int score){
        this.score = score;
        if(score > maxScore) maxScore = score;
    }

}
