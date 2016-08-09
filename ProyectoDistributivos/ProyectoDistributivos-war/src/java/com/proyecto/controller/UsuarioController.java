package com.proyecto.controller;

import com.proyecto.entities.Usuario;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.controller.util.JsfUtil.PersistAction;
import com.proyecto.model.UsuarioFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private com.proyecto.model.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;
    private Usuario activo;

    public UsuarioController() {
        this.activo = new Usuario();
    }

    public Usuario getSelected() {
        return selected;
    }

    public Usuario getActivo() {
        return activo;
    }

    public int count() {
        return ejbFacade.count();
    }

    public void setActivo(Usuario activo) {
        this.activo = activo;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }


    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != null) {
                    switch (persistAction) {
                        case DELETE:
                            getFacade().remove(selected);
                            break;
                        case CREATE:
                            getFacade().create(selected);
                            break;
                        default:
                            getFacade().edit(selected);
                            break;
                    }
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usuario getUsuario(java.lang.String id) {
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

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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
        JsfUtil.addErrorMessage("Usuario o contraseña incorrecta"+tipo);
    }
}
