import objects.*;
import utils.*;

class Main{
    public static void main(String[] args){
        
        //Definindo tamanho da tela
        int width = 640;
        int height = 480;

        //Definindo objetos
        Camera camera = new Camera(0, 0, -1, 15);
        Sphere esfera_0 = new Sphere(0, 0, 80, 4);
        Sphere esfera_1 = new Sphere(0, 0, 100, 2);
        
        //colocando objetos no array de objetos
        Object3D objects[] = new Object3D[2];
        objects[0] = esfera_0;
        objects[1] = esfera_1;

        //criando a cena
        Scene scene = new Scene(camera, objects, width, height);

        //Renderizando
        Engine engine = new Engine();
        engine.render(scene);

    }
}