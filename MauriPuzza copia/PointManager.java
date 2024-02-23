package toolbox.eseguibili;
import java.sql.Time;
import geometria.oggetti2d.Punto;
public class PointManager
{

    // una classe poitmanager che gestisce le funzioni da passare a schermo che le disegnera su terminale 
    //utile per fare le proiezioni dei punti 3d del cubo ma anche per mandare funzioni fighe a schermo
    //ricordati di fare le dovute considerazioni per l'unita di misura 


    //#region Variabili

    Schermo schermo;
    //public ArrayList<Punto> punti = new ArrayList<Punto>();

    //endregion




    //#region Costruttori
    public PointManager(Schermo schermo)
    {
        //una soluzione poco elegante per passare lo schermo
        //importante per accedere alla lista di punti 
        //Debug crea delle sottoliste per gestire i singoli "oggetti" in scena 
        this.schermo = schermo;
    }
    //endregion




    //#region Metodi

    //region funzioni varie
    public void sineWave( int pointNumber, float ampiezza, float frequenza, int tempo , int altezza, int risoluzione )
    {
        //una semplice sinusoide con tutti i diversi moltiplicatori del caso
        for (float i = 0 ; 0 < pointNumber - i ; ++i)
        {
            //schermo.addPoints(new Punto(i, (int) ((ampiezza * Math.sin( frequenza * i/100 + tempo))  + altezza) * 100  ) );
            schermo.addPoints(  new Punto( (int) i ,   (int) (Math.sin(i/risoluzione + tempo) * risoluzione  )  + altezza   )  );
        }
    }


    public void verticalSine( int pointNumber, float ampiezza, float frequenza, int tempo , int altezza )
    {
        //una semplice sinusoide verticale con tutti i diversi moltiplicatori del caso
        for (int i = 0 ; i < pointNumber; ++i)
        {
            schermo.addPoints(new Punto( (int) (ampiezza * Math.sin( frequenza * i + tempo)) + altezza , i));
        }
    }

    public void dna(int contatore, int spazio)
    {
        //ho trovato una carina combinazione di seni che ricordano la il dna 
        verticalSine(120,10,0.2f,contatore, spazio);
        verticalSine(120,10,0.2f,contatore + (int) (5 * Math.PI), spazio);
      
       
    }

    

    public void printString(Punto puntoIniziale, String stringa)
    {

        for(int i = 0; i < stringa.length(); ++i)
        {
            schermo.addPoints( new Punto(puntoIniziale.getX() +i , puntoIniziale.getY() , stringa.charAt(i) ));
        }

    }
    //#endregion

    //region Geometria Varia

    public void ruota(double angolo)
    {

        //non funziona 
        Punto puntoRuotato;
        int x;
        int y;
        for (int i = 0; i < schermo.punti.size() ; ++i)
        {
            puntoRuotato = schermo.punti.get(i);
            x = puntoRuotato.getXint();
            y = puntoRuotato.getYint();

            puntoRuotato.setX( (int) (Math.cos(angolo) * x - Math.sin(angolo) * y));
            puntoRuotato.setY( (int) (Math.sin(angolo) * x + Math.cos(angolo) * y) );

            
            //schermo.punti.get(i).setX( (int) (  Math.cos( angolo)) * schermo.punti.get(i).getX() - (int) ( Math.sin( angolo)) * schermo.punti.get(i).getY() );
            //schermo.punti.get(i).setY(  (int) (  Math.sin( angolo)) * schermo.punti.get(i).getX() +(int)  ( Math.cos( angolo)) * schermo.punti.get(i).getY());
            
            schermo.punti.set(i , puntoRuotato);
            
        }
    }

    //line with points
    public void line(Punto punto1, Punto punto2, char car)
    {
        punto1.setChar(car);
        punto2.setChar(car);

        float deltaX = punto2.getX() - punto1.getX();
        float deltaY = punto2.getY() - punto1.getY();

        Punto puntoIniziale;
        if(punto1.getX() < punto2.getX() )
        {
            puntoIniziale = punto1;
        }else
        {
            puntoIniziale = punto2;
        }


        float m = ( (deltaY))/ (deltaX) ;

        

        for ( int i = 0; i < Math.abs(deltaX); ++i)
        {
            for (int j = 0; j <= Math.abs(m); ++j) //ripete l'aggiunta del punto in verticale per ogni valore di m 
            {
                schermo.addPoints(  new Punto( ( puntoIniziale.getX() + i ),  ( puntoIniziale.getY() + m * i   + (j * (Math.signum(m)))  ), car ) );

            }
        }

        if (deltaX == 0)
        {
            if(punto1.getY() < punto2.getY() )
        {
            puntoIniziale = punto1;
        }else
        {
            puntoIniziale = punto2;
        }
           
            for ( int i = 0; i < Math.abs(deltaY); ++i)
            {
               
                schermo.addPoints(  new Punto( ( puntoIniziale.getX() ), puntoIniziale.getY() + i     , car ) );

            }
        }

    
        schermo.addPoints( punto1);
        schermo.addPoints( punto2);
    }
    
    public void line(Punto punto1, Punto punto2)
    {
        line( punto1, punto2, (char) 35);
    }

    //line with cordinates
    public void line(float x1,float y1,float x2,float y2)
    {
        line(new Punto(x1,y1),new Punto(x2,y2));
    }


    public void rettangolo(Punto punto1, Punto punto2, Punto punto3, Punto punto4)
    {
        this.line(punto1,punto2);
        this.line(punto2,punto3);
        this.line(punto3,punto4);
        this.line(punto4,punto1);
    }

    public void rettangolo(Punto centro, int base, int altezza)
    {
        Punto punto1 = new Punto(0,0);
        Punto punto2 = new Punto(0,0);

        punto1.setX( centro.getX() - + base/2);
        punto1.setY( centro.getY() + altezza/2);
        punto2.setX( centro.getX() +1+ base/2);
        punto2.setY( centro.getY() + altezza/2);
        this.line(punto1,punto2);   

        punto1.setX( centro.getX() + base/2);
        punto1.setY( centro.getY() + altezza/2);
        punto2.setX( centro.getX() + base/2);
        punto2.setY( centro.getY() - altezza/2);
        this.line(punto1,punto2);
        
        punto1.setX(centro.getX() + base/2);
        punto1.setY( centro.getY() - altezza/2);
        punto2.setX( centro.getX() - base/2);
        punto2.setY( centro.getY() - altezza/2);
        this.line(punto1,punto2);

        punto1.setX( centro.getX() - base/2);
        punto1.setY( centro.getY() - altezza/2);
        punto2.setX( centro.getX() - base/2);
        punto2.setY( centro.getY() + altezza/2);
        this.line(punto1,punto2);
    }

    //#endregion


    //#region Numeri

    
    public void uno(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        punto1 = new Punto(x1+2,y1 +12);
        punto2 = new Punto(x2+2,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+3,y1 +12);
        punto2 = new Punto(x2+3,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+4,y1 +12);
        punto2 = new Punto(x2+4,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+5,y1 +12);
        punto2 = new Punto(x2+5,y2-12);
        line(punto1,punto2);

        
        //base
        punto1 = new Punto(x1+9,y1 -11);
        punto2 = new Punto(x2-3,y2-11);
        line(punto1,punto2);

        punto1 = new Punto(x1+9,y1 -12);
        punto2 = new Punto(x2-3,y2 -12);
        line(punto1,punto2);

        //
        punto1 = new Punto(x1-10,y1 +5);
        punto2 = new Punto(x2+2,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+1,y1 +5);
        punto2 = new Punto(x2+2+1,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+2,y1 +5);
        punto2 = new Punto(x2+2+2,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+3,y1 +5);
        punto2 = new Punto(x2+2+3,y2+12);
        line(punto1,punto2);




    }

    public void due(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        //base
        punto1 = new Punto(x1 +9,y1 -12);
        punto2 = new Punto(x2 -10,y2 -12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +10,y1 -11);
        punto2 = new Punto(x2 -10,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 -10);
        punto2 = new Punto(x2 -10,y2 -10);
        line(punto1,punto2);

        // curva sopra
        //sinistra -2
        punto1 = new Punto(x1 -7,y1 +5 -2);
        punto2 = new Punto(x2 -7,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 +5-2);
        punto2 = new Punto(x2 -8,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 +5-2);
        punto2 = new Punto(x2 -9,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -10,y1 +5-2);
        punto2 = new Punto(x2 -10,y2 +10-2);
        line(punto1,punto2);

        //sopra
        punto1 = new Punto(x1 -8,y1 +9-2);
        punto2 = new Punto(x2 -5,y2 +9-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 +5,y1 +9-2);
        punto2 = new Punto(x2 +8,y2 +9-2);
        line(punto1,punto2);
        

        punto1 = new Punto(x1 -8,y1 +10-2);
        punto2 = new Punto(x2 -3,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 +3,y1 +10-2);
        punto2 = new Punto(x2 +8,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 +11-2);
        punto2 = new Punto(x2 +9,y2 +11-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 +12-2);
        punto2 = new Punto(x2 +8,y2 +12-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 +11);
        punto2 = new Punto(x2 +7,y2 +11);
        line(punto1,punto2);

        punto1 = new Punto(x1 -3,y1 +12);
        punto2 = new Punto(x2 +3,y2 +12);
        line(punto1,punto2);
        

        //destra
        punto1 = new Punto(x1 +7,y1 +2-2);
        punto2 = new Punto(x2 +7,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 +2-2);
        punto2 = new Punto(x2 +8,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 +2-2);
        punto2 = new Punto(x2 +9,y2 +10-2);
        line(punto1,punto2);

        punto1 = new Punto(x1 +10,y1 +2-2);
        punto2 = new Punto(x2 +10,y2 +10-2);
        line(punto1,punto2);

        //diagonale
        punto1 = new Punto(x1 -8,y1 -10);
        punto2 = new Punto(x2 +10,y2 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 -10);
        punto2 = new Punto(x2 +9,y2 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -10,y1 -10);
        punto2 = new Punto(x2 +8,y2 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -10,y1 -9);
        punto2 = new Punto(x2 +8,y2 +2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -9);
        punto2 = new Punto(x2 +10,y2 );
        line(punto1,punto2);

       

        



        

       

        


    }

     public void tre(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        

        punto1 = new Punto(x1+10,y1 +10);
        punto2 = new Punto(x2+10,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1+9,y1 +11);
        punto2 = new Punto(x2+9,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 +11);
        punto2 = new Punto(x2+8,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1+7,y1 +11);
        punto2 = new Punto(x2+7,y2 -11);
        line(punto1,punto2);


        //riga 1
        punto1 = new Punto(x1+8,y1 +12);
        punto2 = new Punto(x1-8,y1 +12);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 +11);
        punto2 = new Punto(x1-8,y1 +11);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 +10);
        punto2 = new Punto(x1-8,y1 +10);
        line(punto1,punto2);

        

        //riga2
        punto1 = new Punto(x1+8,y1 -1);
        punto2 = new Punto(x1-8,y1 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 );
        punto2 = new Punto(x1-8,y1 );
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 +1);
        punto2 = new Punto(x1-8,y1 +1);
        line(punto1,punto2);

        //riga3
        punto1 = new Punto(x1+8,y1 -12);
        punto2 = new Punto(x1-8,y1 -12);
        line(punto1,punto2);
        
        punto1 = new Punto(x1+8,y1 -11);
        punto2 = new Punto(x1-8,y1 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 -10);
        punto2 = new Punto(x1-8,y1 -10);
        line(punto1,punto2);

        

        


    }

     public void quattro(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        punto1 = new Punto(x1+2,y1 +12);
        punto2 = new Punto(x2+2,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+3,y1 +12);
        punto2 = new Punto(x2+3,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+4,y1 +12);
        punto2 = new Punto(x2+4,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+5,y1 +12);
        punto2 = new Punto(x2+5,y2-12);
        line(punto1,punto2);

        
        //base
        punto1 = new Punto(x1+9,y1 -11);
        punto2 = new Punto(x2-3,y2-11);
        line(punto1,punto2);

        punto1 = new Punto(x1+9,y1 -12);
        punto2 = new Punto(x2-3,y2 -12);
        line(punto1,punto2);

        //diagonale
        punto1 = new Punto(x1-10,y1 +3);
        punto2 = new Punto(x2+2,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+1,y1 +3);
        punto2 = new Punto(x2+2+1,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+2,y1 +3);
        punto2 = new Punto(x2+2+2,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-10+3,y1 +3);
        punto2 = new Punto(x2+2+3,y2+12);
        line(punto1,punto2);


        //
        punto1 = new Punto(x1-10,y1 +2);
        punto2 = new Punto(x2+2+3,y2+2);
        line(punto1,punto2);

        punto1 = new Punto(x1-10,y1 +1);
        punto2 = new Punto(x2+2+3,y2+1);
        line(punto1,punto2);





    }

    public void cinque(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1; //max x 10, max y 12

        x1 = centro.getX();
        y1 = centro.getY();
        

        punto1 = new Punto(x1 +9, y1 +12);
        punto2 = new Punto(x1 -10, y1 +12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -10, y1 +11);
        punto2 = new Punto(x1 +10, y1 +11);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9, y1 +10);
        punto2 = new Punto(x1 -10, y1 +10);
        line(punto1,punto2);

        //
        punto1 = new Punto(x1 -10, y1 +9);
        punto2 = new Punto(x1 -10, y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9, y1 +9);
        punto2 = new Punto(x1 -9, y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8, y1 +9);
        punto2 = new Punto(x1 -8, y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7, y1 +9);
        punto2 = new Punto(x1 -7, y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6, y1 +9);
        punto2 = new Punto(x1 -6, y1 +1);
        line(punto1,punto2);

        //curva
        punto1 = new Punto(x1 -10, y1 +1 );
        punto2 = new Punto(x1 -1, y1 +1 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -10, y1 );
        punto2 = new Punto(x1 +1, y1 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -8, y1 -1);
        punto2 = new Punto(x1 +4, y1 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -4, y1 -2);
        punto2 = new Punto(x1 +5, y1 -2);
        line(punto1,punto2);

        punto1 = new Punto(x1 + 0, y1 -3);
        punto2 = new Punto(x1 +7, y1 -3);
        line(punto1,punto2);

        punto1 = new Punto(x1+1, y1 -4);
        punto2 = new Punto(x1 +9,  y1 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 +3, y1 -5);
        punto2 = new Punto(x1 +10,  y1 -5);
        line(punto1,punto2);



        //punto medio arco
        punto1 = new Punto(x1 +10, y1 -6);
        punto2 = new Punto(x1 +3, y1 -6);
        line(punto1,punto2);


        //sotto
        // punto1 = new Punto(x1 +3, y1 -7);
        // punto2 = new Punto(x1 +10,  y1 -7);
        // line(punto1,punto2);

        punto1 = new Punto(x1+1, y1 -7);
        punto2 = new Punto(x1 +9,  y1 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1 + 0, y1 -8);
        punto2 = new Punto(x1 +7, y1 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1 -4, y1 -9);
        punto2 = new Punto(x1 +5, y1 -9);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8, y1 -10);
        punto2 = new Punto(x1 +4, y1 -10);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 -10, y1 -11 );
        punto2 = new Punto(x1 +1, y1 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1 -10, y1 -12 );
        punto2 = new Punto(x1 -1, y1 -12);
        line(punto1,punto2);

        

        

       

        

        

        

        







    }

    public void sei(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        
        punto1 = new Punto(x1-2,y1 +12);
        punto2 = new Punto(x2+9,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-2,y1 +11);
        punto2 = new Punto(x2+10,y2+11);
        line(punto1,punto2);

        punto1 = new Punto(x1-2,y1 +10);
        punto2 = new Punto(x2+9,y2+10);
        line(punto1,punto2);

        punto1 = new Punto(x1-3,y1 );
        punto2 = new Punto(x2-4,y2);
        line(punto1,punto2);

        //linea verticale
        punto1 = new Punto(x1-10,y1 +6);
        punto2 = new Punto(x1-10,y1 -5);
        line(punto1,punto2);

        punto1 = new Punto(x1-9,y1 +7);
        punto2 = new Punto(x1-9,y1 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1-8,y1 +8);
        punto2 = new Punto(x1-8,y1 -3);
        line(punto1,punto2);

        punto1 = new Punto(x1-7,y1 +9);
        punto2 = new Punto(x1-7,y1 -2);
        line(punto1,punto2);

        punto1 = new Punto(x1-6,y1 +10);
        punto2 = new Punto(x1-6,y1 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1-5,y1 +11);
        punto2 = new Punto(x1-5,y1 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1-4,y1 +12);
        punto2 = new Punto(x1-4,y1 +7);
        line(punto1,punto2);

        punto1 = new Punto(x1-3,y1 +12);
        punto2 = new Punto(x1-3,y1 +8);
        line(punto1,punto2);

        punto1 = new Punto(x1-2,y1 +12);
        punto2 = new Punto(x1-2,y1 +9);
        line(punto1,punto2);

        
       


        //cerchio
        //sopra
        punto1 = new Punto(x1 -2,y1 );
        punto2 = new Punto(x2 +2,y2 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -4,y1-1 );
        punto2 = new Punto(x2 +4,y2 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -2);
        punto2 = new Punto(x2 +5,y2 -2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -3);
        punto2 = new Punto(x2 -3,y2 -3);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -3);
        punto2 = new Punto(x2 +5,y2 -3);
        line(punto1,punto2);
        

        

        //lato sx
        punto1 = new Punto(x1 -10,y1 -5 );
        punto2 = new Punto(x2 -10,y2 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 -4 );
        punto2 = new Punto(x2 -9,y2 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -3 );
        punto2 = new Punto(x2 -8,y2 -5);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -7 );
        punto2 = new Punto(x2 -8,y2 -9);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -2 );
        punto2 = new Punto(x2 -7,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -8 );
        punto2 = new Punto(x2 -7,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -2 );
        punto2 = new Punto(x2 -6,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -8 );
        punto2 = new Punto(x2 -6,y2 -10);
        line(punto1,punto2);

       

        
        //lato dx
        punto1 = new Punto(x1 +10,y1 -5 );
        punto2 = new Punto(x2 +10,y2 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 -4 );
        punto2 = new Punto(x2 +9,y2 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -3 );
        punto2 = new Punto(x2 +8,y2 -5);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -7 );
        punto2 = new Punto(x2 +8,y2 -9);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -2 );
        punto2 = new Punto(x2 +7,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -8 );
        punto2 = new Punto(x2 +7,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -2 );
        punto2 = new Punto(x2 +6,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -8 );
        punto2 = new Punto(x2 +6,y2 -10);
        line(punto1,punto2);

        //sotto
        punto1 = new Punto(x1 -2,y1 -12);
        punto2 = new Punto(x2 +2,y2 -12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -4,y1-11 );
        punto2 = new Punto(x2 +4,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -10);
        punto2 = new Punto(x2 +5,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -9);
        punto2 = new Punto(x2 -3,y2 -9);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -9);
        punto2 = new Punto(x2 +5,y2 -9);
        line(punto1,punto2);



        
    }

     public void sette(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        

        punto1 = new Punto(x1-2,y1 -12);
        punto2 = new Punto(x2+10,y2+10);
        line(punto1,punto2);

        punto1 = new Punto(x1-3,y1 -12);
        punto2 = new Punto(x2+9,y2+10);
        line(punto1,punto2);

        punto1 = new Punto(x1-4,y1 -12);
        punto2 = new Punto(x2+8,y2+10);
        line(punto1,punto2);

        //
        punto1 = new Punto(x1-8,y1 +10);
        punto2 = new Punto(x2+10,y2+10);
        line(punto1,punto2);

        punto1 = new Punto(x1-8,y1 +11);
        punto2 = new Punto(x2+10,y2+11);
        line(punto1,punto2);

        


    }

    public void otto(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        

        //#region cerchio sopra
        punto1 = new Punto(x1 -2,y1+12 );
        punto2 = new Punto(x2 +2,y2+12 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -4,y1-1 +12);
        punto2 = new Punto(x2 +4,y2 -1+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -2+12);
        punto2 = new Punto(x2 +5,y2 -2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -3+12);
        punto2 = new Punto(x2 -3,y2 -3+12);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -3+12);
        punto2 = new Punto(x2 +5,y2 -3+12);
        line(punto1,punto2);
        

        

        //lato sx
        punto1 = new Punto(x1 -10,y1 -5+12 );
        punto2 = new Punto(x2 -10,y2 -7+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 -4+12 );
        punto2 = new Punto(x2 -9,y2 -8+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -3 +12);
        punto2 = new Punto(x2 -8,y2 -5+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -7+12 );
        punto2 = new Punto(x2 -8,y2 -9+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -2 +12);
        punto2 = new Punto(x2 -7,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -8 +12);
        punto2 = new Punto(x2 -7,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -2+12 );
        punto2 = new Punto(x2 -6,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -8+12 );
        punto2 = new Punto(x2 -6,y2 -10+12);
        line(punto1,punto2);

       

        
        //lato dx
        punto1 = new Punto(x1 +10,y1 -5+12);
        punto2 = new Punto(x2 +10,y2 -7+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 -4+12 );
        punto2 = new Punto(x2 +9,y2 -8+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -3+12 );
        punto2 = new Punto(x2 +8,y2 -5+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -7+12 );
        punto2 = new Punto(x2 +8,y2 -9+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -2 +12);
        punto2 = new Punto(x2 +7,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -8 +12);
        punto2 = new Punto(x2 +7,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -2 +12);
        punto2 = new Punto(x2 +6,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -8 +12);
        punto2 = new Punto(x2 +6,y2 -10+12);
        line(punto1,punto2);

        //sotto

        punto1 = new Punto(x1 -5,y1-11 +12);
        punto2 = new Punto(x2 +5,y2 -11+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -10+12);
        punto2 = new Punto(x2 +5,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -9+12);
        punto2 = new Punto(x2 -3,y2 -9+12);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -9+12);
        punto2 = new Punto(x2 +5,y2 -9+12);
        line(punto1,punto2);
        //endregion


        //#region cerchio sotto
        punto1 = new Punto(x1 -4,y1 );
        punto2 = new Punto(x2 +4,y2 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1-1 );
        punto2 = new Punto(x2 +5,y2 -1);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -2);
        punto2 = new Punto(x2 +5,y2 -2);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -3);
        punto2 = new Punto(x2 -3,y2 -3);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -3);
        punto2 = new Punto(x2 +5,y2 -3);
        line(punto1,punto2);
        

        

        //lato sx
        punto1 = new Punto(x1 -10,y1 -5 );
        punto2 = new Punto(x2 -10,y2 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 -4 );
        punto2 = new Punto(x2 -9,y2 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -3 );
        punto2 = new Punto(x2 -8,y2 -5);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -7 );
        punto2 = new Punto(x2 -8,y2 -9);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -2 );
        punto2 = new Punto(x2 -7,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -8 );
        punto2 = new Punto(x2 -7,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -2 );
        punto2 = new Punto(x2 -6,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -8 );
        punto2 = new Punto(x2 -6,y2 -10);
        line(punto1,punto2);

       

        
        //lato dx
        punto1 = new Punto(x1 +10,y1 -5 );
        punto2 = new Punto(x2 +10,y2 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 -4 );
        punto2 = new Punto(x2 +9,y2 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -3 );
        punto2 = new Punto(x2 +8,y2 -5);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -7 );
        punto2 = new Punto(x2 +8,y2 -9);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -2 );
        punto2 = new Punto(x2 +7,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -8 );
        punto2 = new Punto(x2 +7,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -2 );
        punto2 = new Punto(x2 +6,y2 -4);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -8 );
        punto2 = new Punto(x2 +6,y2 -10);
        line(punto1,punto2);

        //sotto
        punto1 = new Punto(x1 -2,y1 -12);
        punto2 = new Punto(x2 +2,y2 -12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -4,y1-11 );
        punto2 = new Punto(x2 +4,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -10);
        punto2 = new Punto(x2 +5,y2 -10);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -9);
        punto2 = new Punto(x2 -3,y2 -9);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -9);
        punto2 = new Punto(x2 +5,y2 -9);
        line(punto1,punto2);
        //endregion

        
    }

    public void nove(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        //#region cerchio
        punto1 = new Punto(x1 -2,y1+12 );
        punto2 = new Punto(x2 +2,y2+12 );
        line(punto1,punto2);

        punto1 = new Punto(x1 -4,y1-1 +12);
        punto2 = new Punto(x2 +4,y2 -1+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -2+12);
        punto2 = new Punto(x2 +5,y2 -2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -3+12);
        punto2 = new Punto(x2 -3,y2 -3+12);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -3+12);
        punto2 = new Punto(x2 +5,y2 -3+12);
        line(punto1,punto2);
        

        

        //lato sx
        punto1 = new Punto(x1 -10,y1 -5+12 );
        punto2 = new Punto(x2 -10,y2 -7+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -9,y1 -4+12 );
        punto2 = new Punto(x2 -9,y2 -8+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -3 +12);
        punto2 = new Punto(x2 -8,y2 -5+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -8,y1 -7+12 );
        punto2 = new Punto(x2 -8,y2 -9+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -2 +12);
        punto2 = new Punto(x2 -7,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -7,y1 -8 +12);
        punto2 = new Punto(x2 -7,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -2+12 );
        punto2 = new Punto(x2 -6,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -6,y1 -8+12 );
        punto2 = new Punto(x2 -6,y2 -10+12);
        line(punto1,punto2);

       

        
        //lato dx
        punto1 = new Punto(x1 +10,y1 -5+12);
        punto2 = new Punto(x2 +10,y2 -7+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +9,y1 -4+12 );
        punto2 = new Punto(x2 +9,y2 -8+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -3+12 );
        punto2 = new Punto(x2 +8,y2 -5+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +8,y1 -7+12 );
        punto2 = new Punto(x2 +8,y2 -9+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -2 +12);
        punto2 = new Punto(x2 +7,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +7,y1 -8 +12);
        punto2 = new Punto(x2 +7,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -2 +12);
        punto2 = new Punto(x2 +6,y2 -4+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 +6,y1 -8 +12);
        punto2 = new Punto(x2 +6,y2 -10+12);
        line(punto1,punto2);

        //sotto

        punto1 = new Punto(x1 -5,y1-11 +12);
        punto2 = new Punto(x2 +5,y2 -11+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -10+12);
        punto2 = new Punto(x2 +5,y2 -10+12);
        line(punto1,punto2);

        punto1 = new Punto(x1 -5,y1 -9+12);
        punto2 = new Punto(x2 -3,y2 -9+12);
        line(punto1,punto2);
        
        punto1 = new Punto(x1 +3,y1 -9+12);
        punto2 = new Punto(x2 +5,y2 -9+12);
        line(punto1,punto2);
        //endregion
        

        punto1 = new Punto(x1+2,y1 -12);
        punto2 = new Punto(x2-9,y2-12);
        line(punto1,punto2);

        punto1 = new Punto(x1+2,y1 -11);
        punto2 = new Punto(x2-10,y2-11);
        line(punto1,punto2);

        punto1 = new Punto(x1+2,y1 -10);
        punto2 = new Punto(x2-9,y2-10);
        line(punto1,punto2);

        punto1 = new Punto(x1+3,y1 );
        punto2 = new Punto(x2+4,y2);
        line(punto1,punto2);

        //linea verticale
        punto1 = new Punto(x1+10,y1 -6);
        punto2 = new Punto(x1+10,y1 +5);
        line(punto1,punto2);

        punto1 = new Punto(x1+9,y1 -7);
        punto2 = new Punto(x1+9,y1 +4);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 -8);
        punto2 = new Punto(x1+8,y1 +3);
        line(punto1,punto2);

        punto1 = new Punto(x1+7,y1 -9);
        punto2 = new Punto(x1+7,y1 +2);
        line(punto1,punto2);

        punto1 = new Punto(x1+6,y1 -10);
        punto2 = new Punto(x1+6,y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1+5,y1 -11);
        punto2 = new Punto(x1+5,y1 +1);
        line(punto1,punto2);

        punto1 = new Punto(x1+4,y1 -12);
        punto2 = new Punto(x1+4,y1 -7);
        line(punto1,punto2);

        punto1 = new Punto(x1+3,y1 -12);
        punto2 = new Punto(x1+3,y1 -8);
        line(punto1,punto2);

        punto1 = new Punto(x1+2,y1 -12);
        punto2 = new Punto(x1+2,y1 -9);
        line(punto1,punto2);
        


    }


    public void zero(Punto centro)
    {
        Punto punto1,punto2;
        float x1,y1,x2,y2; //max x 10, max y 12

        x1 = centro.getX();
        x2 = centro.getX();
        y1 = centro.getY();
        y2 = centro.getY();
        
        //verticali
        punto1 = new Punto(x1+10,y1 +9);
        punto2 = new Punto(x2+10,y2-9);
        line(punto1,punto2);

        punto1 = new Punto(x1+9,y1 +9);
        punto2 = new Punto(x2+9,y2-9);
        line(punto1,punto2);

        punto1 = new Punto(x1+8,y1 +9);
        punto2 = new Punto(x2+8,y2-9);
        line(punto1,punto2);

        punto1 = new Punto(x1-10,y1 +9);
        punto2 = new Punto(x2-10,y2-9);
        line(punto1,punto2);

        punto1 = new Punto(x1-9,y1 +9);
        punto2 = new Punto(x2-9,y2-9);
        line(punto1,punto2);

        punto1 = new Punto(x1-8,y1 +9);
        punto2 = new Punto(x2-8,y2-9);
        line(punto1,punto2);

        // orizzontali
        punto1 = new Punto(x1-7,y1 +12);
        punto2 = new Punto(x2+7,y2+12);
        line(punto1,punto2);

        punto1 = new Punto(x1-8,y1 +11);
        punto2 = new Punto(x2+8,y2+11);
        line(punto1,punto2);

        punto1 = new Punto(x1-9,y1 +10);
        punto2 = new Punto(x2+9,y2+10);
        line(punto1,punto2);

        punto1 = new Punto(x1-7,y1 -12);
        punto2 = new Punto(x2+7,y2 -12);
        line(punto1,punto2);

        punto1 = new Punto(x1-8,y1 -11);
        punto2 = new Punto(x2+8,y2 -11);
        line(punto1,punto2);

        punto1 = new Punto(x1-9,y1 -10);
        punto2 = new Punto(x2+9,y2 -10);
        line(punto1,punto2);

        // angolo
        punto1 = new Punto(x1+8,y1+10);
        punto2 = new Punto(x1-8,y1-10);
        line(punto1,punto2);

        punto1 = new Punto(x1+7,y1+10);
        punto2 = new Punto(x1-9,y1-10);
        line(punto1,punto2);

        

        


    }


    //endregion



   public void orologio(String time, int contatore, int altezza)
   {

            //punti al centro
            line(new Punto(90,31-2),new Punto(90,33-2));
            line(new Punto(89,32-2),new Punto(91,32-2));

            line(new Punto(90,31-9),new Punto(90,33-9));
            line(new Punto(89,32-9),new Punto(91,32-9));

            


            //#region righe fike
            int x = contatore % schermo.getMaxCols();
            int y = altezza;
            
            
            
            Punto puntoRighe = new Punto(x,y);
            Punto puntoRighe2 = new Punto(x + 30,y);
            // line(puntoRighe, puntoRighe2, '@');

            // puntoRighe = new Punto(x -1,y+1);
            // puntoRighe2 = new Punto(x -1 + 30,y+1);
            // line(puntoRighe, puntoRighe2,'%');

            // puntoRighe = new Punto(x -2,y+2);
            // puntoRighe2 = new Punto(x -2 + 30,y+2);
            // line(puntoRighe, puntoRighe2,'#');

            // puntoRighe = new Punto(x -3,y+3);
            // puntoRighe2 = new Punto(x -3 + 30,y+3);
            // line(puntoRighe, puntoRighe2,'*');

            // puntoRighe = new Punto(x -4,y+4);
            // puntoRighe2 = new Punto(x -4 + 30,y+4);
            // line(puntoRighe, puntoRighe2,'+');

            // puntoRighe = new Punto(x -5,y+5);
            // puntoRighe2 = new Punto(x -5 + 30,y+5);
            // line(puntoRighe, puntoRighe2,'=');

            //" .:-=+*#%@"
            puntoRighe = new Punto(x,y);
            puntoRighe2 = new Punto(x +7 ,y+5);
            line(puntoRighe, puntoRighe2,'@');

            

            puntoRighe = new Punto(x-1,y);
            puntoRighe2 = new Punto(x +7-1 ,y+5);
            line(puntoRighe, puntoRighe2,'@');

            puntoRighe = new Punto(x-2,y);
            puntoRighe2 = new Punto(x -2+7 ,y+5);
            line(puntoRighe, puntoRighe2,'%');

            puntoRighe = new Punto(x-3,y);
            puntoRighe2 = new Punto(x -3+7 ,y+5);
            line(puntoRighe, puntoRighe2,'%');

            puntoRighe = new Punto(x-4,y);
            puntoRighe2 = new Punto(x -4+7 ,y+5);
            line(puntoRighe, puntoRighe2,'#');

            puntoRighe = new Punto(x-5,y);
            puntoRighe2 = new Punto(x -5+7 ,y+5);
            line(puntoRighe, puntoRighe2,'*');

            puntoRighe = new Punto(x-6,y);
            puntoRighe2 = new Punto(x -6+7 ,y+5);
            line(puntoRighe, puntoRighe2,'+');

            

            

             //#endregion
            

            //#region orologio
            time = time.replace(":","");
            for (int i =0; (i< 4) ; ++i)
            {
                //rettangolo(new Punto(51 + i*26 ,26),20,24);
                //printString(new Punto(51 + i*26,26), time.substring(i,i+1));

                switch( time.substring(i,i+1))
                {
                    case "1" :
                        uno(new Punto(51 + i*26 ,26));
                        break;
                    case "2" :
                        due(new Punto(51 + i*26 ,26));
                        break;
                    case "3" :
                        tre(new Punto(51 + i*26 ,26));
                        break;
                    case "4" :
                        quattro(new Punto(51 + i*26 ,26));
                        break;
                    case "5" :
                        cinque(new Punto(51 + i*26 ,26));
                        break;
                    case "6" :
                        sei(new Punto(51 + i*26 ,26));
                        break;
                    case "7" :
                        sette(new Punto(51 + i*26 ,26));
                        break;
                    case "8" :
                        otto(new Punto(51 + i*26 ,26));
                        break;
                    case "9" :
                        nove(new Punto(51 + i*26 ,26));
                        break;
                    case "0" :
                        zero(new Punto(51 + i*26 ,26));
                        break;

                }
            }
            //endregion
   }

    //#endregion
}
    