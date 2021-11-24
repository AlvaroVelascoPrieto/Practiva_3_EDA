package org.eda.packlaboratorio3;

public class Test {

    public static void main(String[] args) {
        String direccion = System.getProperty("user.dir") + "\\src\\org\\eda\\packlaboratorio3\\listaPequeña.txt";
        ListaPeliculas.getListaPeliculas().lectorArchivo(direccion);
        GraphHash miGraphHash = new GraphHash();
        Stopwatch reloj = new Stopwatch();
        miGraphHash.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
        System.out.println(miGraphHash.estanConectados("Nicholson, Sean", "Hatz"));
        double tiempo = reloj.elapsedTime();
        System.out.println(tiempo);
    }
}

