package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDAO extends DAO {

    public CarroDAO() {
        super();
        conectar();
    }
    
    // Removed finalize() - deprecated and unreliable. Call close() manually if needed.
    // For web app, keep connection open or use a pool.

    public boolean insert(Carro carro) {
        boolean status = false;
        try {
            String sql = "INSERT INTO carro (modelo, marca, ano, preco) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setInt(3, carro.getAno());
            stmt.setFloat(4, carro.getPreco());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                carro.setId(rs.getInt("id"));
                status = true;
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public Carro get(int id) {
        Carro carro = null;
        try {
            String sql = "SELECT * FROM carro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                carro = new Carro(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getFloat("preco")
                );
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carro;
    }

    public List<Carro> get() {
        return getOrderByID();
    }

    public List<Carro> getOrderByID() {
        return get("id");
    }

    public List<Carro> getOrderByModelo() {
        return get("modelo");
    }

    public List<Carro> getOrderByPreco() {
        return get("preco");
    }

    private List<Carro> get(String orderBy) {
        List<Carro> carros = new ArrayList<>();
        try {
            Statement stmt = conexao.createStatement();
            String sql = "SELECT * FROM carro" + (orderBy.isEmpty() ? "" : " ORDER BY " + orderBy);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                carros.add(new Carro(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getFloat("preco")
                ));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carros;
    }

    public boolean update(Carro carro) {
        boolean status = false;
        try {
            String sql = "UPDATE carro SET modelo = ?, marca = ?, ano = ?, preco = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getMarca());
            stmt.setInt(3, carro.getAno());
            stmt.setFloat(4, carro.getPreco());
            stmt.setInt(5, carro.getId());

            status = stmt.executeUpdate() > 0;
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        try {
            // Fixed: Use PreparedStatement to prevent SQL injection
            String sql = "DELETE FROM carro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            status = stmt.executeUpdate() > 0;
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
