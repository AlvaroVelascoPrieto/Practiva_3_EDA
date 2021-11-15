package org.eda.packlaboratorio3;

public class Test {

    public static void main(String[] args) {
        String direccion = System.getProperty("user.dir") + "\\src\\org\\eda\\packlaboratorio3\\FilmsActors20212022-20000-first-lines.txt";
        ListaPeliculas.getListaPeliculas().lectorArchivo(direccion);
        GraphHash miGraphHash = new GraphHash();
        miGraphHash.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
        miGraphHash.print();
    }
}

