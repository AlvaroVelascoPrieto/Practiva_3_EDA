package org.eda.packlaboratorio3;

public class Test {

    public static void main(String[] args) {
        String direccion = System.getProperty("user.dir") + "\\src\\org\\eda\\packlaboratorio3\\FilmsActors20212022-20000-first-lines.txt";
        ListaPeliculas.getListaPeliculas().lectorArchivo(direccion);
        GraphHash miGraphHash = new GraphHash();
        Stopwatch reloj = new Stopwatch();
        miGraphHash.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
        System.out.println(miGraphHash.estanConectados("Castillo, K.C.", "Apacible, John"));
        double tiempo = reloj.elapsedTime();
        System.out.println(tiempo);
    }
}

