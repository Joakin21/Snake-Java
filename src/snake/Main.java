
package snake;


import javax.swing.JFrame;
import java.awt.event.*;

public class Main implements KeyListener{
    
    private Snake snake;

    
    public Main(){
        JFrame frame = new JFrame();
        
        snake = new Snake(3);

        
        frame.setTitle("Snake Game");
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        
        frame.add(snake);

        snake.move();

    }
    public static void main(String[] args) {
        Main app = new Main();
         
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37)//LEFT
            snake.setDirection(2);
        if(e.getKeyCode()==38)//UP
            snake.setDirection(3);
        if(e.getKeyCode()==39)//RIGHT
            snake.setDirection(1);
        if(e.getKeyCode()==40)//DOWN
            snake.setDirection(4);  
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
