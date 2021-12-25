import objects.*;
import utils.*;
import javax.swing.*;

class Main{
    public static void main(String[] args) throws InterruptedException{

        //Definindo objetos
        Camera camera = new Camera(-7, 24, -18, 33);
        Sphere esfera_0 = new Sphere(0, 0, 100, 5);
        
        //colocando objetos no array de objetos
        Object3D objects[] = new Object3D[1];
        objects[0] = esfera_0;

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