package ajs.teste;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int CLOUDSPEED = -5;
    public static final int QTDMAX = 30;
    public int qtdCookie = 3;
    public float scaleFactorX;
    public float scaleFactorY;

    private long newCookieTime;
    private boolean playing;

    private GameControl gameControl;
    private MainThread thread;
    private Background bg;
    private Cookie cookie[];


    public GamePanel(Context context){
        super(context);

        //add the callback to the suferholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter < 1000) {
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        scaleFactorX = getWidth()/(float)WIDTH;
        scaleFactorY = getHeight()/(float)HEIGHT;

        gameControl = new GameControl(BitmapFactory.decodeResource(getResources(), R.drawable.pote));
        playing = true;

        //Definindo imagens
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg), BitmapFactory.decodeResource(getResources(), R.drawable.nuvens));
        cookie = new Cookie[QTDMAX];
        for(int i = 0; i < 30; i++) cookie[i] = newCookie();

        newCookieTime = System.nanoTime();

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            for(int i = qtdCookie; playing && i >= 0; i--)
                if(cookie[i].collided(event.getRawX()/scaleFactorX, event.getRawY()/scaleFactorY)){
                    gameControl.addScore();
                    break;
                }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        bg.update();
        for(int i = 0; i <= qtdCookie; i++)
            if(cookie[i].update())
                gameControl.loseLife();

        //Adiciona novos cookies
        if((System.nanoTime() - newCookieTime)/1000000000 > 10){
            System.out.println("Novooo");
            if(qtdCookie < QTDMAX - 1) qtdCookie++;
            newCookieTime = System.nanoTime();
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas != null) {
            final int savedState = canvas.save();

            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            gameControl.draw(canvas, scaleFactorX);
            for(int i = 0; i <= qtdCookie; i++) cookie[i].draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }

    private Cookie newCookie(){
        return new Cookie(BitmapFactory.decodeResource(getResources(), R.drawable.cookie));
    }


}
