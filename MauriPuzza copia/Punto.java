package geometria.oggetti2d;

public class Punto
{

    //una semplice classe punto con set get e tostring

    //variabili
    private float x;
    private float y;
    private char character = 35;// "#" in ascii (valore di default)


    //costruttori 
    public Punto(){}
    public Punto(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Punto(float x, float y, char c)
    {
        this.x = x;
        this.y = y;
        this.character = c;
    }



    //metodi
    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setChar(char c)
    {
        this.character = c;
    }


    public float getX()
    {
        return x; 
    }
    public float getY()
    {
        return y; 
    }

    public int getXint()
    {
        return (int) x; 
    }

    public int getYint()
    {
        return (int) y; 
    }


    

    public char getChar()
    {
        return character; 
    }

    public String toString()
    {
        return "(" + x + "," + y + ")" ;
    }



}
    