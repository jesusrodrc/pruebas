package mongolo;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Path("/proceso")
public class proceso {
	@Path("/nuevo")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String nuevo(@FormParam("dni") String dni, @FormParam("nombre") String nombre, @FormParam("apellidos") String apellidos, @FormParam("tratamientos") String tratamientos, @FormParam("pruebas") String pruebas){
		try{
			MongoClient mungus = new MongoClient("localhost");
			DB db = mungus.getDB( "tratamientos" );
			DBCollection coleccion = db.getCollection("pacientes");
			BasicDBObject doc = new BasicDBObject("_id",dni).
									append("nombre", nombre).
									append("apellidos", apellidos).
									append("tratamientos", tratamientos).
									append("pruebas", pruebas);
			coleccion.insert(doc);
			return "Datos insertados en la base de datos";
		} catch (UnknownHostException e){
			return "Error al acceder a la base de datos";
		}
	}	
	@Path("/altas")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String altas(@FormParam("dni") String dni){
		try{
			MongoClient mungus = new MongoClient("localhost");
			DB db = mungus.getDB( "tratamientos" );
			DBCollection coleccion = db.getCollection("pacientes");
			BasicDBObject identificador = new BasicDBObject("_id", dni);
			DBCursor cursor = coleccion.find(identificador);
			try{
				while(cursor.hasNext()){
					coleccion.remove(cursor.next());
				}
			} finally {
				cursor.close();
			}	
			return "Se ha eliminado el paciente de la base de datos";
		} catch (UnknownHostException e){
			return "Error al acceder a la base de datos: no se ha encontrado el servidor";
		}
	}	
	@Path("/consulta")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String consulta(@FormParam("campo") String consulta){
		try{
			MongoClient mungus = new MongoClient("localhost");
			DB db = mungus.getDB( "tratamientos" );
			DBCollection coleccion = db.getCollection("pacientes");
			BasicDBObject identificador = new BasicDBObject("_id", consulta);
			DBCursor cursor = coleccion.find(identificador);
			try{
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}
			BasicDBObject nombre = new BasicDBObject("nombre", consulta);
			cursor= coleccion.find(nombre);
			try{
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}	
			BasicDBObject apellidos= new BasicDBObject("apellidos", consulta);
			cursor = coleccion.find(apellidos);
			try{
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}	
			BasicDBObject tratamientos= new BasicDBObject("tratamientos", consulta);
			cursor = coleccion.find(tratamientos);
			try{
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}	
			BasicDBObject pruebas = new BasicDBObject("pruebas", consulta);
			cursor = coleccion.find(pruebas);
			try{
				while(cursor.hasNext()){
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}	
			return "Fin de los resultados";
		} catch (UnknownHostException e){
			return "Error al acceder a la base de datos: no se ha encontrado el servidor";
		}
	}	
}
