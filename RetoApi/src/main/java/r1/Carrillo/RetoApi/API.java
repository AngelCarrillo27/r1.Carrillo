package r1.Carrillo.RetoApi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
@RestController
public class API {
    DataBase db = new DataBase();
    @GetMapping("/API/v1/accounts")
    public @ResponseBody ResponseEntity Obtener_Cuentas() {

        ArrayList<Map<String, Object>> lista;
        lista=db.consultar_cuentas();
        if(lista==null)
            return ResponseEntity.ok("No hay cuentas registradas");
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value="/API/v1/accounts/{id}")
    public @ResponseBody ResponseEntity Obtener_Cuenta(@PathVariable String id){
        ArrayList<Map<String, Object>> lista;
        int Id=Integer.parseInt(id);
        lista=db.consultar_cuenta_detalle(Id);
        if(lista==null)
            return ResponseEntity.ok("No Se encontro una cuenta con el ID"+Id);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(
            value = "/API/v1/accounts",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String Crear_Cuenta(@RequestBody Usuario Usuario){
        return db.Crear_Cuenta(Usuario.getNombre(),Usuario.getCuenta(),Usuario.getSaldo(),Usuario.getEstado());
    }

    @PutMapping(
            value = "/API/v1/accounts",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String Actualizar_Saldo(@RequestBody ActualizarCuenta AC){
        int Id=AC.getid();
        float saldo=AC.getSaldo();
        return db.ActualizarCuenta(Id,saldo);
    }


    @DeleteMapping(value ="/API/v1/accounts/{id}")
    public String Borrar_Cuenta(@PathVariable String id){

        return db.Eliminar_Cuenta(id);

    }


}

