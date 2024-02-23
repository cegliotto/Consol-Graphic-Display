
import toolbox.eseguibili.Schermo;
import toolbox.eseguibili.PointManager;  
import toolbox.eseguibili.Space3D;

import geometria.oggetti3d.Punto3D;
import geometria.oggetti2d.Punto;


import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Random;

public class Main
{
    
    LocalTime tempo =  LocalTime.now();
    public int ora()
    {
        return tempo.getHour();
    }



    public static void main(String[] args) throws InterruptedException {
    {
        //#region Documentation
        // nella classe main per ora semplicemente si richiamano i metodi di Pointmanager 
        //per poi settare i punti da disegnare sullo schermo il ciclo while disegna fotogramma per fotogramma con drawframe
        // fill matrix si resetta la matrice per evitare buchi quando si cancellano i punti
        //#endregion


        // crea un oggetto schermo e  uno pointmanager 
        //Debug fai diventare singleton
        Schermo schermo = new Schermo(200,200); 
        PointManager manager = new PointManager(schermo);
        Space3D space3D = new Space3D(schermo, manager,  187,60,50);

        //setta la velocita di refreshrate
        //int refreshRateMs = 16 * 16;
        int refreshRateMs = 16*2 +8 ;
        

        //debug
        //un contatore che uso come tempo per muovere le cose e un segno che serve per la funzione ping pong
        int contatore = 0;
        int altezza = 0;

        int maxCols = 181;
        int maxRows = 53;


        while(true)
        {
            

            //schermo.fillMatrix();
            schermo.drawFrame();
            



            //Esperimenti cubo
             

            //  Punto3D punto1 = new Punto3D( 0 + 93, 20 + 30, 10);
            //  Punto3D punto2 = new Punto3D( 20 + 93, 20 + 30, 10);
            //  Punto3D punto3 = new Punto3D( 20 + 93, 0 + 30, 10);
            //  Punto3D punto4 = new Punto3D( 0 + 93, 0 + 30, 10);

            //  Punto3D punto5 = new Punto3D( 0 + 93, 20 + 30, 10);
            //  Punto3D punto6 = new Punto3D( 20 + 93, 20 + 30, 10);
            //  Punto3D punto7 = new Punto3D( 20 + 93, 0 + 30, 10);
            //  Punto3D punto8 = new Punto3D( 0 + 93, 0 + 30, 10);
            
            //  space3D.parallelepipedo(punto1,punto2,punto3,punto4,punto5,punto6,punto7,punto8);
            //  space3D.RuotatuttoX(0);
            //  space3D.simpleOrtographicProjection();

           
          
            


            
            //Esperimenti rettangolo

            //  Punto punto1 = new Punto( 0 + 93, 0 + 30);
            // Punto punto2 = new Punto( 0 + 93, 10 + 30);
            // Punto punto3 = new Punto( 20 + 93, 10 + 30);
            // Punto punto4 = new Punto( 20 + 93, 0 + 30);

            //  Punto centro = new Punto(93,30);
            //  schermo.addPoints(centro);
            // manager.rettangolo(punto1,punto2,punto3,punto4);

            // System.out.println(schermo.punti.get(0).getX());


            
            //Orologio
            

            manager.orologio(LocalTime.now().toString().substring(0,6),contatore * 2 , altezza);


            // info orari
            //  System.out.println(LocalTime.now().toString().substring(0,5)); 
            //  System.out.println(altezza); 
            //  System.out.println((contatore*2) % (schermo.getMaxCols()+1)); 

            // Main mainInfoHolder = new Main();
            // Integer ciao = mainInfoHolder.ora();
            // System.out.println(ciao);   
            
           



            //altezza = ( ((contatore*2) % (schermo.getMaxCols()+1)) == 0) ? ((altezza+1) % schermo.getMaxRow()) : altezza % schermo.getMaxRow();
            altezza = ( (((contatore+2)*2) % (schermo.getMaxCols()+1)) <= 1) ? ( new Random().nextInt(schermo.getMaxRow()-5) ) : altezza % schermo.getMaxRow();
            //aggiunge il tempo 
            contatore++;

            //aggiunge una sleep per disegnare il fotogramma successivo
            Thread.sleep(refreshRateMs);
        }
    }
    }

    


    

}


