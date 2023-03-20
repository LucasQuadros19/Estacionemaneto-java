package br.com.uniamerica.estacionamento.Entity;
import java.time.LocalDateTime;

public abstract class AbstractEntity {
    LocalDateTime Cadastro;
    LocalDateTime edicao;
    boolean ativo;

    public AbstractEntity(LocalDateTime cadastro, LocalDateTime edicao, boolean ativo) {
        Cadastro = cadastro;
        this.edicao = edicao;
        this.ativo = ativo;
    }

    public LocalDateTime getCadastro() {
        return Cadastro;
    }

    public void setCadastro(LocalDateTime cadastro) {
        Cadastro = cadastro;
    }

    public LocalDateTime getEdicao() {
        return edicao;
    }

    public void setEdicao(LocalDateTime edicao) {
        this.edicao = edicao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
