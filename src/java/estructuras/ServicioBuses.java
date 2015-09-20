/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Luiskr
 */
@WebService(serviceName = "ServicioBuses")

public class ServicioBuses {

    /**
     * This is a sample web service operation
     */
    ArbolABB arbolChoferes;
    ArbolAdmin arbolAdministradores;
    ArbolEstacionClave arbolEstacionClave;
    ArbolEstacionGeneral arbolEstacionGeneral;
    ListadoBuses busesTotales;

    public ServicioBuses() {
        this.arbolAdministradores = new ArbolAdmin();
        this.arbolChoferes = new ArbolABB();
        this.arbolEstacionClave = new ArbolEstacionClave();
        this.arbolEstacionGeneral = new ArbolEstacionGeneral();
        this.busesTotales = new ListadoBuses();
    }

    @WebMethod(operationName = "Volumen")
    public Double Volumen(@WebParam(name = "radio") Double radio) {

        return ((4.0 / 3.0) * (Math.PI) * (Math.pow(radio, 3)));

    }

    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "tipoUsuario") String tipoUsuario) {

        boolean encontrado;
        String claveEncontrada = "";

        if (tipoUsuario.equals("Administrador")) {
            encontrado = arbolAdministradores.busca(user);
            if (encontrado == true) {
                /*
                 claveEncontrada = arbolAdministradores.busca2(user);
                 if(claveEncontrada.equals(password)){
                 return true;
                 }
                 else{
                 return false;
                 }*/

                return true;
            } else {
                return false;
            }

        } else if (tipoUsuario.equals("Chofer")) {
            int id = Integer.parseInt(user);
            encontrado = arbolChoferes.busca(id);
            if (encontrado == true) {
                /*
                 claveEncontrada = arbolChoferes.busca2(id);
                 if (claveEncontrada.equals(password)) {
                 return true;
                 } else {
                 return false;
                 }*/

                return true;
            } else {
                return false;
            }

        } else if (tipoUsuario.equals("EstacionClave")) {
            int id = Integer.parseInt(user);
            encontrado = arbolEstacionClave.busca(id);
            if (encontrado == true) {
                /*
                 claveEncontrada = arbolAdministradores.busca2(user);
                 if(claveEncontrada.equals(password)){
                 return true;
                 }
                 else{
                 return false;
                 }*/

                return true;
            } else {
                return false;
            }

        } else if (tipoUsuario.equals("EstacionGeneral")) {
            int id = Integer.parseInt(user);
            encontrado = arbolEstacionGeneral.busca(id);
            if (encontrado == true) {
                /*
                 claveEncontrada = arbolAdministradores.busca2(user);
                 if(claveEncontrada.equals(password)){
                 return true;
                 }
                 else{
                 return false;
                 }*/

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    @WebMethod(operationName = "crearChofer")
    public String crearChofer(@WebParam(name = "idChofer") int idChofer, @WebParam(name = "nombreChofer") String nombreChofer, @WebParam(name = "apellidoChofer") String apellidoChofer, @WebParam(name = "password") String password) {

        if (!nombreChofer.equals("") && !apellidoChofer.equals("") && !password.equals("")) {
            arbolChoferes.insertarElemento(idChofer, nombreChofer, apellidoChofer, password);
            arbolChoferes.graficar();
            return "ID: " + idChofer + " Chofer: " + nombreChofer + " creado con exito";
        } else {
            return "Debe llenar todos los campos";
        }

    }

    @WebMethod(operationName = "crearAdmin")
    public String crearAdmin(@WebParam(name = "correo") String correo, @WebParam(name = "password") String password) {

        if (!correo.equals("")) {
            arbolAdministradores.insertarElemento(correo, password);
            arbolAdministradores.graficar();
            return "Admin: " + correo + " insertado con exito";
        } else {
            return "ingrese valores aceptados";
        }

    }

    @WebMethod(operationName = "crearEstacionClave")
    public String crearEstacionClave(@WebParam(name = "idEstacion") int idEstacion, @WebParam(name = "nombreEstacion") String nombreEstacion, @WebParam(name = "password") String password, @WebParam(name = "cantidadPersonas") int cantidadPersonas) {

        if (!nombreEstacion.equals("") && !password.equals("")) {
            arbolEstacionClave.insertarElemento(idEstacion, nombreEstacion, password, cantidadPersonas);
            arbolEstacionClave.graficar();
            return "ID: " + idEstacion + " Nombre: " + nombreEstacion + " creada con exito";
        } else {
            return "debe llenar todos los campos";
        }

    }

    @WebMethod(operationName = "crearEstacionGeneral")
    public String crearEstacionGeneral(@WebParam(name = "idEstacionG") int idEstacion, @WebParam(name = "nombreEstacionG") String nombreEstacion, @WebParam(name = "passwordG") String password, @WebParam(name = "cantidadPersonasG") int cantidadPersonasG) {

        if (!nombreEstacion.equals("") && !password.equals("")) {
            arbolEstacionGeneral.insertarElemento(idEstacion, nombreEstacion, password, cantidadPersonasG);
            arbolEstacionGeneral.graficar();
            return "ID: " + idEstacion + " Nombre: " + nombreEstacion + " creada con exito";
        } else {
            return "debe llenar todos los campos";
        }
    }

    @WebMethod(operationName = "crearBus")
    public String crearBus(@WebParam(name = "idBus00") int idBus, @WebParam(name = "horaInicio") Double horaInicio, @WebParam(name = "horaFinal") Double horaFinal, @WebParam(name = "nombreRuta") String nombreRuta) {

        busesTotales.insertar(idBus, horaInicio, horaFinal, nombreRuta);
        busesTotales.graficar();
//busesTotales.imprimir();
        return "Bus: " + idBus + " insertado con exito";

    }

    @WebMethod(operationName = "asignarBus")
    public String asignarBus(@WebParam(name = "idBus") int idBus, @WebParam(name = "idChofer") int idChofer, @WebParam(name = "fecha") int fecha) {

        boolean choferEncontrado;
        NodoBus busEncontrado;

        busEncontrado = busesTotales.buscarBus(idBus);

        choferEncontrado = arbolChoferes.insertarBus(idChofer, idBus, fecha, busEncontrado.horaInicio, busEncontrado.horaFinal, busEncontrado.nombreRuta);

        if (choferEncontrado == true) {
            return "Bus asignado con exito";
        } else {
            return ("No se asigno correctamente");
        }
    }

    @WebMethod(operationName = "grafica")
    public String grafica(@WebParam(name = "tipoGrafica") String tipoGrafica) {

        if (tipoGrafica.equals("Choferes")) {
            arbolChoferes.cadena = "";
            return arbolChoferes.inOrden(arbolChoferes.raiz);
        } else if (tipoGrafica.equals("Administradores")) {
            arbolAdministradores.cadena = "";
            return arbolAdministradores.inOrden(arbolAdministradores.raiz);
        } else if (tipoGrafica.equals("Buses")) {

            return busesTotales.imprimir();
        } else if (tipoGrafica.equals("Estacion Clave")) {
            arbolEstacionClave.cadena = "";
            return arbolEstacionClave.inOrden(arbolEstacionClave.raiz);
        } else if (tipoGrafica.equals("Estacion General")) {
            arbolEstacionGeneral.cadena = "";
            return arbolEstacionGeneral.inOrden(arbolEstacionGeneral.raiz);
        } else {
            return "no es una opcion";
        }

    }

    @WebMethod(operationName = "busesAsignados")
    public String busesAsignados(@WebParam(name = "idChofer2") int idChofer, @WebParam(name = "fecha2") int fecha) {

        NodoABB chofer;

        chofer = arbolChoferes.buscar(arbolChoferes.raiz, idChofer);

        if (chofer == null) {
            System.out.println("no existe el chofer consultado");
        } else {
            NodoFecha fecha1;
            fecha1 = chofer.fechas.buscarFecha(fecha);

            if (fecha1 == null) {
                return ("la fecha buscada no se ha encontrado en la agenda");
            } else {
                return fecha1.lista.imprimir();
            }
        }
        return "vacio";

    }

    @WebMethod(operationName = "verHorarios")
    public String verHorarios(@WebParam(name = "idBus") int idBus) {

        NodoBus encontrado;

        encontrado = busesTotales.buscarBus(idBus);

        if (encontrado == null) {
            return "No se encuentra el bus deseado";
        } else {

            return "Ruta: " + encontrado.nombreRuta + " Inicio: " + encontrado.horaInicio + "Finaliza: " + encontrado.horaFinal + "||";
        }

    }

    @WebMethod(operationName = "asignarRuta")
    public String asignarRuta(@WebParam(name = "idBus") int idBus, @WebParam(name = "estacion1") int estacion1) {

        NodoBus busEncontrado;

        busEncontrado = busesTotales.buscarBus(idBus);

        if (busEncontrado != null) {

            busEncontrado.rutas.insertar(estacion1);
            busEncontrado.rutas.graficar();
            return busEncontrado.rutas.imprimir();
        }

        return "vacio";

    }

    @WebMethod(operationName = "fechasAsignadas")
    public String fechasAsignadas(@WebParam(name = "idChofer3") int idChofer) {

        NodoABB chofer;

        chofer = arbolChoferes.buscar(arbolChoferes.raiz, idChofer);

        if (chofer == null) {
            System.out.println("no se encontro el chofer");
        } else {
            NodoFecha fecha;

            return chofer.fechas.imprimir();
        }
        return "vacio";

    }

    @WebMethod(operationName = "devuelvePersonasEstacionGeneral")
    public int devuelvePersonasEstacionGeneral(@WebParam(name = "idEstacion") int idEstacion) {

        int encontrado;

        encontrado = arbolEstacionClave.busca2(idEstacion);

        return encontrado;

    }

    @WebMethod(operationName = "pedirBus")
    public String pedirBus(@WebParam(name = "idEstacion") int idEstacion) {

        NodoBus temp;
        NodoRuta temp2;

        temp = busesTotales.primero;

        for (int i = 1; i <= busesTotales.contadorBuses; i++) {

            temp2 = temp.rutas.buscarRuta(idEstacion);

            if (temp2 == null) {
                temp = temp.siguiente;
            } else {
                return "ID. Bus Asignado: " + temp.idBus + " Hora Llegada: " + temp.horaInicio;
            }
        }

        return "No hay bus asignado para esta estacion";

    }

    @WebMethod(operationName = "sacarPersonas")
    public int sacarPersonas(@WebParam(name = "idEstacion") int idEstacion, @WebParam(name = "salientes") int salientes) {

        int cantidadPersonas;
        NodoBus temp;
        NodoRuta temp2;

        temp = busesTotales.primero;
        cantidadPersonas = arbolEstacionClave.buscaPersonas(idEstacion, salientes);

        for (int i = 1; i <= busesTotales.contadorBuses; i++) {

            temp2 = temp.rutas.buscarRuta(idEstacion);

            if (temp2 == null) {
                temp = temp.siguiente;
            } else {
                temp.rutas.eliminarNodo(idEstacion);
                temp.rutas.graficar();
                return cantidadPersonas;
            }
        }

        return cantidadPersonas;

    }

    @WebMethod(operationName = "cargar")
    public String cargar(@WebParam(name = "areaTexto") String areaTexto) {

        int contadorFilas = 0;
        int idBus = 0;

        for (int i = 1; i <= areaTexto.length(); i++) {
            String[] filas = areaTexto.split("\\n");
            for (int a = 1; a <= filas.length; a++) {
                String[] palabras = filas[i].split(",");
                contadorFilas++;
            }

        }

        return "contador Palabras: " + contadorFilas;

    }

}
