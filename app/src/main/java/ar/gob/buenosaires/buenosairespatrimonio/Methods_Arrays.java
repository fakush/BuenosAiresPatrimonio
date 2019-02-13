package ar.gob.buenosaires.buenosairespatrimonio;

public class Methods_Arrays {
    private String Titulo ="";
    private String Descripcion ="";
    private String Icono ="";
    private String Activity ="";
    private String Anio ="";
    private String DirInternet="";
    private Integer Bool= 0;
    private String Direccion ="";
    private String Info="";
    private String VR="";
    private Integer VrType= 0;
    private Integer ZoomMax= 0;
    private Integer ZoomMin= 0;

    /*********** Set Methods ******************/

    public void setTitulo(String Titulo)
    {
        this.Titulo = Titulo;
    }
    public void setDescripcion(String Descripcion)
    {
        this.Descripcion = Descripcion;
    }
    public void setIcono(String Icono)
    {
        this.Icono = Icono;
    }
    public void setActivity(String Activity)
    {
        this.Activity = Activity;
    }
    public void setAnio(String Anio)
    {
        this.Anio = Anio;
    }
    public void setDirInternet(String DirInternet)
    {
        this.DirInternet = DirInternet;
    }
    public void setBool(Integer Bool) {this.Bool = Bool; }
    public void setDireccion(String Direccion)
    {
        this.Direccion = Direccion;
    }
    public void setInfo(String Info)
    {
        this.Info = Info;
    }
    public void setVR(String VR)
    {
        this.VR = VR;
    }
    public void setVrType(Integer VrType) {this.VrType = VrType; }
    public void setZoomMax(Integer ZoomMax) {this.ZoomMax = ZoomMax; }
    public void setZoomMin(Integer ZoomMin) {this.ZoomMin = ZoomMin; }

    /*********** Get Methods ****************/

    public String getTitulo()
    {
        return this.Titulo;
    }
    public String getDescripcion() {
        return this.Descripcion;
    }
    public String getIcono()
    {
        return this.Icono;
    }
    public String getActivity()
    {
        return this.Activity;
    }
    public String getAnio()
    {
        return this.Anio;
    }
    public String getDirInternet() {
        return this.DirInternet;
    }
    public Integer getBool() {
        return this.Bool;
    }
    public String getDireccion()
    {
        return this.Direccion;
    }
    public String getInfo() {
        return this.Info;
    }
    public String getVR() {
        return this.VR;
    }
    public Integer getVrType() {
        return this.VrType;
    }
    public Integer getZoomMax() { return this.ZoomMax; }
    public Integer getZoomMin() { return this.ZoomMin; }

}
