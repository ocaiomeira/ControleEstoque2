package com.projetoEstoque.ControleEstoque.service;

import com.projetoEstoque.ControleEstoque.domain.Produto;
import com.projetoEstoque.ControleEstoque.repository.ProdutoRepository;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto findByIdOrThrowBadRequestException(long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto not Found"));
    }

    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        return produtoRepository.save(Produto.builder().name(produtoPostRequestBody.getName()).build());
    }

    public void delete(long id) {
        produtoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProdutoPutRequestBody produtoPutRequestBody) {
        findByIdOrThrowBadRequestException(produtoPutRequestBody.getId());
        Produto produto = Produto.builder()
                .id(produtoPutRequestBody.getId())
                .name(produtoPutRequestBody.getName())
                .build();
        produtoRepository.save(produto);
    }
}
