package com.projetoEstoque.ControleEstoque.mapper;
import com.projetoEstoque.ControleEstoque.domain.Produto;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {
    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
    public abstract Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody);
    public abstract Produto toProduto(ProdutoPutRequestBody produtoPostRequestBody);


}
