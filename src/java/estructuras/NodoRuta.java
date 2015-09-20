/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author Luiskr
 */
public class NodoRuta {
    
    int idEstacion;
    NodoRuta siguiente, anterior;
    
    public NodoRuta(int idEstacion){
        this.idEstacion = idEstacion;
        this.siguiente = null;
        this.anterior = null;
    }
    
}
