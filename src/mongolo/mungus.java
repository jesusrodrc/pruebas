package mongolo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



@Path("/tratamientos")
@Produces("text/html")
public class mungus {
	@Path("/inicio")
	@GET
	public String bienvenida(){
		return "<a href='../tratamientos/nuevo'>Nuevo paciente</a></br><a href='../tratamientos/altas'>Altas</a></br><a href='../tratamientos/consulta'>Consulta de datos</a>";
	}	
	@Path("/nuevo")
	@GET
	public String nuevo(){
		return "Nuevos pacientes</br> <form action='../proceso/nuevo' method='post'></br> DNI <input type='text' name='dni'><br>Nombre <input type='text' name='nombre'><br>Apellidos: <input type='text' name='apellidos'><br>Tratamientos: <input type='text' name='tratamientos'><br>Pruebas: <input type='text' name='pruebas'><br><input type='submit' value='Enviar'></form>";
	}	
	@Path("/altas")
	@GET
	public String altas(){
		return "Altas</br></br> Por favor, introduzca el DNI del paciente al que se le dará el alta </br><form action='../proceso/altas' method='post'>DNI: <input type='text' name='dni'><input type='submit' value='Enviar'>";
	}	
	@Path("/consulta")
	@GET
	public String consulta(){
		return "Consulta de datos</br></br> Introduzca el t&eacute;rmino a consultar: <form action='./proceso/consulta' method='post'><input type='text' name='campo'><input type='submit' value='Enviar'>";
	}	
}
