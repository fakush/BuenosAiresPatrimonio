package ar.gob.buenosaires.buenosairespatrimonio;

public class Method_Bares {
    private String Nombre ="";
    private String Direccion ="";
    private String Barrio ="";
    private String Telefono ="";
    private String Descripcion ="";
    private String Foto ="";
    private String Coordenadas ="";

    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setDireccion(String Direccion) {this.Direccion = Direccion;}
    public void setBarrio(String Barrio) {this.Barrio = Barrio;}
    public void setTelefono(String Telefono) {this.Telefono = Telefono;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setFoto(String Foto) {this.Foto = Foto;}
    public void setCoordenadas(String Coordenadas) {this.Coordenadas = Coordenadas;}

    public String getNombre() {return this.Nombre;}
    public String getDireccion() {return this.Direccion;}
    public String getBarrio() {return this.Barrio;}
    public String getTelefono() {return this.Telefono;}
    public String getDescripcion() {return this.Descripcion;}
    public String getFoto() {return this.Foto;}
    public String getCoordenadas() {return this.Coordenadas;}

}
