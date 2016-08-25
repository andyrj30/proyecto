package com.proyecto.controller;

import com.proyecto.entities.Usuario;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.model.UsuarioFacadeLocal;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "usuarioController")
@javax.faces.bean.SessionScoped
public class UsuarioController extends AbstractController implements Serializable {

    private Usuario selected;
    private Usuario activo;

    public UsuarioController() {
        this.activo = new Usuario();
    }

    private UsuarioFacadeLocal getFacade() {
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
        listUsuario = getFacade().findAll();
        return listUsuario;
    }

    public Usuario getUsuario(Object id) {
        return getFacade().find(id);
    }

    public void login() throws IOException {
        List<Usuario> list = getFacade().FindByUsuarioContasena(activo.getUsuario(), activo.getContasena());
        if (list != null && list.size() == 1) {
            String tipo = list.get(0).getTipo();
            if (tipo.equals("adm")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/administrador");
            }
            if (tipo.equals("coo")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/coordinador");
            }
            if (tipo.equals("doc")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/docentes");
            } else {
                JsfUtil.addErrorMessage("Usuario o contraseña incorrecta");
            }
            activo = list.get(0);
        } else {
            JsfUtil.addErrorMessage("Usuario o contraseña incorrecta");
        }
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdusuario().toString());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}
