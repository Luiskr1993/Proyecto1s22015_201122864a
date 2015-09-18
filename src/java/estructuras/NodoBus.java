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
public class NodoBus {
    int idBus;
    Double horaInicio, horaFinal;
    NodoBus siguiente, anterior;
    
    public NodoBus(int idBus){
        this.idBus = idBus;
        this.siguiente = null;
        this.anterior = null;
    }
}
