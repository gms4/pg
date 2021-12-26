import objects.*;
import utils.*;
import javax.swing.*;
import java.awt.Color;

class Main{
    public static void main(String[] args) throws InterruptedException{

        //Definindo objetos
        // Camera camera = new Camera(-7, 24, -18, 33, 640, 480, 40, new Point(3, 4, 2), new Vector(2, 2, 1));
        // Sphere esfera_0 = new Sphere(2, 3, 6, 2);
        Camera camera = new Camera(0, 0, 0, 120, 720, 480, 360, new Point(0, 0, 120), new Vector(1, 0, 0));
        Sphere esfera_0 = new Sphere(0, 0, 360, 50, new Material(new Color(0, 0, 255)));
        Sphere esfera_1 = new Sphere(400, 0, 360, 90, new Material(new Color(255, 0, 0)));
        
        //colocando objetos no array de objetos
        Object3D objects[] = new Object3D[2];
        objects[0] = esfera_0;
        objects[1] = esfera_1;

        //criando a cena
        Scene scene = new Scene(camera, objects);

        //Renderizando
        Engine engine = new Engine();
        engine.render(scene);

        //Criando janela
        Window window = new Window("Ray-Tracing", camera.pixelsWide, camera.pixelsHigh, scene);
        window.frame.setContentPane(new JLabel(new ImageIcon("output/image_0.png")));
        window.setVisible();
        
    }
}