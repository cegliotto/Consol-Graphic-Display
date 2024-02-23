package geometria.oggetti3d;

import geometria.oggetti2d.Linea;
import geometria.oggetti2d.Punto;


public class Linea3D extends Linea
{

    //una semplice classe linea ancora non usata

    //variabili
   
 
    //costruttori 
    public Linea3D(){}
    public Linea3D(Punto3D punto1, Punto3D punto2)
    {
        this.setPunto1(punto1);
        this.setPunto2(punto2);
    }
    public Linea3D(float x1 ,float y1 ,float z1, float x2 ,float y2, float z2)
    {
        
        super.setPunto1(new Punto3D(x1, y1, z1));
        super.setPunto2(new Punto3D(x2, y2, z2));

        

        
    }


    //metodi
    
    @Override
    public void setPunto1(Punto punto1)
    {
       if (punto1 instanceof Punto3D)
       {
            super.setPunto1(punto1);
       }
    }

    public void setPunto1(Punto3D punto1)
    {
        super.setPunto1(punto1);
    }

    @Override
    public void setPunto2(Punto punto2)
    {
       if (punto2 instanceof Punto3D)
       {
            super.setPunto1(punto2);
       }
    }
   
    public void setPunto2(Punto3D punto2)
    {
        super.setPunto2(punto2);
    }

    @Override
    public Punto3D getPunto1()
    {
        return (Punto3D) super.getPunto1();
    }


    @Override
    public Punto3D getPunto2()
    {
        return ((Punto3D) super.getPunto2());
    } 
    

    @Override
    public String toString()
    {
        return this.getPunto1().toString() + "   " + this.getPunto2().toString();
    }



}
    