/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrabajoPracticoFinalEDD;
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        boolean exito = true;
        Nodo nuevoNodo = new Nodo(elem, null);
        if (this.frente == null) {
            this.frente = nuevoNodo;
        } else {
            this.fin.setEnlace(nuevoNodo);
        }
        this.fin = nuevoNodo;
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object frente = null;
        if (this.frente != null) {
            frente = this.frente.getElem();
        }
        return frente;
    }

    public boolean esVacio() {
        return this.frente == null && this.fin == null;
    }

    public void vaciar() {
        if (this.frente != null) {
            this.frente = null;
            this.fin = null;
        }
    }

    public Cola clone() {
        Cola colaClon = new Cola();
        Nodo aux = this.frente;
        Nodo nuevoNodo;
        if (this.frente != null) {
            nuevoNodo = new Nodo(this.frente.getElem(), null);
            colaClon.frente = nuevoNodo;
            colaClon.fin = nuevoNodo;
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                nuevoNodo = new Nodo(aux.getElem(), null);
                colaClon.fin.setEnlace(nuevoNodo);
                colaClon.fin = nuevoNodo;
            }
        }
        return colaClon;
    }

    public String toString() {
        String cad = "cola vacia";
        Nodo aux = this.frente;
        if (this.frente != null) {
            cad = "| ";

            while (aux != null) {
                cad = cad + " " + aux.getElem();
                aux = aux.getEnlace();
            }
            cad = cad + " |";
        }
        return cad;
    }

}