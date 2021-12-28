package objects;

import javax.lang.model.util.ElementScanner14;

import utils.*;

public class Sphere extends Object3D{
    public float radius;
    public Material material;

    public Sphere(float x, float y, float z, float radius, Material mat){
        super(x, y, z);
        this.radius = radius; 
        this.material = mat;
    }

    public Material getMaterial(){
        return this.material;
    }

    public boolean intersects(Ray ray){
        //vetor entre centro da camera e centro da esfera
        Vector camera_to_sphere = new Vector(ray.origin, this.center);
        //Achamos a projeção entre o vetor Camera-Esfera e o ray
        Vector proj = camera_to_sphere.proj(ray.direction);
        //declaramos a distancia
        float distance = 0;
        if(ray.origin == new Point(0,0,0)){
            //obtemos a distancia entre essa projeção e o centro da esfera
            distance = proj.toPoint().distanceTo(this.center).magnitude;
        }else{
            Point p = ray.origin.addVector(proj);
            //obtemos a distancia entre essa projeção e o centro da esfera
            distance = p.distanceTo(this.center).magnitude;
        }
        //checamos se essa distancia é menor que o raio da esfera
        if(distance <= this.radius){
            return true;
        }else{
            return false;
        }
    }

    public Point intersectionPoint(Ray ray){
        
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
        //achamos as distancias até o ponto de intersecção para usar de parametro dentro do raio de luz
        float dist1 = Math.abs((float)( -b + Math.sqrt(delta) ) / 2*a);
        float dist2 = Math.abs((float)( -b - Math.sqrt(delta) ) / 2*a);

        //finalmente usamos a menor distancia como parametro para achar o ponto em si
        if(dist1 <= dist2){
            return ray.at(dist1);
        }else{
            return ray.at(dist2);
        }
    }

    public Vector getNormalAt(Point p){
        Vector normal = p.minus(this.center);
        normal.normalize();
        return normal;
    }
}
