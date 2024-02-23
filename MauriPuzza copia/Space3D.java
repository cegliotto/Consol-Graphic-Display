

package toolbox.eseguibili;
import java.util.ArrayList;
import geometria.oggetti3d.Punto3D;
import geometria.oggetti3d.Linea3D;
import geometria.oggetti2d.Punto;
import geometria.oggetti2d.Linea;
import java.lang.Math;

public class Space3D
{

  
    //#region variabili
    Schermo schermo;
    PointManager manager;


    private float maxX, maxY, maxZ;
    public ArrayList<Punto3D> punti = new ArrayList<>();
    public ArrayList<Linea3D> linee = new ArrayList<>();
    
   
    //#endregion



    
    //#region costruttori
    public Space3D(Schermo schermo,PointManager manager , float maxX, float maxY, float maxZ)
    {
        this.schermo = schermo;
        this.manager = manager;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;


    }

    //#endregion
  
  


    //#region metodi

    



    // #region input Output
    public void addPoints(Punto3D punto)
    {
        this.punti.add(punto);
    }
 

    //#endregion

    
    // #region  Proiezioni

    //fa una proiezione ortogonale semplicemente schiacciando tutto sul piano della telecamera
    public void simpleOrtographicProjection ()
    {
        for(Punto3D temp : punti)
        {

            schermo.addPoints(new Punto( (int) temp.getX() , (int) temp.getY()) );
        }

        for (Linea3D temp : linee)
        {
            manager.line(temp.getPunto1(), temp.getPunto2());
        }

       punti.removeAll(punti);
    }
    
    //#endregion



    // #region input Geometria
    
    public void parallelepipedo(Punto3D punto1, Punto3D punto2, Punto3D punto3, Punto3D punto4, Punto3D punto5, Punto3D punto6, Punto3D punto7, Punto3D punto8) 
    {
        //punti
        this.punti.add(punto1);
        this.punti.add(punto2);
        this.punti.add(punto3);
        this.punti.add(punto4);
        this.punti.add(punto5);
        this.punti.add(punto6);
        this.punti.add(punto7);
        this.punti.add(punto8);

        this.linee.add( new Linea3D( punto1,punto2));
        this.linee.add( new Linea3D( punto2,punto3));
        this.linee.add( new Linea3D( punto3,punto4));
        this.linee.add( new Linea3D( punto4, punto1));

        this.linee.add( new Linea3D( punto5,punto6));
        this.linee.add( new Linea3D( punto6,punto7));
        this.linee.add( new Linea3D( punto7,punto8));
        this.linee.add( new Linea3D( punto8, punto5));        

        this.linee.add( new Linea3D( punto1,punto5));
        this.linee.add( new Linea3D( punto2,punto6));
        this.linee.add( new Linea3D( punto3,punto7));
        this.linee.add( new Linea3D( punto4, punto8));    

        


    }
    //#endregion


    //#region Trasformazioni

    public void RuotatuttoX(float angolo)
    {
        for (int i = 0; i < punti.size(); i++)
        {
            Punto3D punto = punti.get(i);
            punto.setY( (float) (punto.getY() * Math.cos(angolo) + punto.getZ() * Math.sin(angolo)) );
            punto.setZ( (float) (punto.getZ() * Math.cos(angolo) - punto.getY() * Math.sin(angolo)) );
            punti.set(i,punto);

        }

        for (int i = 0; i < linee.size(); i++)
        {
            Linea3D linea = linee.get(i);
            Punto3D punto1 = linee.get(i).getPunto1();
            Punto3D punto2 = linee.get(i).getPunto2();


            punto1.setY( (float) (punto1.getY() * Math.cos(angolo) + punto1.getZ() * Math.sin(angolo)) );
            punto1.setZ( (float) (punto1.getZ() * Math.cos(angolo) - punto1.getY() * Math.sin(angolo)) );

            punto2.setY( (float) (punto2.getY() * Math.cos(angolo) + punto2.getZ() * Math.sin(angolo)) );
            punto2.setZ( (float) (punto2.getZ() * Math.cos(angolo) - punto2.getY() * Math.sin(angolo)) );

            linee.set(i,linea);
        }
    }

    public void RuotatuttoY()
    {

    }

    public void RuotatuttoZ()
    {

    }

    //#endregion

    //#endregion

}
    