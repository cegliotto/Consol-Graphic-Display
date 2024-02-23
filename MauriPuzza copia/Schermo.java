package toolbox.eseguibili;
import java.util.ArrayList;
import geometria.oggetti2d.Punto;

public class Schermo
{

    //#region Documentation
    //definisce la classe schermo che serve per diseganre ogni singolo carattere a schermo,
    // gestisce la matrice di questi caratteri, gestisce i punti 
    //Debug fai diventare singleton, 
    //#endregion

    //#region variabili
    //riche e colonne con anche i massimi
    int rows = 20;
    int cols = 20;
    int maxCols = 187; //(x)
    int maxRows = 60; //(y)

    //matrice che salva la griglia con i punti
    public char[][] matrix;

    //lista dei punti
    public ArrayList<Punto> punti = new ArrayList<Punto>();



    
    //#endregion



    
    //#region costruttori
    public Schermo()
    {
        //cap di righe e colonne
        if (rows >= maxRows)
        {
            this.rows = maxRows ; 
        }
        if (cols >= maxCols)
        {
            this.cols = maxCols;
        } 

        matrix = new char[rows][cols];
        punti.removeAll(punti);
        
    }
    public Schermo(int rows, int cols)
    {
        //set di riche e colonne
        this.rows = rows;
        this.cols = cols;

        if (rows >= maxRows)
        {
            this.rows = maxRows; 
        }
        if (cols >= maxCols)
        {
            this.cols = maxCols;
        }

        matrix = new char[rows][cols];
       // new Schermo(); //perche cazzo non funziona??
    }

    //#endregion
  
  


    //#region metodi


    //#region Add e Delete
    //aggiunge i punti 2d alla griglia che andranno calcolati altrove(?)
    public void addPoints(Punto... punti)
    {
        for(Punto temp : punti)
        {
            this.punti.add(temp);
        }
    }

    public void addPoints(Punto punto)
    {
        this.punti.add(punto);
    }
    
     
     
    public void deletePoints(Punto... punti)
    {
        for(Punto temp : punti)
        {
            matrix[temp.getYint()][temp.getXint()] = 43;
            this.punti.remove(temp);
        }
    }

    public void deletePoints(Punto punto)
    {
        this.punti.remove(punti.indexOf(punto)); // non funziona
        matrix[punto.getYint()][punto.getXint()] = 43;
        
    }

    public void deletePoints()
    {
        if(punti != null)
        {
            //da cancellare
            // for (int i = 0; i < punti.size(); i++)
            // {
            //     if(punti.get(i).getY() < rows && punti.get(i).getY() >= 0 &&  punti.get(i).getX() < cols && punti.get(i).getX() >= 0 ) //Debug verifica che gli indici siano corretti
            //     {
            //         matrix[punti.get(i).getY()][punti.get(i).getX()] = 43;
            //     }
            // }
            punti.removeAll(punti);
        }
    }
    //#endregion


    //#region get e set
    public int getMaxRow()
    {
        return maxRows;
    }
    public int getMaxCols()
    {
        return maxCols;
    }



    //#region Matrice
    //riempie la matrice
    public void fillMatrix()
    {

        //prima la setta tutta vuota
        for (int i = 0 ; i < rows ; ++i)
        {   
            for (int j = 0; j < cols; ++j)
            {
            
                matrix[i][j] = 32;// " " in ascii
                //matrix[i][j] = 46;// "." in ascii
                //matrix[i][j] = 58;// ":" in ascii
                //matrix[i][j] = 43;// "+" in ascii
                //matrix[i][j] = 35;// "#" in asci
               //matrix[i][j] = 0xDB;
              
                

            }
        }

        // setta i punti
        if(punti.size() !=  0)
        {
            for (Punto temp : punti)
            {
                if(temp.getY() < rows  && temp.getY() >= 0 && temp.getX() < cols  && temp.getX() >= 0) //Debug verifica che gli indici siano corretti
                {
                    matrix[temp.getYint()][temp.getXint()] = temp.getChar();// "#" in ascii 
                                                      // per qualche motivo in java la matrice ha come indice delle 
                                                      //righe i e delle colonne j e non viceversa
                } 
            }
        }
    }


    //fa la print della Matrice
    private void drawGrid()
    {
        for (int i = rows-1 ; i >= 0; i--)
        {   
            for (int j = 0; j < cols; j++)
            {
                System.out.print( matrix[i][j] );
            }
            System.out.println();
        }
    }




    //disegna la griglia con spazi sopra e sotto 
    //TO DO aggiorna la griglia con una matrice
    public void drawFrame()
    {
        //spazi vuoti 
        for (int i = 0; i < maxRows - rows+5; i++)
        {
            System.out.println();
        }
        
        

        //griglia

        fillMatrix();
        drawGrid();
        deletePoints();
    }
    //#endregion

    //#endregion

}
    