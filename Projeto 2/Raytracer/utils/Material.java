package utils;

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
    public Material(){
        this(new Color(255));
    }
}
