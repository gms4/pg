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
    
    private int resWide, resHigh;
    private long aspect_ratio;

     
    public void render(Scene scene){

        //Passando os elementos da cena
        this.resWide = scene.camera.pixelsWide;
        this.resHigh= scene.camera.pixelsHigh;
        this.aspect_ratio = (long)this.resWide / this.resHigh;
        Object3D[] objects = scene.objects;
        int totalFrames = 1;

        //Renderizando
        this.total_render_begin = System.nanoTime();
        for(int r = 0; r < totalFrames; r++){
            this.frame_render_begin = System.nanoTime();

            Colors pixel_color;

            File image = new File("output/image_"+r+".png");
            BufferedImage buffer = new BufferedImage(this.resWide, this.resHigh, BufferedImage.TYPE_INT_RGB);

            for(int h = 0; h < this.resHigh; h++){
                for(int w = 0; w < this.resWide; w++){
                    //render
                    Ray ray = scene.camera.castRay(w, h); 
                    for(int i = 0; i < objects.length; i++){
                        if(objects[i].intersects(ray)){
                            pixel_color = objects[i].color;
                            buffer.setRGB(w, h, 255);
                        }else{ 
                            buffer.setRGB(w, h, 0);
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

            if(r < 10){
                scene.camera.origin.x += 5;
            }
        }
        //Reportando o tempo de renderização total
        total_render_end = System.nanoTime();
        this.report(total_render_end - total_render_begin);

    }




    private void report(int frame, int totalFrames, float delta){
        System.out.println(Colors.YELLOW + "Frame " + (frame+1) + "/" + totalFrames + Colors.RESET + " - tempo de renderização: " + delta/1000000000.0F + " segundos");
    }
    private void report(float delta){
        System.out.println(Colors.GREEN + "\nTotal" + Colors.RESET + " - Tempo de renderização: " + delta/1000000000.0F + " segundos");
    }

}
