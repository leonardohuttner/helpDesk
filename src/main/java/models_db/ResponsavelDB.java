/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models_db;

import br.com.myproject3.db.connection;
import br.com.myproject3.entities.Responsavel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardohuttner
 */
public class ResponsavelDB {
    
    private Connection conexao = connection.getConexao();

    public void save(Responsavel responsavel) {
        try {
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO responsaveis (nome, email, telefone, cargo) values (?,?,?,?)");
            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getEmail());
            ps.setString(3, responsavel.getTelefone());
            ps.setString(4, responsavel.getCargo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Responsavel responsavel) {
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE responsaveis SET nome=? , email=?, telefone=?, cargo=? WHERE id=? ");
            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getEmail());
            ps.setString(3, responsavel.getTelefone());
            ps.setString(4, responsavel.getCargo());
            ps.setInt(5, responsavel.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Responsavel atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void saveOrUpdate(Responsavel responsavel) {
        if (responsavel.getId() > 0) {
            update(responsavel);
        } else {
            save(responsavel);
        }
    }
    
    public void delete(Responsavel responsavel) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM responsaveis WHERE id=? ");
            ps.setInt(1, responsavel.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Responsavel id "+ responsavel.getId()+" deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Responsavel> getAll() {
         List<Responsavel> responsaveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM responsaveis");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Responsavel responsavel = new Responsavel();
                responsavel.setId(rs.getInt("id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setTelefone(rs.getString("telefone"));
                responsavel.setCargo(rs.getString("cargo"));
                responsaveis.add(responsavel);
            }
            return responsaveis;
        } catch (SQLException ex) {
            Logger.getLogger(Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    };
    
    public List<Responsavel> getOne(Responsavel responsavel) {
         List<Responsavel> responsaveis = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM responsaveis WHERE nome=?");
            ps.setString(1, responsavel.getNome());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                responsavel.setId(rs.getInt("id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setTelefone(rs.getString("telefone"));
                responsavel.setCargo(rs.getString("cargo"));
                responsaveis.add(responsavel);
            }
            return responsaveis;
        } catch (SQLException ex) {
            Logger.getLogger(Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
