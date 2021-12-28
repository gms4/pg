package objects;

public class Light extends Object3D{
    public int r;
    public int g;
    public int b;

    public Light(float x, float y, float z, int r, int g, int b){
        super(x, y, z);
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
