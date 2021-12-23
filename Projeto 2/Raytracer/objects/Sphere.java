package objects;

import utils.*;

public class Sphere extends Object3D{
    public float radius;
    public Material material;

    public Sphere(float x, float y, float z, float radius){
        super(z, y, z);
        this.radius = radius; 
        this.material = new Material(255, 0, 0);
    }

    public boolean intersects(Ray ray){
        
        //equação da esfera:
        // ||x-c||^2 = r^2
        
        //equação do raio:
        // x = s + td
        
        //intersecção de ambas:
        // ||s + td - c||^2 = r^2

        //subtração de dois pontos = vetor
        // v = s - c
        Vector sphere_to_ray = ray.origin.distanceTo(this.center); //é um numero ou um vetor?
        
        //substituindo:
        // ||v + td||^2 = r^2

        //expandida:
        // v^2 + (t^2 * d^2) + 2*v . t*d = r^2
        
        //d em evidencia:
        // (d^2)*(t^2) + (2*v . d)*t + (v^2 - r^2)      

        //resolvendo equação de segundo grau:
        // a = d^2
        float a = ray.direction.magnitude;
        // b = (2*v . d) | v = sphere_to_ray, d = ray.direction
        float b = 2 * ray.direction.dotProduct(sphere_to_ray);
        // c = (v^2 - r^2) | v = sphere_to_ray, r = this.radius
        float c = sphere_to_ray.dotProduct(sphere_to_ray) - (this.radius * this.radius);
        //t = ( -b +- sqrt(b^2 - 4ac) ) / 2
        //delta = b^2 - 4ac
        float delta = b*b - 4*a*c;
        
        //intersecta dois pontos da esfera (existe refração)
        if (delta > 0){
            //deve-se calcular a cor?
            return true;
        }
        //tangencia a esfera (sem refração)
        else if (delta == 0){
            //deve-se calcular a cor?
            return false;
        }
        //não toca na esfera
        else if (delta < 0){
            return false;
        } 
        return false;
    }
}
