package utils;

public class Vector {
    public final float x;
    public final float y;
    public final float z;
    public float magnitude;
  
    //construtores
    public Vector(float x, float y, float z, float magnitude) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.magnitude = magnitude;
    }
    public Vector(float x, float y, float z){
      this(x, y, z, 1);
    }
    public Vector(Point start, Point end){
      this((end.x - start.x), (end.y - start.y), (end.z - start.z));
    }
  
    //implementando soma (entre dois vetores)
    public Vector add(Vector added) {
      return new Vector(x + added.x, y + added.y, z + added.z);
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
  }