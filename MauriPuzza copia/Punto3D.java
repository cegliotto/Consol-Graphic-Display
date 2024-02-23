package geometria.oggetti3d;

import geometria.oggetti2d.Punto;



public class Punto3D extends Punto
{

    //una semplice classe punto 3d estensione di punto utile per dopo 
    
    //variabili
    private float z;

    //costruttori 
    public Punto3D(){}
    public Punto3D(float  x, float y, float z)
    {
        super(x,y);
        //this.setX(x);
        //this.setY(y);
        this.setZ(z);

    }
    
    //metodi
    public void setZ(float z)
    {
        this.z = z;
    }

    public float getZ()
    {
        return this.z;
    }   

    public String toString()
    {
        return "(" + this.getX() + "," + this.getY() + "," + z + ")";
    } 
}
    