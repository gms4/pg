package utils;

import java.awt.*;
import javax.swing.*;

public class Window extends Canvas implements Runnable{

    public Thread thread;
    public JFrame frame;
    public String title;
    private Engine engine = new Engine();
    private Scene scene;
    public boolean running = false;
    
    public Window(String title, int width, int height, Scene scene){
        this.title = title;
        this.scene = scene;
        frame = new JFrame(this.title);
        Dimension size = new Dimension(width, height);
        frame.setPreferredSize(size);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(25, 25, 25));
        //frame.setResizable(false);
    }
    
    public synchronized void start(){
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();

    }

    public synchronized void stop(){
        this.running = false;
        try{
            this.thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        long now;
        int fps = 60;
        final double ns = 1000000000.0 / fps;
        double delta = 0;
        int frames = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                this.update();
                delta--;
            }
            
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                this.frame.setTitle(this.title + " | " + frames + " FPS");
                frames = 0;
            }
        }

        this.stop();
    }

    private void update(){
        this.frame.setContentPane(new JLabel(new ImageIcon("output/image_0.png")));
    }

    public void setVisible(){
        frame.setVisible(true);
    }

}
