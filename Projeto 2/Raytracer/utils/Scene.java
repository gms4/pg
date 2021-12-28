package utils;

import objects.*;

public class Scene {
    public Camera camera;
    public Object3D[] objects;
    public Object3D[] lights;

    public Scene(Camera camera, Object3D[] objects, Object3D[] lights){
        this.camera = camera;
        this.objects = objects;
        this.lights = lights;

        this.translate_origin_to_camera();
        this.base_to_camera_vectors();
    }

    public void translate_origin_to_camera(){
        //Alterando Objetos
        for(int i = 0; i < this.objects.length; i++){
            this.objects[i].center.x -= this.camera.origin.x; 
            this.objects[i].center.y -= this.camera.origin.y;
            this.objects[i].center.z -= this.camera.origin.z; 
        }
        //Alterando Luzes
        for(int i = 0; i < this.lights.length; i++){
            this.lights[i].center.x -= this.camera.origin.x; 
            this.lights[i].center.y -= this.camera.origin.y;
            this.lights[i].center.z -= this.camera.origin.z; 
        }
        this.camera.origin = new Point(0,0,0); 
    }

    public void base_to_camera_vectors(){
        Vector T = this.camera.target_vector;
        Vector U = this.camera.up_vector;
        Vector O = this.camera.orto_vector;

        //            U,    O,    T
        //          [ U.x,  O.x,  T.x ] transposta         [ T.z,  O.z,  U.z ]
        //  M   =   [ U.y,  O.y,  T.y ]               =    [ T.y,  O.y,  U.y ]
        //          [ U.z,  O.z,  T.z ]                    [ T.x,  O.x,  U.x ]

        //          { T.z*P.x + O.z*P.y + U.z*P.z = [P.x]nova_base
        //  M*P =   { T.y*P.x + O.y*P.y + U.y*P.z = [P.y]nova_base
        //          { T.x*P.x + O.x*P.y + U.x*P.z = [P.z]nova_base

        //Alterando Objetos
        for(int i = 0; i < this.objects.length; i++){
            Point P = new Point(
                this.objects[i].center.x,
                this.objects[i].center.y,
                this.objects[i].center.z
            );
            this.objects[i].center.x = (T.z*P.x) + (O.z*P.y) + (U.z*P.z);
            this.objects[i].center.y = (T.y*P.x) + (O.y*P.y) + (U.y*P.z);
            this.objects[i].center.z = (T.x*P.x) + (O.x*P.y) + (U.x*P.z);
        }
        //Alterando Luzes
        for(int i = 0; i < this.lights.length; i++){
            Point P = new Point(
                this.lights[i].center.x,
                this.lights[i].center.y,
                this.lights[i].center.z
            );
            this.lights[i].center.x = (T.z*P.x) + (O.z*P.y) + (U.z*P.z);
            this.lights[i].center.y = (T.y*P.x) + (O.y*P.y) + (U.y*P.z);
            this.lights[i].center.z = (T.x*P.x) + (O.x*P.y) + (U.x*P.z);
        }
    }

}
