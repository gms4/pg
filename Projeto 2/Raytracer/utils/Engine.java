package utils;

import java.io.File;
import javax.imageio.ImageIO;
import java.lang.Math;
import java.awt.Color;
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
        int totalFrames = 1;

        //Renderizando
        this.total_render_begin = System.nanoTime();
        for(int r = 0; r < totalFrames; r++){
            this.frame_render_begin = System.nanoTime();

            Color pixel_color;
            boolean filled;

            File image = new File("output/image_"+r+".png");
            BufferedImage buffer = new BufferedImage(this.resWide, this.resHigh, BufferedImage.TYPE_INT_RGB);

            for(int h = 0; h < this.resHigh; h++){
                for(int w = 0; w < this.resWide; w++){
                    filled = false;

                    if(h>=this.resHigh/2 && w>=this.resWide/2){
                        //render
                        Ray ray = scene.camera.castRay(w, h);
                        pixel_color = raytrace(ray, scene, null, 0);
                        buffer.setRGB(w, h, pixel_color.getRGB());
                    }else{
                        //render
                        Ray ray = scene.camera.castRay(w, h);
                        pixel_color = raytrace(ray, scene, null, 0);
                        buffer.setRGB(w, h, pixel_color.getRGB());
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

            // scene.objects[0].center.x += 5;
            // scene.objects[1].center.x -= 5;
            //scene.lights[0].center.x -= 10;
            scene.objects[1].center.x -= 10;
        }
        //Reportando o tempo de renderização total
        total_render_end = System.nanoTime();
        this.report(total_render_end - total_render_begin);

    }

    private Color raytrace(Ray ray, Scene scene, Object3D curr, int level){

        if(level == 4){
            return new Color(0,0,0);
        }

        //definindo cor padrão (caso não haja intersecções)
        Color final_color = new Color(0, 0, 0);
        //Ponto de intersecção
        Point inter_point;
        //Ultimo ponto de intersecção
        Point last_inter_point = new Point(0, 0, -1);

        //Percorremos todos os objetos
        for(int i = 0; i < scene.objects.length; i++){

            //Checa se exite intersecção entre o raio e o objeto
            if(scene.objects[i] != curr && scene.objects[i].intersects(ray)){

                //Acha essa intersecção
                inter_point = scene.objects[i].intersectionPoint(ray);

                if((inter_point.z >= 0) && ((inter_point.z < last_inter_point.z) || (last_inter_point.z <= 0))){

                    //aplicamos phong
                    int phong_col[] = phong((Light[])scene.lights, scene.objects[i], inter_point, ray.origin);

                    //acha a normal da esfera no ponto de intersecção
                    Vector normal = scene.objects[i].getNormalAt(inter_point);

                    //acha o vetor do raio refletido
                    //Vector perfect_reflection = normal.multipliedBy(2).multipliedBy(normal.dotProduct(ray.direction)).add(ray.direction);
                    Vector perfect_reflection = ray.direction.minus(ray.direction.proj(normal).multipliedBy(2));

                    //achamos o raio refletido
                    Ray reflexion_ray = new Ray(inter_point, perfect_reflection);

                    //aplicamos a recursão
                    Color reflexion_col = raytrace(reflexion_ray, scene, scene.objects[i], level+1);

                    //obtemos o kr
                    double kr = scene.objects[i].getMaterial().reflexion;

                    //criamos um array das componentes rgb da reflexao
                    int ref_col[] = new int[3];
                    int f_col[] = new int[3];

                    ref_col[0] = (int)(reflexion_col.getRed() * kr);
                    ref_col[1] = (int)(reflexion_col.getRed() * kr);
                    ref_col[2] = (int)(reflexion_col.getRed() * kr);
                    
                    //int transmission_col[] = scene.objects[i].material.transmission * raytrace(transmission_ray)
                    
                    //somamos phon + ambiente + reflexao
                    f_col[0] = phong_col[0] + ref_col[0];
                    f_col[1] = phong_col[1] + ref_col[1];
                    f_col[2] = phong_col[2] + ref_col[2];

                    if(f_col[0]>255){
                        f_col[0] = 255;
                    }else if(f_col[0]<0){
                        f_col[0] = 0;
                    }
            
                    if(f_col[1]>255){
                        f_col[1] = 255;
                    }else if(f_col[1]<0){
                        f_col[1] = 0;
                    }
            
                    if(f_col[2]>255){
                        f_col[2] = 255;
                    }else if(f_col[2]<0){
                        f_col[2] = 0;
                    }

                    //construimos a cor final
                    final_color = new Color(
                        f_col[0],
                        f_col[1],
                        f_col[2]
                    );

                    //armazena o valor dessa intersecção para comparar com as proximas
                    last_inter_point = inter_point;

                }

            }
            
        }

        return final_color;

        //phong + raytrace 
        // int r = phong(scene.lights, obj) + (obj.reflexion * raytrace(reflected_ray)) + (obj.transmission * raytrace(transmitted_ray));
    }

    private int[] phong(Light[] lights, Object3D object, Point inter_point, Point ray_origin){
        

        float diffuseIntensity = 0;
        float specularIntensity = 0;
        int final_array[] = new int[3];

        for(int i = 0; i < lights.length; i++){

            diffuseIntensity = this.diffusion(lights[i], object, inter_point);
            specularIntensity = this.specular(lights[i], object, inter_point, ray_origin);

            final_array[0] += Math.round( lights[i].r * (diffuseIntensity + specularIntensity) );
            final_array[1] += Math.round( lights[i].g * (diffuseIntensity + specularIntensity) );
            final_array[2] += Math.round( lights[i].b * (diffuseIntensity + specularIntensity) );  

        }

        //adicionamos luz ambiente

        final_array[0] = (int)(final_array[0] + (object.getMaterial().color.getRed()*object.getMaterial().ambient));
        final_array[1] = (int)(final_array[1] + (object.getMaterial().color.getGreen()*object.getMaterial().ambient));
        final_array[2] = (int)(final_array[2] + (object.getMaterial().color.getBlue()*object.getMaterial().ambient));

        if(final_array[0]>255){
            final_array[0] = 255;
        }else if(final_array[0]<0){
            final_array[0] = 0;
        }

        if(final_array[1]>255){
            final_array[1] = 255;
        }else if(final_array[1]<0){
            final_array[1] = 0;
        }

        if(final_array[2]>255){
            final_array[2] = 255;
        }else if(final_array[2]<0){
            final_array[2] = 0;
        }

        return final_array;

    }

    private float diffusion(Light light, Object3D object, Point inter_point){
        //acha a normal da esfera no ponto de intersecção
        Vector normal = object.getNormalAt(inter_point);
        //acha o vetor que aponta para a fonte de luz
        Vector point_to_light = light.center.minus(inter_point);
        point_to_light.normalize();
        //calcula o cosseno enrte essa normal e o vetor para a fonte de luz
        float cosTheta = normal.dotProduct(point_to_light);
        //calcula a intensidade de luz difusa
        return (float)(object.getMaterial().diffuse * cosTheta);
    }

    private float specular(Light light, Object3D object, Point inter_point, Point ray_origin){
        //acha o vetor ponto_de_inter-origem_do_raio
        Vector point_to_source = ray_origin.minus(inter_point);
        point_to_source.normalize();
        //acha a normal da esfera no ponto de intersecção
        Vector normal = object.getNormalAt(inter_point);
        //acha o vetor que aponta para a fonte de luz
        Vector point_to_light = light.center.minus(inter_point);
        point_to_light.normalize();
        //achamos o vetor que direciona o reflexo perfeito do ponto de luz: R = 2N(N.L) - L
        Vector perfect_reflection = normal.multipliedBy(2).multipliedBy(normal.dotProduct(point_to_light)).minus(point_to_light);
        perfect_reflection.normalize();
         //checa se o angulo enrte R e o raio enviado pela camera é menor que 90 (para evitar ressurgimento de reflexao)
        if(perfect_reflection.dotProduct(point_to_source) > 0){
            //Agora calculamos o cosseno entre a reflexao perfeita e o vetor point_to_source
            float cosAlpha = perfect_reflection.dotProduct(point_to_source);
            //calcula o cos(alpha^q)
            double cosaq = Math.pow(cosAlpha, object.getMaterial().q);
            //calcula a intensidade de luz difusa
            return (float)(object.getMaterial().specularity * cosaq);
        }else{
            return 0;
        }
    }

    private void report(int frame, int totalFrames, float delta){
        System.out.println(Colors.YELLOW + "Frame " + (frame+1) + "/" + totalFrames + Colors.RESET + " - tempo de renderização: " + delta/1000000000.0F + " segundos");
    }
    private void report(float delta){
        System.out.println(Colors.GREEN + "\nTotal" + Colors.RESET + " - Tempo de renderização: " + delta/1000000000.0F + " segundos");
    }

}
