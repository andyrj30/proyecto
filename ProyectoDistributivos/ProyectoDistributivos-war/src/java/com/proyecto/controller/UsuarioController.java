package com.proyecto.controller;

import com.proyecto.entities.Usuario;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.UsuarioFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;

@javax.faces.bean.ManagedBean(name = "usuarioController")
@javax.faces.bean.SessionScoped
public class UsuarioController extends AbstractController implements Serializable {

    private Usuario selected;
    private Usuario activo;

    public UsuarioController() {
        this.activo = new Usuario();
    }

    private UsuarioFacade getFacade() {
        return ejbUsuario;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public Usuario getActivo() {
        return activo;
    }

    public void setActivo(Usuario activo) {
        this.activo = activo;
    }

    public int count() {
        return getFacade().count();
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        return selected;
    }

    public void create() {
        try {
            getFacade().create(selected);
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            listUsuario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listUsuario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listUsuario = null;
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Usuario> getItems() {
        if (listUsuario == null) {
            listUsuario = getFacade().findAll();
        }
        return listUsuario;
    }

    public Usuario getUsuario(Object id) {
        return getFacade().find(id);
    }

    public String getUsuario(String username, String password) {
        List<Usuario> list = getItems();
        String tipo = "";
        for (Usuario next : list) {
            if (next.getUsuario().equals(username) && next.getContasena().equals(password)) {
                tipo = tipo + next.getTipo();
            }
        }
        return tipo;
    }

    public void login() throws IOException {
        List<Usuario> list = getItems();
        String tipo = "";
        for (Usuario next : list) {
            if (next.getUsuario().equals(activo.getUsuario()) && next.getContasena().equals(activo.getContasena())) {
                tipo = tipo + next.getTipo();
            }
        }
        if (tipo.equals("adm")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/administrador");
        }
        if (tipo.equals("coo")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/coordinador");
        }
        if (tipo.equals("doc")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/docentes");
        }
        JsfUtil.addErrorMessage("Usuario o contraseña incorrecta" + tipo);
    }

}
