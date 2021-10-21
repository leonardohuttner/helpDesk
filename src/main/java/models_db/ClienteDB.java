/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models_db;


import br.com.myproject3.db.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


import br.com.myproject3.entities.Cliente;
import java.sql.Date;
import java.time.Instant;
//import utils.Conexao;


/**
 *
 * @author leonardohuttner
 */
public class ClienteDB {
    private Connection conexao = connection.getConexao();

    public void save(Cliente cliente) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO clientes (nome, email, telefone, sexo, endereco, cpf, created_at) values (?,?,?,?,?,?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getSexo());
            ps.setString(5, cliente.getEndereco());
            ps.setString(6, cliente.getCpf());
            ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Cliente cliente) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE clientes SET nome=? , email=?, telefone=?, sexo=?, endereco=?, cpf= ? WHERE id=? ");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(4, cliente.getSexo());
            ps.setString(3, cliente.getTelefone());
            ps.setString(5, cliente.getEndereco());
            ps.setString(6, cliente.getCpf());
            ps.setInt(6, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void saveOrUpdate(Cliente cliente) {
        if (cliente.getId() > 0) {
            update(cliente);
        } else {
            save(cliente);
        }
    }
    
    public void delete(Cliente cliente) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM clientes WHERE id=? ");
            ps.setInt(1, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente id "+ cliente.getId()+" deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Cliente> getAll() {
         List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setClienteDesde(rs.getDate("created_at"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    };
    
    public List<Cliente> getOne(Cliente cliente) {
         List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM clientes WHERE nome=?");
            ps.setString(1, cliente.getNome());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setClienteDesde(rs.getDate("created_at"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
