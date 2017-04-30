package br.com.diego.service;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.diego.model.Pessoa;

/**
 * 
 * @author Diego Francisco
 *
 */
public class ServiceClient {
    
    //gerencia a infraestrutura de comunicão lado cliente para executar as solicitações realizadas
    private Client client;
    
    //acessa um recurso identificado pelo URI (Uniform Resource Identifier/Identificador Uniforme de Recursos)
    private WebTarget webTarget;
    
    //URL do servico REST que vamos acessar
    private final String URL_SERVICE = "http://localhost:8080/webServicePessoa_rest_jersey_JPA/pessoasevice/";
    
    public ServiceClient() {
        this.client = ClientBuilder.newClient();
    }

    public String cadastrarPessoa(Pessoa pessoa) {

        this.webTarget = this.client.target(URL_SERVICE).path("cadastar");

        Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");

        Response response = invocationBuilder.post(Entity.entity(pessoa, "application/json;charset=UTF-8"));

        return response.readEntity(String.class);

    }

    public String alterarPessoa(Pessoa pessoa) {

        this.webTarget = this.client.target(URL_SERVICE).path("alterar");

        Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");

        Response response = invocationBuilder.put(Entity.entity(pessoa, "application/json;charset=UTF-8"));

        return response.readEntity(String.class);

    }

    public Pessoas buscarTodasPessoas() {

        this.webTarget = this.client.target(URL_SERVICE).path("buscartodas");

        Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");

        Response response = invocationBuilder.get();

        return response.readEntity(Pessoas.class);

    }

    public Pessoa buscarPessoaPorId(int codigo) {

        this.webTarget = this.client.target(URL_SERVICE).path("buscarPorId").path(String.valueOf(codigo));

        Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");

        Response response = invocationBuilder.get();

        return response.readEntity(Pessoa.class);

    }

    public String excluirPessoa(int codigo) {

        this.webTarget = this.client.target(URL_SERVICE).path("excluir").path(String.valueOf(codigo));

        Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");

        Response response = invocationBuilder.delete();

        return response.readEntity(String.class);

    }

}

class Pessoas extends ArrayList<Pessoa> {

    private static final long serialVersionUID = 1L;

}