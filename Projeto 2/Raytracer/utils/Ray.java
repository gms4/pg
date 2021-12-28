package utils;
public class Ray {
    public Point origin;
    public Vector direction;

    public Ray(Point origin, Vector direction){
        this.origin = origin;
        this.direction = direction;
        this.direction.normalize();
    }

    //utilizamos para achar o ponto de um certo parametro dentro do raio de luz
    public Point at(float dist){
        return new Point(
            this.origin.x + (this.direction.x * dist),
            this.origin.y + (this.direction.y * dist),
            this.origin.z + (this.direction.z * dist)
        );
    }
}
