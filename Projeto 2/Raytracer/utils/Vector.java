package utils;

public class Vector {
    public final float x;
    public final float y;
    public final float z;
    public float magnitude;
  
    public Vector(float x, float y, float z, float magnitude) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.magnitude = magnitude;
    }
    public Vector(float x, float y, float z){
      this(x, y, z, 1);
    }
  
    public Vector add(Vector added) {
      return new Vector(x + added.x, y + added.y, z + added.z);
    }

    public float dotProduct(Vector multiplier){
      return (this.x * multiplier.x) + (this.y * multiplier.y) + (this.z * multiplier.z);
    }
  }