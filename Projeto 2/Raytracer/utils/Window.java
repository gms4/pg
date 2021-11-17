package utils;

import javax.swing.*;

public class Window {

    private JFrame canvas;
    
    public Window(String title, int width, int height){
        canvas = new JFrame(title);
        canvas.setSize(width, height);
        canvas.setVisible(true);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(){
        this.canvas.setContentPane(new JLabel(new ImageIcon("output/image_0.png")));
    }
}
