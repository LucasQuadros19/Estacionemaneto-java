package br.com.uniamerica.estacionamento.Entity;

public enum Cor {
    AZUL("Azul"),
    PRETO("preto"),
    BRANCO("branco"),

    VERMELHO("Vermelho");
    private String cores;

    Cor(String cores) {
        this.cores = cores;
    }

    public String getCores() {
        return cores;
    }
}
