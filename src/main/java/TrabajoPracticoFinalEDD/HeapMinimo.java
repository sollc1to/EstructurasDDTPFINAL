/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrabajoPracticoFinalEDD;

/**
 *
 * @author PC
 */
public class HeapMinimo {

    private Comparable[] heap;
    private int ultimo;
    private int TAMANIO = 30;

    public HeapMinimo() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;

    }

    public boolean insertar(Object elem) {
        boolean exito = false;

        if (this.ultimo + 1 <= this.TAMANIO) {

            if (ultimo == 0) {
                this.ultimo++;
                this.heap[ultimo] = (Comparable) elem;
                exito = true;

            } else {
                ultimo++;

                this.heap[ultimo] = (Comparable) elem;
                hacerSubir(ultimo);
                exito = true;

            }

        }
        return exito;

    }

    private void hacerSubir(int pos) {
        int posPadre;
        boolean subir = false;
        Comparable auxiliar;

        while (!subir && pos / 2 > 0) {
            posPadre = pos / 2;
            if (this.heap[posPadre].compareTo(this.heap[pos]) > 0) {

                auxiliar = this.heap[posPadre];
                this.heap[posPadre] = this.heap[pos];
                this.heap[pos] = auxiliar;

                pos = posPadre;

            } else {
                subir = true;

            }
        }

    }

    public boolean eliminarCima() {
        boolean exito = false;
        if (this.ultimo != 0) {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;

        }
        return exito;

    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                if (posH < this.ultimo) {
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        posH++;

                    }

                }

                if (this.heap[posH].compareTo(temp) < 0) {
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;

                } else {
                    salir = true;

                }

            } else {
                salir = true;
            }

        }

    }

    public Object obtenerCima() {
        Object elem = null;

        if (!this.esVacio()) {
            elem = this.heap[1];

        }

        return elem;
    }

    public boolean esVacio() {

        return (this.ultimo == 0);
    }

    public void vaciar() {
        this.heap = null;
        this.ultimo = 0;

    }

    public String toString() {
        String cadena = "";
        int pos = 1;

        for (int i = 0; i < this.ultimo; i++) {
            cadena = cadena + this.heap[pos];
            pos++;

        }

        return cadena;

    }

}
