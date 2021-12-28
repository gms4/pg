import objects.*;
import utils.*;
import javax.swing.*;
import java.awt.Color;

class Main{
    public static void main(String[] args) throws InterruptedException{

        //Definindo materiais(Color base, double ambient, double diffuse, double specularity, double q, double reflexion, double transmission) 
        Material m1 = new Material(new Color(0, 0, 255), 0.1, 1, 0.5, 10, 1, 0);
        Material m2 = new Material(new Color(255, 0, 255), 0.1, 0.8, 1, 10, 1, 0);

        //Definindo objetos
        Camera camera = new Camera(0, 0, 0, 360, 720, 480, 360, new Point(0, 0, 120), new Vector(1, 0, 0));
        Sphere esfera_0 = new Sphere(0, 0, 360, 50, m1);
        Sphere esfera_1 = new Sphere(200, 0, 300, 50, m2);
        Light light_0 = new Light(0, 200, 200, 50, 50, 50);

        //colocando objetos no array de objetos
        Object3D objects[] = new Object3D[2];
        objects[0] = esfera_0;
        objects[1] = esfera_1;

        //adicionando pontos de luz no array de luz
        Light lights[] = new Light[1];
        lights[0] = light_0;

        //criando a cena
        Scene scene = new Scene(camera, objects, lights);

        //Renderizando
        Engine engine = new Engine();
        engine.render(scene);

        //Criando janela
        Window window = new Window("Ray-Tracing", camera.pixelsWide, camera.pixelsHigh, scene);
        window.frame.setContentPane(new JLabel(new ImageIcon("output/image_0.png")));
        window.setVisible();
        
    }
}