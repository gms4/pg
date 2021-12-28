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
    public Camera(int x, int y, int z, double f, int resw, int resh, int hx, Vector target, Vector up_vector){
        //definindo a origem da camera
        this.origin = new Point(x, y, z);

        //definindo a distância focal
        this.focal_distance = f;

        //tamanho (no espaço) do plano de captura
        this.hx = hx;
        this.hy = (hx*resh)/resw;
        //2*Math.tan(this.angle_rad/2)*this.focal_distance

        //resolução (em pixels) da captura
        this.pixelsWide = resw;
        this.pixelsHigh = resh;

        //inferindo tamanho de um pixel individual
        this.pixelSizeX = 2*this.hx/this.pixelsWide;
        this.pixelSizeY = 2*this.hy/this.pixelsHigh;

        //normaliza os vetores da camera
        double target_norma = Math.sqrt((target.x*target.x) + (target.y*target.y) + (target.z*target.z));
        this.target_vector = new Vector(
            (float)(target.x / target_norma),
            (float)(target.y / target_norma),
            (float)(target.z / target_norma)
        );
        double up_norma = Math.sqrt((up_vector.x*up_vector.x) + (up_vector.y*up_vector.y) + (up_vector.z*up_vector.z));
        this.up_vector = new Vector(
            (float)(up_vector.x / up_norma),
            (float)(up_vector.y / up_norma),
            (float)(up_vector.z / up_norma)
        );
        this.orto_vector = this.target_vector.crossProduct(this.up_vector);
        double orto_norma = Math.sqrt((this.orto_vector.x*this.orto_vector.x) + (this.orto_vector.y*this.orto_vector.y) + (this.orto_vector.z*this.orto_vector.z));
        this.orto_vector = new Vector(
            (float)(this.orto_vector.x/orto_norma),
            (float)(this.orto_vector.y/orto_norma),
            (float)(this.orto_vector.z/orto_norma)
        );
    }
    public Camera(int x, int y, int z, double f, int resw, int resh, int hx, Point target, Vector up_vector){
        this(x, y, z, f, resw, resh, hx, new Vector(target.x - x, target.y - y, target.z - z), up_vector);
    }

    //cria um raio que parte da camera e passa pelo pixel desejado
    public Ray castRay(float px, float py){

        float planeCoordX = this.map(px, 0, this.pixelsWide-1, -this.hx, this.hx, 6);
        float planeCoordY = this.map(py, 0, this.pixelsHigh-1, -this.hy, this.hy, 6);
        float planeCoordZ = (float)this.focal_distance;

        Vector direction = new Vector(this.origin, new Point(planeCoordX, planeCoordY, planeCoordZ));
        return new Ray(this.origin, direction);
    }

    private float map(double sourceNumber, double fromA, double fromB, double toA, double toB, int decimalPrecision ) {
        double deltaA = fromB - fromA;
        double deltaB = toB - toA;
        double scale  = deltaB / deltaA;
        double negA   = -1 * fromA;
        double offset = (negA * scale) + toA;
        double finalNumber = (sourceNumber * scale) + offset;
        int calcScale = (int) Math.pow(10, decimalPrecision);
        return Math.round(finalNumber * calcScale) / calcScale;
    }
}