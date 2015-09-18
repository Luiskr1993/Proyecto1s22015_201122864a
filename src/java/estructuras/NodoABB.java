/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

public class NodoABB {
    
    int id;
    String nombre, apellido, password;
    int fe;
    NodoABB derecha;
    NodoABB izquierda;
    ListaFechas fechas;
    
    public NodoABB(int id, String nombre, String apellido, String password){
        
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.fe = 0;
        this.derecha = null;
        this.izquierda = null;
        fechas = new ListaFechas();
        
    }
    
}
