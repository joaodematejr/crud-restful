package br.com.joao.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.joao.dao.NotaDAO;
import br.com.joao.entity.Nota;

@Path("/notas")
public class NotasService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private NotaDAO notaDAO;

	@PostConstruct
	private void init() {
		notaDAO = new NotaDAO();
	}

	// ADICIONAR
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNota(Nota nota) {
		String msg = "";
		System.out.println(nota.getTitulo());
		try {
			int idGerado = notaDAO.addNota(nota);

			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao add a nota!";
			e.printStackTrace();
		}

		return msg;
	}

	// LISTAR
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Nota> listarNotas() {
		List<Nota> lista = null;
		try {
			lista = notaDAO.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	// BUSCAR POR ID
	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Nota buscarPorId(@PathParam("id") int id) {
		Nota nota = null;
		try {
			nota = notaDAO.buscarNotaPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota;
	}

	// EDITAR
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarNota(Nota nota, @PathParam("id") int id) {
		String msg = "";
		System.out.println(nota.getTitulo());
		try {
			notaDAO.editarNota(nota, id);
			msg = "Nota editada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar a nota!";
			e.printStackTrace();
		}
		return msg;
	}

	// EDITAR
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNota(@PathParam("id") int id) {
		String msg = "";
		try {
			notaDAO.removerNota(id);
			msg = "Nota excluida com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao excluir a nota!";
			e.printStackTrace();
		}
		return msg;
	}

}
