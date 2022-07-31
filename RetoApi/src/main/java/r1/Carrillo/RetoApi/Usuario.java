package r1.Carrillo.RetoApi;

public class Usuario {

    private String Nombre=null;
    private String Cuenta;
    private float Saldo=0.00f;
    private int Estado=0;

    public String getNombre(){
        return Nombre;
    }

    public void setNombre(String Nombre){
        this.Nombre=Nombre;
    }

    public String getCuenta(){
        return Cuenta;
    }

    public void setCuenta(String Num_Cuenta){
        this.Cuenta=Num_Cuenta;
    }

    public float getSaldo(){
        return Saldo;
    }

    public void setSaldo(float Saldo){

        this.Saldo=Saldo;
    }

    public int getEstado(){
        return Estado;
    }

    public void setEstado(int Estado){
        this.Estado=Estado;
    }


}
