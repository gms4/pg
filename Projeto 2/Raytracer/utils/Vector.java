package utils;

public class Vector {
    public float x;
    public float y;
    public float z;
    public float magnitude;
  
    //construtores
    public Vector(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.magnitude = (float)Math.sqrt(
        this.x*this.x+
        this.y*this.y+
        this.z*this.z
      );
    }
    public Vector(Point start, Point end){
      this((end.x - start.x), (end.y - start.y), (end.z - start.z));
    }
  
    //implementando soma (entre dois vetores)
    public Vector add(Vector added) {
      return new Vector(x + added.x, y + added.y, z + added.z);
    }

    //implementando subtração (entre dois vetores)
    public Vector minus(Vector subbed) {
      return new Vector(x - subbed.x, y - subbed.y, z - subbed.z);
    }

    //implementando produto escalar (entre dois vetores)
    public float dotProduct(Vector multiplier){
      return (this.x * multiplier.x) + (this.y * multiplier.y) + (this.z * multiplier.z);
    }

    //implementando produto vetorial (entre dois vetores)
    public Vector crossProduct(Vector multiplier){
      float product_x = (this.y * multiplier.z) - (this.z * multiplier.y);
      float product_y = (this.z * multiplier.x) - (this.x * multiplier.z);
      float product_z = (this.x * multiplier.y) - (this.y * multiplier.x);
      return new Vector(product_x, product_y, product_z);
    }

    //multiplicação por escalar
    public Vector multipliedBy(float s){
      Vector v = new Vector(
        this.x * s,
        this.y * s,
        this.z * s
      );
      return v;
    }

    //obtendo projeção entre dois vetores
    public Vector proj(Vector base){
      float multiplier = this.dotProduct(base)/(base.magnitude*base.magnitude);
      return base.multipliedBy(multiplier);
    }

    //normaliza o vetor
    public void normalize(){
        float norm = (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
        this.x = this.x / norm;
        this.y = this.y / norm;
        this.z = this.z / norm;
        this.magnitude = 1; 
    }

    //tranformando em ponto
    public Point toPoint(){
      return new Point(
        this.x,
        this.y,
        this.z
      );
    }
  }