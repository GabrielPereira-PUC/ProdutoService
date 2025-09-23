package app;

import static spark.Spark.*;
import service.ProdutoService;
import service.CarroService;

public class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	private static CarroService carroService = new CarroService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        // Rotas Produto
        post("/produto/insert", produtoService::insert);
        get("/produto/:id", produtoService::get);
        get("/produto/list/:orderby", produtoService::getAll);
        get("/produto/update/:id", produtoService::getToUpdate);
        post("/produto/update/:id", produtoService::update);
        get("/produto/delete/:id", produtoService::delete);

        // Rotas Carro
        post("/carro/insert", carroService::insert);
        get("/carro/:id", carroService::get);
        get("/carro/list/:orderby", carroService::getAll);
        get("/carro/update/:id", carroService::getToUpdate);
        post("/carro/update/:id", carroService::update);
        get("/carro/delete/:id", carroService::delete);
    }
}
