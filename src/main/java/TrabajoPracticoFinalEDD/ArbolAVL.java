package TrabajoPracticoFinalEDD;

public class ArbolAVL implements Comparable {

    private NodoAVL raiz;

    public void ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Object elem) {
        NodoAVL nodoAux = null;

        boolean pertenece = false;

        if (this.raiz != null) {
            nodoAux = buscarElem(this.raiz, (Comparable) elem);

        }

        return (nodoAux != null);

    }

    public Object recuperarElemento(Object elem) {
        NodoAVL aux = null;
        Object elemAux = null;
        if (this.raiz != null) {
            aux = buscarElem(this.raiz, (Comparable) elem);
            
            if (aux != null) {
                elemAux = aux.getElem();
                
                
            }

        }
        
        
        return elemAux;

    }

    private NodoAVL buscarElem(NodoAVL nodo, Comparable elem) {
        boolean exito = false;
        NodoAVL nodoAux = null;
        int comparable;

        if (nodo != null) {

            if (nodo.getElem().compareTo(elem) == 0) {
                nodoAux = nodo;

            } else {

                comparable = nodo.getElem().compareTo(elem);

                if (comparable < 0) {
                    nodoAux = buscarElem(nodo.getDerecho(), elem);

                } else {
                    nodoAux = buscarElem(nodo.getIzquierdo(), elem);

                }

            }

        }
        return nodoAux;

    }

    public boolean insertar(Object elem) {
        boolean insertado = false;
        int comparar;
        NodoAVL aux;
        if (this.raiz != null) {
            insertado = insertarAux((Comparable) elem, this.raiz);
            verificarBalance(raiz, null, 'r'); //Por último, verifico el balance de la raíz.

        } else {
            this.raiz = new NodoAVL(elem, null, null);
            insertado = true;

        }
        return insertado;

    }

    //El insertar debe ser recursivo, ya que al insertar, debo verificar el balance del padre, el padre del padre, etc.. hasta la raíz.
    private boolean insertarAux(Comparable elem, NodoAVL n) {
        boolean exito = false;
        int comparar;
        NodoAVL aux;
        if (n != null) {

            comparar = n.getElem().compareTo(elem);

            if (comparar < 0) { //Si el nodo es menor al elemento, entonces se inserta en lado derecho.
                if (n.getDerecho() != null) {
                    exito = insertarAux(elem, n.getDerecho());

                    if (exito == true) { //Si exito es true, esto quiere decir que debo verificar el balanceo.

                        verificarBalance(n.getDerecho(), n, 'd');

                    }

                } else {
                    exito = true;
                    n.setDerecho(new NodoAVL(elem, null, null));
                    n.getDerecho().recalcularAltura();
                }

            } else if (comparar > 0) {

                {
                    if (n.getIzquierdo() != null) { //Si el nodo es mayor al elemento, se inserta en el lado izquierdo.
                        exito = insertarAux(elem, n.getIzquierdo());
                        if (exito == true) { //Si exito es true, esto quiere decir que debo verificar el balanceo.
                            verificarBalance(n.getIzquierdo(), n, 'i');

                        }

                    } else {
                        n.setIzquierdo(new NodoAVL(elem, null, null));
                        exito = true;
                        n.getIzquierdo().recalcularAltura();

                    }

                }

            }

        }

        return exito;

    }

    private void verificarBalance(NodoAVL nodo, NodoAVL nodoPadre, char lugar) {
        int balance, balanceHijo;
        NodoAVL aux = null;

        nodo.recalcularAltura();

        balance = nodo.calcularBalance();

        if (balance == 2) { //Si el balance es dos, significa que está desbalanceado hacia la izquierda.

            balanceHijo = nodo.getIzquierdo().calcularBalance(); //Verifico el balance del hijo izquierdo.

            if (balanceHijo == -1) { //En este caso se hace una rotación doble izq-der.

                aux = rotacionSimpleIzquierda(nodo.getIzquierdo());

                nodo.setIzquierdo(aux);

                aux = rotacionSimpleDerecha(nodo);

            } else { //Se hace una rotación simple a derecha.

                aux = rotacionSimpleDerecha(nodo);

            }

        } else if (balance == -2) {
            balanceHijo = nodo.getDerecho().calcularBalance();

            if (balanceHijo == 1) { //Rotación doble der-izq.

                aux = rotacionSimpleDerecha(nodo.getDerecho());

                nodo.setDerecho(aux);

                aux = rotacionSimpleIzquierda(nodo);

            } else { //Rotación simple a derecha.

                aux = rotacionSimpleIzquierda(nodo);

            }

        }
        //Si aux != null, significa que se modificaron los enlaces.
        if (aux != null) {

            if (lugar == 'i') {
                nodoPadre.setIzquierdo(aux);

            } else if (lugar == 'd') {
                nodoPadre.setDerecho(aux);
            } else {

                this.raiz = aux;
            }

        }

    }

    private NodoAVL rotacionSimpleDerecha(NodoAVL nodo) { //Paso el padre como parámetro para ya modificar.

        NodoAVL aux = nodo.getIzquierdo(); //El hijo izquierdo, será ahora el padre de nodo.
        NodoAVL temp = aux.getDerecho();
        aux.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        //Una vez hecho el cambio, recalculo la altura de ambos.
        nodo.recalcularAltura();
        aux.recalcularAltura();

        return aux;

    }

    private NodoAVL rotacionSimpleIzquierda(NodoAVL nodo) {
        NodoAVL aux = nodo.getDerecho(); //El hijo derecho, será ahora el padre del nodo.
        NodoAVL temp = aux.getIzquierdo();
        aux.setIzquierdo(nodo);
        nodo.setDerecho(temp);

        //Recalculo la altura de los dos nodos.
        nodo.recalcularAltura();
        aux.recalcularAltura();

        return aux;

    }

    public boolean eliminar(Object elem) {
        int comparar;
        boolean eliminado = false;
        NodoAVL nodoPadre, aux;

        if (this.raiz != null) {
            if (this.raiz.getElem().compareTo((Comparable) elem) == 0) {
                eliminado = determinarCasoYEliminarRaiz(this.raiz);

            } else {
                eliminado = buscarPadre(this.raiz, (Comparable) elem);

            }

            if (eliminado) {
                verificarBalance(this.raiz, null, 'r');

            }
        }
        return eliminado;

    }

    private boolean buscarPadre(NodoAVL nodo, Comparable elem) {
        int comparableI, comparableD, comparable;
        boolean eliminado = false;
        NodoAVL nodoPadre = null, aux;

        if (nodo != null) { //Comparo sus dos hijos para ver si es igual a "elem".
            comparableD = (nodo.getDerecho() != null) ? (nodo.getDerecho().getElem().compareTo(elem)) : -1;
            comparableI = ((nodo.getIzquierdo() != null) ? (nodo.getIzquierdo().getElem().compareTo(elem)) : -1);

            if (comparableD == 0 || comparableI == 0) { //Verificio si alguno de los hijos es igual a elem. 
                //Una vez que alguno sea igual, lo elimino y empiezo a calcular el balance.
                determinarCasoyEliminar(nodo, elem);

                nodo.recalcularAltura(); //Recalculo la altura de "nodo", el cuál sería el nodo padre del eliminado.

                eliminado = true;

            } else { //Si no, sigo buscando a el elemento.
                comparable = nodo.getElem().compareTo(elem);

                if (comparable < 0) {
                    eliminado = buscarPadre(nodo.getDerecho(), elem);
                    if (eliminado) { //En el caso de que eliminado sea true, debo ver el balance
                        verificarBalance(nodo.getDerecho(), nodo, 'd');

                    }

                } else {

                    eliminado = buscarPadre(nodo.getIzquierdo(), elem);
                    if (eliminado) {
                        verificarBalance(nodo.getIzquierdo(), nodo, 'i');
                    }

                }

            }

        }
        return eliminado;

    }

    private boolean determinarCasoYEliminarRaiz(NodoAVL raiz) {

        char lugar;
        if (raiz.getIzquierdo() != null && raiz.getDerecho() != null) { //Caso 3, la raíz tiene hijo izquierdo y derecho.
            eliminarCasoTresRaiz(raiz);

        } else {
            if (raiz.getDerecho() != null || raiz.getIzquierdo() != null) { //Caso 2, la raíz tiene solo un hijo.
                eliminarCasoDos(raiz, null, 'r', (raiz.getDerecho() != null) ? 'd' : 'i');

            } else { //Caso 1, la raíz no tiene hijos.
                this.raiz = null;

            }

        }
        return true;

    }

    private void determinarCasoyEliminar(NodoAVL nodoPadre, Comparable elem) {
        NodoAVL nodoHijo;
        char lugarHijo;
        //Busco el hijo el cual eliminar.
        nodoHijo = ((nodoPadre.getIzquierdo() != null && nodoPadre.getIzquierdo().getElem().compareTo(elem) == 0) ? nodoPadre.getIzquierdo() : nodoPadre.getDerecho());

        //Si al comparar no es el hijo izquierdo, entonces es el hijo derecho.
        lugarHijo = ((nodoPadre.getIzquierdo() != null && nodoPadre.getIzquierdo().getElem().compareTo(elem) == 0) ? 'i' : 'd');
        //Veo que tipo de caso es el que tengo que eliminar
        if (nodoHijo.getIzquierdo() != null && nodoHijo.getDerecho() != null) { //Caso 3, el hijo tiene hijo izquierdo e hijo derecho.
            eliminarCasoTres(nodoPadre, nodoHijo, lugarHijo);

        } else if (nodoHijo.getIzquierdo() != null || nodoHijo.getDerecho() != null) { //Caso dos, existe solo un hijo izquierdo o derecho.
            eliminarCasoDos(nodoPadre, nodoHijo, lugarHijo, (nodoHijo.getIzquierdo() != null ? 'i' : 'd'));

        } else { //Caso 1. Debo determinar si es el izquierdo o derecho.
            eliminarCasoUno(nodoPadre, lugarHijo);

        }
    }

    private void eliminarCasoUno(NodoAVL nodoPadre, char lugar) {

        if (lugar == 'i') {
            nodoPadre.setIzquierdo(null);
        } else {
            nodoPadre.setDerecho(null);
        }

    }

    private void eliminarCasoDos(NodoAVL nodoPadre, NodoAVL nodoHijo, char lugar, char lugarHijo) {

        if (lugar == 'r') {//Caso de la raíz

            this.raiz = (lugarHijo == 'i' ? nodoPadre.getIzquierdo() : nodoPadre.getDerecho());

        } else if (lugar == 'i') { //Si el lugar es 'i', seteo en el lado izquierdo el hijo en lugar hijo.
            //Lo mismo para el lado derecho.

            nodoPadre.setIzquierdo((lugarHijo == 'i') ? nodoHijo.getIzquierdo() : nodoHijo.getDerecho());

        } else {
            nodoPadre.setDerecho((lugarHijo == 'i') ? nodoHijo.getIzquierdo() : nodoHijo.getDerecho());

        }

    }

    private void eliminarCasoTres(NodoAVL nodoPadre, NodoAVL nodoHijo, char lugarHijo) {
        //Para este caso, debo buscar un candidato A.
        //Candidato A. Mayor elemento del sub arbol zquierdo de nodoHijo.
        NodoAVL candidato;
        NodoAVL nodoAux, nodoAuxI;
        //Sabemos que no tiene enlace derecho por ser candidato.getDerecho() = null (El último nodo de los enlaces hacia la derecha)
        candidato = candidatoA(nodoHijo.getIzquierdo());

        if (candidato.getElem() != nodoHijo.getIzquierdo().getElem()) { // Si el candidato es distinto del hijo izquierdo directo del nodo...

            if (candidato.getIzquierdo() != null) { //En caso de que tenga hijo izquierdo, debo setearlo como padre del nodoHijo.getIzquierdo()

                candidato.getIzquierdo().setIzquierdo(nodoHijo.getIzquierdo());

            } else {

                candidato.setIzquierdo(nodoHijo.getIzquierdo());
            }

            candidato.setDerecho(nodoHijo.getDerecho());

        } else {

            if (nodoHijo.getDerecho() != null) {

                candidato.setDerecho(nodoHijo.getDerecho());

            }

        }

        if (lugarHijo == 'i') {
            nodoPadre.setIzquierdo(candidato);
        } else {
            nodoPadre.setDerecho(candidato);
        }

        nodoPadre.recalcularAltura();

    }

    private void eliminarCasoTresRaiz(NodoAVL nodo) {
        NodoAVL nodoAux = candidatoA(nodo.getIzquierdo());

        if (nodoAux.getElem().compareTo(nodo.getIzquierdo().getElem()) != 0) { //Caso de que el nodoAux no sea el hijo izquierdo directo de la raiz.
            nodo.getIzquierdo().recalcularAltura();

            if (nodoAux.getIzquierdo() != null) { //En caso de que el candidato tenga hijo izquierdo, debo enlazar este
                //Con el hijo izquierdo de la raiz.

                nodoAux.getIzquierdo().setIzquierdo(nodo.getIzquierdo());

            } else { //Si no, simplemente enlazo el hijo izquierdo.

                nodoAux.setIzquierdo(nodo.getIzquierdo());

            }

            //Tambien enlazo el candidato con el hijo derecho de la raiz.
            nodoAux.setDerecho(nodo.getDerecho());
            this.raiz = nodoAux;

        } else { //En caso de que el candidato A sea el mismo que el hijo izquierdo de la raíz.
            nodo.setIzquierdo(null);

            nodoAux.setDerecho(nodo.getDerecho());
            this.raiz = nodoAux;

        }

    }

    public NodoAVL candidatoA(NodoAVL nodoHijo) {
        NodoAVL candidatoA = null;
        if (nodoHijo != null && (nodoHijo.getDerecho() != null && nodoHijo.getDerecho().getDerecho() != null)) {
            //En este caso, el hijo izquierdo tiene más de un hijo derecho.
            candidatoA = candidatoA(nodoHijo.getDerecho());
            if (candidatoA != null) { //Verifico el balance una vez encontrado el nodoHijo.
                verificarBalance(nodoHijo.getDerecho(), nodoHijo, 'd');
            }

        } else {
            if (nodoHijo.getDerecho() != null) {
                //En este caso, solo tiene un hijo derecho.
                candidatoA = nodoHijo.getDerecho(); //Necesito el padre para poder desenlazar el candidato.
                nodoHijo.setDerecho(null);
                nodoHijo.recalcularAltura();

            } else {
                candidatoA = nodoHijo;
            }

        }
        return candidatoA;

    }

    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = generarToString(this.raiz);

        } else {

            cadena = " Arbol vacío. ";
        }
        return cadena;

    }

    private String generarToString(NodoAVL nodo) {

        String cadena = "";

        if (nodo != null) {

            cadena = " P: " + nodo.getElem() + " HI:" + (nodo.getIzquierdo() != null ? nodo.getIzquierdo().getElem() : " null ") + " HD: "
                    + (nodo.getDerecho() != null ? nodo.getDerecho().getElem() : " null. ") + "\n";

            cadena = cadena + generarToString(nodo.getIzquierdo());

            cadena = cadena + generarToString(nodo.getDerecho());

        }

        return cadena;

    }

    public Lista listar() {
        Lista lista = new Lista();

        if (this.raiz != null) {
            listarArbol(this.raiz, lista);

        }

        return lista;

    }

    private void listarArbol(NodoAVL nodo, Lista lista) {

        if (nodo != null) {

            listarArbol(nodo.getIzquierdo(), lista);

            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            listarArbol(nodo.getDerecho(), lista);

        }

    }

    public Object maximoElem() {
        Object maximo = null;
        if (this.raiz != null) {
            maximo = maximoElemAux(this.raiz);

        }
        return maximo;

    }

    private Object maximoElemAux(NodoAVL nodo) {
        Object maximo = null;

        if (nodo != null) {

            if (nodo.getDerecho() != null) {

                maximo = maximoElemAux(nodo.getDerecho());

            } else {
                maximo = nodo.getElem();
            }

        }
        return maximo;

    }

    public Object minimoElem() {
        Object minimo = null;

        if (this.raiz != null) {

            minimo = buscarMinimo(this.raiz);

        }
        return minimo;

    }

    private Object buscarMinimo(NodoAVL nodo) {
        Object minimoElem = null;

        if (nodo != null) {

            if (nodo.getIzquierdo() != null) {

                minimoElem = buscarMinimo(nodo.getIzquierdo());

            } else {

                minimoElem = nodo.getElem();
            }

        }

        return minimoElem;

    }

    public Lista listarRango(Object minimo, Object maximo) {
        Lista lista = new Lista();

        if (this.raiz != null) { //Si el árbol es != null, busco el rango.

            listarRangoAux(this.raiz, minimo, maximo, lista);

        }
        return lista;

    }

    public void listarRangoAux(NodoAVL nodo, Object minimo, Object maximo, Lista lista) {
        int compararMinimo, compararMaximo;

        if (nodo != null) {

            compararMinimo = nodo.getElem().compareTo(minimo); //Preguntar el compareTo
            compararMaximo = nodo.getElem().compareTo(maximo);

            if (compararMinimo >= 0 && compararMaximo <= 0) {
                //Si compararMinimo > 0 quiere decir que el nodo es mayor a el elemento,
                //Si comparar maximo es <0 quiere decir que el nodo es menor al elemento.
                if (nodo.getIzquierdo() != null) {
                    listarRangoAux(nodo.getIzquierdo(), minimo, maximo, lista);
                    //Recorro primero el hijo izquierdo.

                }
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                //Lo inserto, y luego recorro el hijo derecho.
                if (nodo.getDerecho() != null) {
                    listarRangoAux(nodo.getDerecho(), minimo, maximo, lista);

                }

            } else if (compararMinimo < 0 && compararMaximo < 0) {
                //En el caso de el nodo sea menor a el minimo y maximo, debo recorrer el derecho si este != null. 
                if (nodo.getDerecho() != null) {
                    listarRangoAux(nodo.getDerecho(), minimo, maximo, lista);

                }

            } else if (compararMinimo > 0 && compararMaximo >= 0) {
                //En el caso de que el nodo sea mayor a los dos intervalos del rango,
                //Debo recorrer por el lado izquierdo.
                if (nodo.getIzquierdo() != null) {
                    listarRangoAux(nodo.getIzquierdo(), minimo, maximo, lista);

                }

            }

        }

    }

    public ArbolAVL clone() {
        ArbolAVL arbolNuevo = new ArbolAVL();

        if (this.raiz != null) {

            arbolNuevo.raiz = new NodoAVL(this.raiz.getElem(), null, null);

            cloneAux(this.raiz, arbolNuevo);
            arbolNuevo.raiz.recalcularAltura();

        }
        return arbolNuevo;

    }

    private void cloneAux(NodoAVL nodo, ArbolAVL arbol) {

        if (nodo != null) {

            if (nodo.getIzquierdo() != null) {
                //Uso el metodo insertar para clonar, así este va calculando su altura y balanceandose meidante el método.
                arbol.insertar(nodo.getIzquierdo().getElem());

                cloneAux(nodo.getIzquierdo(), arbol);

            }

            if (nodo.getDerecho() != null) {
                arbol.insertar(nodo.getDerecho().getElem());

                cloneAux(nodo.getDerecho(), arbol);

            }

        }

    }

    public boolean vacio() {

        return (this.raiz == null);
    }

    public void vaciar() {

        this.raiz = null;

    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
