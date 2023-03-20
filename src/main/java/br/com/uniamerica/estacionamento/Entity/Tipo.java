package br.com.uniamerica.estacionamento.Entity;

public enum Tipo {
    CARRO("Carro"),
    MOTO("Moto"),
    VAM("Vam");

    private String Tipos;

    Tipo(String tipos) {
        this.Tipos = tipos;
    }

    public String getTipos() {
        return Tipos;
    }
}
