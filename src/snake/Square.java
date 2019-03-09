
package snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Square extends JPanel{
    
    private int x;
    private int y;
    private int w;
    private int h;
    private int dire;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        dire = 2;
        w = 20;
        h = 20;
    }
    public void moverSquare(){
        if(dire == 1)
            x = x + w;
        if(dire == 2)
            x = x - w;
        if(dire == 3)
            y = y - h;
        if(dire == 4)
            y = y + h;
    }
    public void setDire(int dire){
        this.dire = dire;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getDire(){
        return dire;
    }
    public int getW(){
        return w;
    }
    public int getH(){
        return h;
    }
    public int getXvalue(){
        return x;
    }
    public int getYvalue(){
        return y;
    }
}
