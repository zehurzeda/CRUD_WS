package br.com.api.resource;

import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.api.domain.Lembrete;
import br.com.api.domain.LembreteRepository;
import br.com.api.domain.Pagina;
import br.com.api.exception.ApiException;
import br.com.api.orm.DaoGenerico;

@Path("/lembrete")
public class LembreteResource {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private static DaoGenerico<Lembrete> dao = new DaoGenerico<>();

	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("page") String page) throws ApiException {
		LembreteRepository repository = new LembreteRepository();

		if ((page == null) || (page.isEmpty())) {
			return Response.ok(new Pagina(repository.getAll())).build();
		}
		if (page == "0") {
			throw new ApiException(400, "A página não pode ser 0. Informe um valor maior ou igual a 1.");
		}

		if (!Pattern.matches("^\\d+", page)) {
			throw new ApiException(400, "Um valor inválido foi fornecido para um ou mais parâmetros.");
		}
		
		return Response.ok(new Pagina(repository.getByRange(Integer.parseInt(page)))).build();
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response get(@PathParam("id") Long id) throws ApiException {
	         
	    if(id == 0){
	        throw new ApiException(400, "O id deve ser maior ou igual a 1.");
	    }
	     
	    Lembrete lembrete;
	     
	    lembrete = dao.findById(Lembrete.class, id);
	     
	    if(lembrete == null){
	        throw new ApiException(204, "O lembrete especificado não existe.");
	    }
	 
	    return Response.ok(lembrete).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response post(Lembrete lembrete) throws ApiException {
	 
	    if(lembrete.getDescricao().isEmpty() || lembrete.getTitulo().isEmpty()){
	        throw new ApiException(400, 
	                "Um ou mais parâmetros obrigatórios não foram informados.");
	    }
	     
	    dao.saveOrUpdate(lembrete);
	 
	    return Response.ok(lembrete).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response put(Lembrete lembrete, @PathParam("id") Long id) throws ApiException {
	     
	    if(id <= 0){
	        throw new ApiException(400, 
	            "O id do lembrete não pode ser menor ou igual a zero.");
	    }
	     
	    lembrete.setId(id);
	     
	    dao.saveOrUpdate(lembrete);
	 
	    return Response.ok(lembrete).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response delete(@PathParam("id") Long id) throws ApiException {
	     
	    Lembrete lembrete = new Lembrete();
	    lembrete = dao.findById(Lembrete.class, id);
	    dao.remove(Lembrete.class, lembrete);
	    
	    return Response.ok(lembrete).build();
	}

}