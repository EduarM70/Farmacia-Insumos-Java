package sv.edu.udb.mbeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import sv.edu.udb.models.Categoria;
import sv.edu.udb.services.CategoriaService;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Named
@SessionScoped
public class CategoriasBean implements Serializable {
    private CategoriaService categoriaService = new CategoriaService();
    private ArrayList<Categoria> categorias = new ArrayList();
    private Categoria categoria = new Categoria();

    private Boolean isUpdating = false;
    private String message;

    @PostConstruct
    public void init(){
        try {
            categorias = categoriaService.getAllCategorias();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String crearCategoria(){
        try{
            if (categoria.getNombre() == null || categoria.getNombre().isEmpty()){
                // Agregar mensaje de error cuando un dato sea incorrecto
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es requerido", null));
                return null;
            }

            if (categoria.getDescripcion() == null || categoria.getDescripcion().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes escribir una descripción para la categoria", null));
                return null;
            }

            if (!isUpdating){
                categoriaService.createCategoria(categoria);
            }else {
                categoriaService.updateCategoria(categoria.getId(), categoria);
            }

            return this.regresarLista();
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al editar la categoria: " + e.getMessage(), null));
            return null;
        }
    }


    public void deleteCategoria(int id) {
        try {
            categoriaService.deleteCategoria(id);
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .put("successMessage", "Categoria eliminada correctamente");
            init();
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando insumo" + e.getMessage(), null));
        }
    }


    public String goFormCreate(){
        isUpdating = false;
        categoria = new Categoria(); // inicializar el objeto de categorias en null, para limpiar los campos del formulario al comento de crear
        return "categoriasForm?faces-redirect=true";
    }

    public String goFormEditing(int id){
        try {
            isUpdating = true;
            categoria = categoriaService.getOneCategoria(id);
            return "categoriasForm?faces-redirect=true";
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error actualzindo categoria", null));
            return null;
        }
    }

    public String regresarLista(){
        init();
        return "categoriasList?faces-redirect=true";
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getUpdating() {
        return isUpdating;
    }

    public void setUpdating(Boolean updating) {
        isUpdating = updating;
    }
}

