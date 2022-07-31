package r1.Carrillo.RetoApi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    Conexion cn = new Conexion();
    Connection cxn;

    private String SQLQuery="";


    public ArrayList<Map<String, Object>> consultar_cuentas()  {
        CallableStatement statement=null;
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        ResultSet resultSet=null;
        try{
            cxn=cn.conectar();
            if(cxn==null)
                return null;
            SQLQuery="call Obtener_Cuentas()";
            statement=cxn.prepareCall(SQLQuery);
            // statement.execute();

            resultSet=statement.executeQuery();
            while (resultSet.next()){
                Map<String, Object> Mapa_JSON = new HashMap<>();
                Mapa_JSON.put("Id",resultSet.getString(1));
                Mapa_JSON.put("Nombre",resultSet.getString(2));
                Mapa_JSON.put("Numero de Cuenta",resultSet.getString(3));
                Mapa_JSON.put("Estado",resultSet.getString(4));
                Mapa_JSON.put("Saldo",resultSet.getString(5));

                data.add(Mapa_JSON);
            }

        }
        catch(SQLException SQLE){
            return null;

        }
        finally {
            if(statement!=null||cxn!=null)
            {
                try {
                    assert statement != null;
                    statement.close();
                    resultSet.close();
                    cxn.close();
                } catch (SQLException ex) {


                }
            }
        }

        return data;
    }


    public ArrayList<Map<String, Object>> consultar_cuenta_detalle(int id)  {
        CallableStatement statement=null;
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            cxn=cn.conectar();

            if(cxn==null)
                return null;
            SQLQuery="call Obtener_Cuenta(?);";
            statement=cxn.prepareCall(SQLQuery);
            statement.setString(1,Integer.toString(id));
            // statement.execute();

            resultSet=statement.executeQuery();
            while (resultSet.next()){
                Map<String, Object> Mapa_JSON = new HashMap<>();
                Mapa_JSON.put("Id Cuenta",resultSet.getString(1));
                Mapa_JSON.put("Nombre",resultSet.getString(2));
                Mapa_JSON.put("Numero de Cuenta",resultSet.getString(3));
                Mapa_JSON.put("Estado",resultSet.getString(4));
                Mapa_JSON.put("Saldo",resultSet.getString(5));

                data.add(Mapa_JSON);
            }

        }
        catch(SQLException SQLE){
            return null;

        }
        finally {
            if(statement!=null||cxn!=null)
            {
                try {
                    statement.close();
                    resultSet.close();
                    cxn.close();

                } catch (SQLException ex) {


                }
            }
        }

        return data;
    }

    public String Eliminar_Cuenta(String id)
    {
        CallableStatement statement=null;

        try{
            cxn=cn.conectar();

            if(cxn==null)
                return "No se pudo Eliminar la cuenta";
            SQLQuery="call Eliminar_Cuenta(?)";
            statement=cxn.prepareCall(SQLQuery);
            statement.setString(1,id);


            statement.executeQuery();


        }
        catch(SQLException SQLE){
            return "Ha ocurrido un error";
        }
        finally {
            if(statement!=null||cxn!=null)
            {
                try {
                    statement.close();
                    //resultSet.close();
                    cxn.close();

                } catch (SQLException ex) {
                    System.out.print(ex);

                }
            }
        }

        return "Cuenta Eliminada con exito";
    }


    public String Crear_Cuenta(String nombre,String cuenta,float saldo,int estado){


        String Nombre,Cuenta;
        float Saldo;
        int Estado;
        CallableStatement statement=null;

        try{
            Nombre=nombre;
            Cuenta=cuenta;
            Saldo=saldo;
            Estado=estado;
            cxn=cn.conectar();

            if((Estado<0||Estado>2)||(Saldo<0.00f))
                return"No se pudo crear la cuenta, Revisa que los parametros sean correctos";
            else if(cxn==null)
                return "No se pudo conectar con la base de datos";
            SQLQuery="call Crear_Cuenta(?,?,?,?)";
            statement=cxn.prepareCall(SQLQuery);
            statement.setString(1,Nombre);
            statement.setString(2,Cuenta);
            statement.setString(3,Integer.toString(Estado));
            statement.setString(4,Float.toString(Saldo));
            statement.executeQuery();

        }
        catch(SQLException ex){
            return "Hubo un error al crear la cuenta";
        }
        finally {
            if(statement!=null||cxn!=null)
            {
                try {
                    statement.close();
                    cxn.close();

                } catch (SQLException ex) {
                    System.out.print(ex);

                }
            }
        }

        return "Cuenta Creada con Exito";
    }

    public String ActualizarCuenta(int id,float saldo){
        CallableStatement statement=null;
        try{
            cxn=cn.conectar();
            if(id<1||saldo<0.00f)
                return "No se pudo actualizar la cuenta, revisa los parametros";
            else if(cxn==null)
                return "Ha ocurrido un error!";

            SQLQuery="call Actualizar_Saldo(?,?)";
            statement=cxn.prepareCall(SQLQuery);
            statement.setString(1,Integer.toString(id));
            statement.setString(2,Float.toString(saldo));
            statement.executeUpdate();


        }
        catch(SQLException SQLE){
            return "Ha ocurrido un error!"+SQLE;
        }
        finally {
            if(statement!=null)
            {
                try {
                    statement.close();
                    cxn.close();
                } catch (SQLException ex) {
                    System.out.print(ex);

                }
            }

        }

        return "Saldo Actualizado!";
    }




}
