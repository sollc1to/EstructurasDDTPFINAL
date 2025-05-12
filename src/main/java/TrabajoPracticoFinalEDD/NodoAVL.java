/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrabajoPracticoFinalEDD;

public class NodoAVL {

    private Comparable elem;

    private int altura;

    private NodoAVL izquierdo;

    private NodoAVL derecho;

    public NodoAVL(Object elem, NodoAVL izquierdo, NodoAVL derecho) {

        this.altura = 0;

        this.elem = (Comparable) elem;

        this.izquierdo = izquierdo;

        this.derecho = derecho;

    }

    public Comparable getElem() {

        return this.elem;
    }

    public void setElem(Object elem) {

        this.elem = (Comparable) elem;
    }

    public int getAltura() {

        return this.altura;
    }

    public NodoAVL getIzquierdo() {

        return this.izquierdo;

    }

    public NodoAVL getDerecho() {

        return this.derecho;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {

        this.derecho = derecho;
    }

    public void recalcularAltura() {
        int alturaHD = (this.derecho == null) ? -1 : this.derecho.getAltura();
        int alturaHI = (this.izquierdo == null) ? -1 : this.izquierdo.getAltura();

        this.altura = Math.max(alturaHI, alturaHD) + 1;

    }

    public int calcularBalance() {
        int alturaHI = (this.izquierdo == null) ? -1 : this.izquierdo.getAltura();
        int alturaHD = (this.derecho == null) ? -1 : this.derecho.getAltura();

        return (alturaHI - alturaHD);

    }

  

}
