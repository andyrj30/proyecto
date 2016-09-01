/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.websocket;

import com.proyecto.controller.util.JsfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Andy
 */
@ServerEndpoint("/endpoint")
public class WSEndpoint {

    static List<Session> conexiones = new ArrayList<>();

    @OnOpen
    public void iniciaSesion(Session session) {
        conexiones.add(session); //Simplemente, lo agregamos a la lista

    }

    @OnClose
    public void finConexion(Session session) {
        if (conexiones.contains(session)) { // se averigua si está en la colección
            try {
                session.close(); //se cierra la conexión
                conexiones.remove(session); // se retira de la lista
            } catch (IOException ex) {
                JsfUtil.addErrorMessage(ex, "error");
            }
        }
    }

    public static void notificar(String mensaje) {
        for (Session sesion : conexiones) { //recorro toda la lista de conectados
            RemoteEndpoint.Basic remote = sesion.getBasicRemote(); //tomo la conexion remota con el cliente...
            try {
                remote.sendText(mensaje); //... y envío el mensajue
            } catch (IOException ex) {
                JsfUtil.addErrorMessage(ex, "error");
            }
        }

    }

    @OnMessage
    public void onMessage(String mensaje, Session sesion) {
        JsfUtil.addSuccessMessage(mensaje);
    }

    @OnError
    public void onError(Throwable t) {
    }

}
