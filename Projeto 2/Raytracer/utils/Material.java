package utils;

public class Material {
    public Colors color;
    public float diffuse;
    public float specularity;
    public float trasmission;
    public float traslucency;
    public float emission;

    public Material(Colors color){
        this.color = color;
    }
    public Material(int r, int g, int b){
        this(new Colors(r, g, b));
    }
    public Material(){
        this(new Colors(255));
    }
}
