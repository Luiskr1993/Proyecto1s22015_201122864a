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
public class NodoFecha {
    
    int idDia;
    Double horaInicio, horaFinal;
    
    ListaBuses lista;
    NodoFecha siguiente, anterior;
    
    public NodoFecha(int dia){
        this.idDia = dia;
        
        this.siguiente = null;
        this.anterior = null;
        lista = new ListaBuses();
    }
}
