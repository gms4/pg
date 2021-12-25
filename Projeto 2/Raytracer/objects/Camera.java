package objects;

import utils.Point;
import utils.Ray;
import utils.Vector;

public class Camera{
    public Point origin;
    public Vector target_vector;
    public Vector up_vector;
    public Vector orto_vector;
    public double focal_distance;
    public double angle_degree;
    public double angle_rad;

    //comprimento da metade do plano de caoptura no espaço
    public double hx;
    public double hy;
    //quantidade de pixels (Resolução)
    public int pixelsWide;
    public int pixelsHigh;
    //tamanho (no espaço) de um único pixel
    public double pixelSizeX;
    public double pixelSizeY;
    
    //construtores
    public Camera(int x, int y, int z, double f, int resw, int resh, Vector target){
        //definindo a origem da camera
        this.origin = new Point(x, y, z);

        //definindo a distância focal
        this.focal_distance = f;

        //definindo o angulo da camera
        this.angle_degree = 60;
        //inferindo esse angulo em radianos
        this.angle_rad = (angle_degree*Math.PI)/180;

        //tamanho (no espaço) do plano de captura
        this.hx = 2*Math.tan(this.angle_rad/2)*this.focal_distance;
        this.hy = 2*Math.tan(this.angle_rad/2)*this.focal_distance;

        //resolução (em pixels) da captura
        this.pixelsWide = resw;
        this.pixelsHigh = resh;

        //inferindo tamanho de um pixel individual
        this.pixelSizeX = this.hx/this.pixelsWide;
        this.pixelSizeY = this.hy/this.pixelsHigh;

        //só o vetor alvo varia
        this.target_vector = target;
        this.up_vector = new Vector(0, 1, 0);
        this.orto_vector = this.target_vector.crossProduct(this.up_vector);
    }
    public Camera(int x, int y, int z, double f, int rw, int rh){
        this(x, y, z, f, rw, rh, new Vector(0, 0, 1));
    }
    public Camera(int x, int y, int z, double f){
        this(x, y, z, f, 640, 480, new Vector(0, 0, 1));
    }

    //cria um raio que parte da camera e passa pelo pixel desejado
    public Ray castRay(float px, float py){

        //definindo o ponto minimo no plano de captura
        Point point = new Point(
            (float)(this.origin.x - (this.hx) + (this.hx)),
            (float)(this.origin.y - (this.hy) + (this.hy)),
            (float)(this.origin.z + this.focal_distance)
        );

        //distancias x e y a partir do pixels 0, 0 do plano de captura
        float x_dist = (float)((this.pixelSizeX * (px + 1)) - (this.pixelSizeX/2));
        float y_dist = (float)((this.pixelSizeY * (py + 1)) - (this.pixelSizeY/2));

        //achando o ponto global desse pixel
        point.x  = point.x + x_dist;
        point.y  = point.y + y_dist;

        Vector direction = new Vector(point.x, point.y, (float)(this.origin.z+this.focal_distance));
        return new Ray(this.origin, direction);
    }
}