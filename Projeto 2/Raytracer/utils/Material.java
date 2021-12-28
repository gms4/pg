package utils;
import java.awt.Color;

public class Material {
    public Color color;
    public double ambient;
    public double diffuse;
    public double specularity;
    public double q;
    public double reflexion;
    public double transmission;

    public Material(Color base, double ambient, double diffuse, double specularity, double q, double reflexion, double transmission){
        this.color = base;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specularity = specularity;
        this.q = q;
        this.reflexion = reflexion;
        this.transmission = transmission;
    }
    public Material(){
        this(new Color(255), 0, 0, 0, 0, 0, 0);
    }
}
