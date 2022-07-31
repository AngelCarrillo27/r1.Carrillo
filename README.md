# r1.Carrillo
 API Rest usando Spring 
 
 Herramientas Utilizadas
INELLIJ IDEA
MYSQL WORKBENCH
POSTMAN
 
 Responsabilidades de la API completadas
 
 GET de todas las cuentas o con un id especifico 
 al ejecutar http://localhost:8080/API/v1/accounts para obtener todas las cuentas 
 ó http://localhost:8080/API/v1/accounts/{id} para obtener una cuenta especifica
 
 Tendremos un output JSON como este
     {
        "Nombre": "Angel Carrillo",
        "Saldo": "2000.00",
        "Numero de Cuenta": "4-4334-0-443-23-1",
        "Id": "1",
        "Estado": "Inactiva"
    }
    
POST para crear cuenta, aqui les adjunto un ejemplo para usarlo http://localhost:8080/API/v1/accounts

BodyJSON
{
    "nombre":"Manuel Salvador",
    "cuenta":"8-964-868-5455",
    "saldo":"15.56",
    "estado":"1"
}
estado 1= Cuenta activada 2= Cuenta desactivada
Se valida que no hayan numeros de cuenta repetidos en la base de datos, que el saldo no sea negativo y que el estado no sea menor a 0 ó mayor a 2

PUT para actualizar el saldo  http://localhost:8080/API/v1/accounts
BodyJSON
{
    "id":"2",
    "saldo":"800"
}

Se verifica que el saldo no sea negativo y que exista una cuenta con el ID colocado


DELETE http://localhost:8080/API/v1/accounts/{id}
como se solicitó en el documento no se utiliza la sentencia delete de la base de datos solo se cambia el estado de la cuenta a 2 para que esté desactivada
Se notifica si no se pudo eliminar la cuenta

