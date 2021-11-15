package org.eda.packlaboratorio1;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ListaPeliculas {
    private HashMap<String,Pelicula> peliculas;
    private ListaActores todos;
    private static ListaPeliculas miListaPeliculas;

    //Constructora
    private ListaPeliculas(){
        this.peliculas = new HashMap<String,Pelicula>();
        this.todos = new ListaActores();
    }

    //Patron singleton que devuelve la unica instancia de ListaPeliculas
    public static ListaPeliculas getListaPeliculas() {
        if(ListaPeliculas.miListaPeliculas==null) {
            ListaPeliculas.miListaPeliculas = new ListaPeliculas();
        }
        return miListaPeliculas;
    }

    //Métodos
    public void lectorArchivo(String pNombreArchivo){
        try {
            Scanner archivo = new Scanner(new FileReader(pNombreArchivo));
            String linea;

            while (archivo.hasNextLine()){
                linea = archivo.nextLine();
                String[] part =linea.split("\\s++-{1,}+>{1,}+\\s");
                String pelicula=part[0];
                String Actores=part[1];

                String[] tablaAct =Actores.split("\\s+#{1,}+\\s");
                peliculas.put(pelicula, new Pelicula(pelicula,0));
                int i=0;
                while (i<(tablaAct.length)){
                    if(todos.contiene(tablaAct[i])){
                        Actor miActor = todos.buscarActor(tablaAct[i]);
                        peliculas.get(pelicula).insertarActor(miActor);
                    }
                    else{
                        Actor miActor = new Actor(tablaAct[i]);
                        peliculas.get(pelicula).insertarActor(miActor);
                        todos.insertarActor(miActor);
                    }
                    i++;
                }
            }
            System.out.println("Leido");
            archivo.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public Actor buscarActor(String pNombre){
        return todos.buscarActor(pNombre);
    }

    public void incrementarRecaudacion(String pPelicula, double pImporte){
        if(ListaPeliculas.getListaPeliculas().peliculas.containsKey(pPelicula)) {
            ListaPeliculas.getListaPeliculas().peliculas.get(pPelicula).incrementarRecaudacion(pImporte);
        }
    }

    public void borrarActor(String pActor){
        peliculas.forEach((k,v) -> v.borrarActor(pActor));
        todos.borrarActor(pActor);
    }

    public void insertarActor(String pPelicula, String pNombre){
        Actor miActor;
        if (todos.contiene(pNombre)){
            miActor = todos.buscarActor(pNombre);
        }
        else {
            miActor = new Actor(pNombre);
            todos.insertarActor(miActor);
        }

        if (peliculas.containsKey(pPelicula)){
            peliculas.get(pPelicula).insertarActor(miActor);
        }
        else{
            peliculas.put(pPelicula,new Pelicula(pPelicula, 0));
            peliculas.get(pPelicula).insertarActor(miActor);
        }

    }

    public HashMap<String, Actor> devolverActores(String pPelicula){
        HashMap<String,Actor> actoresADevolver = new HashMap<String, Actor>();
        if (peliculas.containsKey(pPelicula)){
             actoresADevolver = peliculas.get(pPelicula).devolverActores();
        }
        return actoresADevolver;
    }


    public HashMap<String,Pelicula> devolverPeliculas(String pActor){
        HashMap<String, Actor> actores;
        HashMap<String,Pelicula> peliculasADevolver = new HashMap<String,Pelicula>();
        for (Map.Entry<String, Pelicula> entry : peliculas.entrySet()) {
            actores = entry.getValue().devolverActores();
            if (actores.containsKey(pActor)){
                peliculasADevolver.put(entry.getKey(),entry.getValue());
            }
        }
        return peliculasADevolver;
    }

    public void guardarListaEnFichero(String pDireccionFichero){
        FileWriter fichero;
        PrintWriter pw;
        try{
            fichero = new FileWriter(pDireccionFichero);
            pw = new PrintWriter(fichero);
            if (!peliculas.isEmpty()){
                for (Map.Entry<String, Pelicula> entry : peliculas.entrySet()) {
                    pw.print(entry.getKey() + " --->>> ");
                    HashMap<String,Actor> actores = entry.getValue().devolverActores();
                    for (Map.Entry<String, Actor> entryAct : actores.entrySet()){
                        pw.print(entryAct.getKey() + " ##### ");
                    }
                    pw.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Actor> devolverTodosLosActoresOrdenados(){
        return todos.ordenarActores();
    }

    //Metodos para las pruebas
    public double getRecaudacion(String pPelicula){
        return this.peliculas.get(pPelicula).devolverRecaudacion();
    }

}
