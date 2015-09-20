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
    String nombreRuta;
    NodoBus siguiente, anterior;
    ListaRutas rutas;
    
    
    public NodoBus(int idBus, Double horaInicio, Double horaFinal, String nombreRuta){
        this.idBus = idBus;
        this.horaFinal = horaFinal;
        this.horaInicio = horaInicio;
        this.nombreRuta = nombreRuta;
        this.rutas = new ListaRutas();
        this.siguiente = null;
        this.anterior = null;
    }
}
