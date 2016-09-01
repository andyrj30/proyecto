package com.proyecto.controller;

import com.proyecto.entities.Docente;
import com.proyecto.controller.util.JsfUtil;
import com.proyecto.entities.Usuario;
import com.proyecto.model.DocenteFacadeLocal;
import com.proyecto.model.UsuarioFacadeLocal;
import com.proyecto.websocket.WSEndpoint;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@javax.faces.bean.ManagedBean(name = "docenteController")
@javax.faces.bean.SessionScoped
public class DocenteController implements Serializable {

    @EJB
    protected DocenteFacadeLocal ejbDocente;

    @EJB
    protected UsuarioFacadeLocal ejbUsuario;

    private Docente selected;
    protected List<Docente> listDocente;
    protected String defaultMsg = "Ha ocurrido un error en la transacción";

    public DocenteController() {
    }

    private DocenteFacadeLocal getFacade() {
        return ejbDocente;
    }

    public Docente getSelected() {
        return selected;
    }

    public void setSelected(Docente selected) {
        this.selected = selected;
    }

    public int count() {
        return getFacade().count();
    }

    public Docente prepareCreate() {
        selected = new Docente();
        return selected;
    }

    public void view() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ProyectoDistributivos-war/administrador/vista/docente");
    }

    public void create() {
        try {
            listDocente = getFacade().FindByDocente(selected.getCedula());
            if (listDocente == null || listDocente.size() < 1) {
                getFacade().create(selected);
                Usuario usuario;
                usuario = new Usuario();
                usuario.setDocente(selected);
                usuario.setUsuario(selected.getCedula());
                usuario.setContasena(selected.getCedula());
                usuario.setTipo("doc");
                ejbUsuario.create(usuario);
                JsfUtil.addSuccessMessage("Registro agregado correctamente");
                WSEndpoint.notificar("docente");
            } else if (listDocente.size() > 0) {
                JsfUtil.addErrorMessage("El docente ya existe");
            }
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void update() {
        try {
            getFacade().edit(selected);
            JsfUtil.addSuccessMessage("Datos editados");
            listDocente = null;
            WSEndpoint.notificar("docente");
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public void destroy() {
        try {
            int id = selected.getIddocente();
            List<Usuario> users;
            users = ejbUsuario.FindByDocente(selected);
            for (Usuario next : users) {
                ejbUsuario.remove(next);
            }
            getFacade().remove(selected);
            JsfUtil.addSuccessMessage("Registro eliminado correctamente");
            selected = null;
            listDocente = null;
            WSEndpoint.notificar("docente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, defaultMsg);
        }
    }

    public List<Docente> getItems() {
        listDocente = getFacade().findAll();
        return listDocente;
    }

    public Docente getDocente(Object id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Docente.class)
    public static class DocenteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DocenteController controller = (DocenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "docenteController");
            return controller.getDocente(getKey(value));
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
            if (object instanceof Docente) {
                Docente o = (Docente) object;
                return getStringKey(o.getCedula());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Docente.class.getName()});
                return null;
            }
        }

    }

}
