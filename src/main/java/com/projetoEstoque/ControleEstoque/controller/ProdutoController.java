package com.projetoEstoque.ControleEstoque.controller;

import com.projetoEstoque.ControleEstoque.domain.Produto;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPutRequestBody;
import com.projetoEstoque.ControleEstoque.service.ProdutoService;
import com.projetoEstoque.ControleEstoque.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("produtos")
@Log4j2
@RequiredArgsConstructor
public class ProdutoController{
    private final DateUtil dateUtil;
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(produtoService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable long id) {
        return ResponseEntity.ok(produtoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ProdutoPutRequestBody produtoPutRequestBody) {
        produtoService.replace(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
