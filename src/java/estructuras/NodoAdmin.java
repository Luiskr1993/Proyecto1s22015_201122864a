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
public class NodoAdmin {
    
    String correo, password;
    
    NodoAdmin izquierda, derecha;
    int fe;
    
    public NodoAdmin(String correo, String password){
        this.correo = correo;
        this.password = password;
        this.izquierda = null;
        this.derecha = null;
        this.fe = 0;
    }
}
