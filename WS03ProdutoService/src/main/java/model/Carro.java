package model;

public class Carro {
    private int id;
    private String modelo;
    private String marca;
    private int ano;
    private float preco;

    public Carro() {}

    public Carro(int id, String modelo, String marca, int ano, float preco) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public float getPreco() { return preco; }
    public void setPreco(float preco) { this.preco = preco; }

    @Override
    public String toString() {
        return "Carro [id=" + id + ", modelo=" + modelo + ", marca=" + marca + 
               ", ano=" + ano + ", preco=" + preco + "]";
    }
}
