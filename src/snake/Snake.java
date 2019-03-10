
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Snake extends JPanel implements Runnable{
    
    private int lon;
    private ArrayList<Square> squares = new ArrayList<Square>();
    private Thread mainTheread;
    private int direction;
    //arrays to food
    private int[] arrXpos_food= new int[37];
    private int[] arrYpos_food= new int[27];
    private Square food;
    
    private int score;
    private boolean collision;
    
    public Snake(int lon){
        mainTheread = new Thread(this,"mainThread");
        score = 0;
        //We start with a snake of 3 squares
        initSnake(lon);
        //food
        int n = 40;
        for(int i=0; i<arrXpos_food.length; i++)
        {
            arrXpos_food[i] = n;
            if(i<arrYpos_food.length)
                arrYpos_food[i] = n;
            n = n+20;
        }
        food = new Square(0,0);
        foodRandPosition();

    }
    public void initSnake(int lon){
        //We start with a snake of 3 squares
        direction = 2;
        this.lon = lon;
        Square sqr;
        int xSqrd = 200; int ySqrd = 80;//x,y initial of the snake (first square)
        for(int i=0; i<lon; i++)
        {
            sqr = new Square(xSqrd,ySqrd); 
            squares.add(sqr);
            xSqrd = xSqrd + sqr.getW();
        }
        collision = false;
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g); 
        //paint food
        g.setColor(Color.blue);
        g.fillRect(food.getXvalue(), food.getYvalue(), food.getW(), food.getH());
        Square head = squares.get(0);
        //paint and move snake
        g.setColor(Color.red);
        head.setDire(direction);
        //Detect line colitions
        if(head.getXvalue() < 40 || head.getXvalue() > 760 || head.getYvalue() < 40 || head.getYvalue() > 560)
            reboot();
        for(int i=squares.size()-1; i>=0; i--)
        { 
            
            //Paint
            g.fillRect(squares.get(i).getXvalue(), squares.get(i).getYvalue(), squares.get(i).getW(), squares.get(i).getH());
            squares.get(i).moverSquare();
            //change direction
            if(i>0){
                int nextDire = squares.get(i-1).getDire();
                squares.get(i).setDire(nextDire);

            } 
            
        }
        //paint Lines
        g.setColor(Color.black);
        //Horizontal lines
        g.drawLine(40, 40, 780, 40);
        g.drawLine(40, 580, 780, 580);
        //vertical lines
        g.drawLine(40, 40, 40, 580);
        g.drawLine(780, 40, 780, 580);
        //paint score
        g.drawString("Score: "+score, 40, 30);
    }
    public void move(){
        mainTheread.start();
    }
    public ArrayList<Square> getSquares(){
        return squares;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    public void detectCollision(){
        Square head = squares.get(0);
        for (int i = 1; i < squares.size()-1; i++){//no take head
            Square actual =  squares.get(i);
            if(head.getXvalue() == actual.getXvalue() && head.getYvalue() == actual.getYvalue())
               collision = true; 
        }
        
        if(collision)
            reboot();
    }
    public void foodRandPosition(){//food appear in a random position on the window
        double xRand, yRand;
        xRand = (Math.random() * (((arrXpos_food.length-1) - 0) + 1)) + 0;
        yRand = (Math.random() * (((arrYpos_food.length-1) - 0) + 1)) + 0;
        food.setX(arrXpos_food[(int) xRand]);
        food.setY(arrYpos_food[(int) yRand]);
 
    }
    public int headDirection(){
        Square head = squares.get(0);
        return head.getDire();
    }
    public void reboot(){
        //dejo el array en 3 squares
        squares.clear();
        initSnake(lon);
        score = 0;
        foodRandPosition();
    }
    public void eat()
    {
        if(food.getXvalue() == squares.get(0).getXvalue() && food.getYvalue() == squares.get(0).getYvalue())
        {
            score = score + 1;
            //add square to squares 
            Square new_square;
            Square last_square = squares.get(squares.size()-1);
            int x_new_square=0, y_new_square =0;
            int dire_last_square = last_square.getDire();
            
            if (dire_last_square==1){// right
                y_new_square = last_square.getYvalue();
                x_new_square = last_square.getXvalue()-last_square.getW();
            }
            if (dire_last_square==2){//left
                y_new_square = last_square.getYvalue();
                x_new_square = last_square.getXvalue()+last_square.getW();
            }
            if (dire_last_square==3){//up
                y_new_square = last_square.getYvalue()+last_square.getH();
                x_new_square = last_square.getXvalue();
            }
            if (dire_last_square==4){//down
                y_new_square = last_square.getYvalue()-last_square.getH();
                x_new_square = last_square.getXvalue();
            }
            new_square = new Square(x_new_square,y_new_square);
            new_square.setDire(dire_last_square);
            squares.add(new_square);
            foodRandPosition();
        }
    }
    @Override
    public void run() {
        try{  
            while(true)
            {
                eat();
                detectCollision();
                repaint();
                
                Thread.sleep(200);
                
            }
        }catch(InterruptedException ex){}
    }
    
    
}
