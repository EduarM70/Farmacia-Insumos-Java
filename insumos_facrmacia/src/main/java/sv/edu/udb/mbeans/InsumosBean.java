package sv.edu.udb.mbeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import sv.edu.udb.models.Insumo;
import sv.edu.udb.services.InsumosService;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Named
@SessionScoped
public class InsumosBean implements Serializable {
    private InsumosService insumosService = new InsumosService();
    private ArrayList<Insumo> insumos;
    private Insumo insumo = new Insumo();

    private Boolean isUpdating = false;
    private String message;

    // Obtener todos los datos de insumos
    @PostConstruct
    public void init(){
        try {
            insumos = insumosService.getAllInsumos();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String crearInsumo(){
        try{
            if (insumo.getNombre() == null || insumo.getNombre().isEmpty()){
                // Agregar mensaje de error cuando un dato sea incorrecto
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es requerido", null));
                return null;
            }

            if (insumo.getDescripcion() == null || insumo.getDescripcion().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes escribir una descripción del producto", null));
                return null;
            }

            if (insumo.getCantidad() <= 0){
                // Agregar mensaje de error cuando un dato sea incorrecto
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cantidad debe ser mayor que 0", null));
                return null;
            }

            if (insumo.getPrecio() <= 0){
                // Agregar mensaje de error cuando un dato sea incorrecto
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio debe ser mayor que 0", null));
                return null;
            }

            if (insumo.getCategoriaId() <= 0){
                // Agregar mensaje de error cuando un dato sea incorrecto
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "la categoria es inválida", null));
                return null;
            }

            // Convertir fecha a tipo date y comprar si es mayor que la fecha actual
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            if (insumo.getFecha_vencimiento() != null){
                // convertir fecha
                LocalDate fechaConvertida = LocalDate.parse(insumo.getFecha_vencimiento(), formatter);
                if (!fechaConvertida.isAfter(fechaActual)){
                    // Agregar mensaje de error cuando el dato sea incorrecto
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "la fecha no es inválida", null));
                    return null;
                }
            }

            if (!isUpdating){
                insumosService.createInsumo(this.insumo);
            }else {
                insumosService.updateInsumo(insumo.getId(), insumo);
            }

            return this.regresarLista();
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el insumo: " + e.getMessage(), null));
            return null;
        }
    }

    public void eliminarInsumo(int id) {
        try {
            insumosService.deleteInsumo(id);
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .put("successMessage", "Inusmo eliminado correctamente");
            init();
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando insumo" + e.getMessage(), null));
        }
    }

    public String goFormCreate(){
        isUpdating = false;
        insumo = new Insumo();
        return "insumosForm?faces-redirect=true";
    }

    public String goFormEditing(int id){
        try {
            isUpdating = true;
            insumo = insumosService.getOneInsumo(id);
            return "insumosForm?faces-redirect=true";
        }catch (IOException | InterruptedException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error actualzindo insumo", null));
            return null;
        }
    }

    public String regresarLista(){
        init();
        return "insumosList?faces-redirect=true";
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList<Insumo> insumos) {
        this.insumos = insumos;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
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
