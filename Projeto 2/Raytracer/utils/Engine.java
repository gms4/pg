package utils;

import java.io.File;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Canvas;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.*;

public class Engine {

    private long total_render_begin;
    private long frame_render_begin;
    private long total_render_end;
    private long frame_render_end;

    
    public void render(Scene scene){

        //Passando os elementos da cena
        int width = scene.width;
        int height = scene.width;
        Object3D[] objects = scene.objects;
        Camera camera = scene.camera;
        int totalFrames = 5;

        //Gerando tela
        Window window = new Window("Ray-Tracing", width, height);
        
        //Renderizando
        this.total_render_begin = System.nanoTime();
        for(int r = 0; r < totalFrames; r++){
            this.frame_render_begin = System.nanoTime();

            File image = new File("output/image_"+r+".png");
            BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    //render
                    Ray ray = camera.castRay(x, y); 
                    for(int i = 0; i < objects.length; i++){
                        if(objects[i].intersects(ray)){
                            buffer.setRGB(x, y, 255);
                        }else{
                            buffer.setRGB(x, y, 0);
                        }
                    }
                }   
            }

            try{
                ImageIO.write(buffer, "PNG", image);
            } catch (Exception e){
                System.out.println("Não foi possível escrever o arquivo da imagem");
                System.exit(1);
            }

            frame_render_end = System.nanoTime();

            //Reportando o tempo de renderização de um frame
            this.report(r, totalFrames, frame_render_end - frame_render_begin);
        }
        //Reportando o tempo de renderização total
        total_render_end = System.nanoTime();
        this.report(total_render_end - total_render_begin);

        window.update();
    }




    private void report(int frame, int totalFrames, float delta){
        System.out.println(Color.YELLOW + "Frame " + (frame+1) + "/" + totalFrames + Color.RESET + " - tempo de renderização: " + delta/1000000000.0F + " segundos");
    }
    private void report(float delta){
        System.out.println(Color.GREEN + "\nTotal" + Color.RESET + " - Tempo de renderização: " + delta/1000000000.0F + " segundos");
    }

}
