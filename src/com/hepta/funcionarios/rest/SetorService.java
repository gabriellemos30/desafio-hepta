package com.hepta.funcionarios.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.SetorDAO;

@Path("/setores")
public class SetorService {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    private SetorDAO dao;

    public SetorService() {
        dao = new SetorDAO();
    }

    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Adiciona novo Setor
     * 
     * @param Setor: Novo Setor
     * @return response 200 (OK) - Conseguiu adicionar
     */
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response SetorCreate(Setor setor) {
    	
    	try {
    		setor.setId(null);
			dao.salvar(setor);
			return Response.status(Status.CREATED).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }

    /**
     * Lista todos os Setors
     * 
     * @return response 200 (OK) - Conseguiu listar
     */
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response SetorRead() {
        List<Setor> Setores = new ArrayList<>();
        try {
            Setores = dao.listarTodos();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar Setors").build();
        }
        GenericEntity<List<Setor>> entity = new GenericEntity<List<Setor>>(Setores) {
        };
        return Response.status(Status.OK).entity(entity).build();
    }

    /**
     * Atualiza um Setor
     * 
     * @param id:          id do Setor
     * @param Setor: Setor atualizado
     * @return response 200 (OK) - Conseguiu atualizar
     */
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response SetorUpdate(@PathParam("id") Integer id, Setor Setor) {
    	try {
    		if (dao.buscarPorId(id)==null) {
    			return Response.status(Status.NOT_FOUND).build();
    		}
    		Setor setor = dao.editar(Setor);
    		return Response.status(Status.OK).entity(setor).build();
    	}catch (Exception e) {
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao Atualizar Setores").build();
    	}
        
    }

    /**
     * Métodos simples apenas para testar o REST
     * @return
     */
    @Path("/teste")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String TesteJersey() {
        return "Funcionando.";
    }

}
