package utils;
import java.awt.Color;

public class Material {
    public Color color;
    public float diffuse;
    public float specularity;
    public float trasmission;
    public float traslucency;
    public float emission;

    public Material(Color color){
        this.color = color;
    }
    public Material(int r, int g, int b){
        this(new Color(r, g, b));
    }
    public Material(){
        this(new Color(255));
    }
}
