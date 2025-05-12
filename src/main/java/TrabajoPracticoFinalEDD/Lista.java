package TrabajoPracticoFinalEDD;

public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        boolean exito = true;
        if (pos < 1 || this.longitud() + 1 < pos) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(elem, this.cabecera);
                longitud++;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
                longitud++;

            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;

        if (pos < 1 || this.longitud() + 1 < pos) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                longitud--;

            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                longitud--;
            }

        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object elem = "Lista Vacia";

        if (1 <= pos && pos <= this.longitud()) {
            int i = 1;
            Nodo aux = this.cabecera;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();

        }
        return elem;
    }

    public int localizar(Object elem) {

        int i = -1;
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            int f = 1;
            if (aux.getElem().equals(elem)) {
                i = 1;
            }
            while (f < this.longitud() && !aux.getElem().equals(elem)) {
                aux = aux.getEnlace();
                f++;
                if (aux.getElem().equals(elem)) {
                    i = f;
                }
            }
        }
        return i;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {
        return (longitud == 0);
    }

    public Lista clone() {
        Lista ListaClon = new Lista();
        Nodo topeAux = this.cabecera;
        Nodo nodoAnterior = null;
        Nodo nodoActual;
        if (this.cabecera != null) {
            nodoActual = new Nodo(cabecera.getElem(), null); // caso especial
            ListaClon.cabecera = nodoActual; //
            while (topeAux.getEnlace() != null) {
                topeAux = topeAux.getEnlace();
                nodoAnterior = nodoActual;
                nodoActual = new Nodo(topeAux.getElem(), null);
                nodoAnterior.setEnlace(nodoActual);
            }

        }
        
        ListaClon.longitud = longitud;


        return ListaClon;
    }

    public int longitud() {
        return longitud;
    }

    public String toString() {
        String datos;
        Nodo aux = this.cabecera;
        if (this.cabecera == null) {
            datos = "Lista vacia";

        } else {
            datos = "[ ";
            while (aux != null) {
                datos = datos + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    datos = datos + " , ";
                }

            }
            datos = datos + " ]";
        }
        return datos;
    }

}
