package geometria.oggetti2d;


public class Linea
{

    //una semplice classe linea ancora non usata

    //variabili
    Punto punto1;
    Punto punto2;
 
    //costruttori 
    public Linea(){}
    public Linea(Punto punto1, Punto punto2)
    {
        this.punto1 = punto1;
        this.punto2 = punto2;
    }
    public Linea(float x1 ,float y1 ,float x2 ,float y2)
    {
        this.punto1.setX(x1);
        this.punto1.setY(y1);
        this.punto2.setX(x2);
        this.punto2.setY(y2);
    }


    //metodi
    public void setPunto1(Punto punto1)
    {
        this.punto1 = punto1;
    }

    public void setPunto2(Punto punto2)
    {
        this.punto2 = punto2;
    }


    public Punto getPunto1()
    {
        return this.punto1;
    }

    public Punto getPunto2()
    {
        return this.punto2;
    } 
    

    public String toString()
    {
        return punto1.toString() + "   " + punto2.toString();
    }



}
    