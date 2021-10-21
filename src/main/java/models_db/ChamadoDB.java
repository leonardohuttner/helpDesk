/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models_db;

import br.com.myproject3.db.connection;
import br.com.myproject3.entities.Chamado;

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
public class ChamadoDB {
    
    private Connection conexao = connection.getConexao();

    public void save(Chamado chamado) {
            try{
//              Testa se o cliente ja é cliente e pega o id
                PreparedStatement VC = conexao.prepareStatement("SELECT id "
                        + "FROM clientes WHERE nome = ?");
                VC.setString(1,chamado.getNome_cliente());
                ResultSet VCR = VC.executeQuery();
                if(VCR.next()){
                    chamado.setId_cliente(VCR.getInt(1));
                    JOptionPane.showMessageDialog(null, VCR.getInt("id"));
            }
                
//                Testa e pega o id do responsavel
                try{
                    PreparedStatement ps = conexao.prepareStatement("SELECT id FROM responsaveis WHERE nome = ?");
                    ps.setString(1,chamado.getNome_responsavel());
                    ResultSet rs = ps.executeQuery();
                        if(rs != null && rs.next()){
                            chamado.setId_responsavel(rs.getInt(1));
                        }
                    
//                    se esta tudo correto salva
                    try {
                        PreparedStatement cx = conexao.prepareStatement("INSERT INTO atendimento (titulo, descricao, id_cliente, data_hora_abertura, id_responsavel, status) values (?,?,?,?,?,?)");
                        cx.setString(1, chamado.getTitulo());
                        cx.setString(2, chamado.getDescricao());
                        cx.setInt(3, chamado.getId_cliente());
                        cx.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                        cx.setInt(5, chamado.getId_responsavel());
                        cx.setString(6, chamado.getStatus());            
//                        cx.execute();
                        JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
                    } catch (SQLException ex) {
                        Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }catch(SQLException ex) {
                    Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado, por favor crie um antes de criar um chamado"); 
                Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void update(Chamado chamado) {
        try {
            try{
                PreparedStatement ps = conexao.prepareStatement("SELECT id FROM clientes WHERE nome = ?");
                ps.setString(1,chamado.getNome_cliente());
                ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        chamado.setId_cliente(rs.getInt("id"));
                    }
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado"); 
                Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                PreparedStatement ps = conexao.prepareStatement("SELECT id FROM responsaveis WHERE nome = ?");
                ps.setString(1,chamado.getNome_responsavel());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    chamado.setId_responsavel(rs.getInt("id"));
                }
            }catch(SQLException ex) {
                Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement ps = conexao.prepareStatement("UPDATE atendimento SET titulo=? , descricao=?, id_cliente=?, data_hora_abertura=?, id_responsavel=?, status= ? WHERE id=? ");
            ps.setString(1, chamado.getTitulo());
            ps.setString(2, chamado.getDescricao());
            ps.setInt(3, chamado.getId_cliente());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(5, chamado.getId_responsavel());
            ps.setString(6, chamado.getStatus()); 
            ps.setInt(7, chamado.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Chamado atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void saveOrUpdate(Chamado chamado) {
        if (chamado.getId() > 0) {
            update(chamado);
        } else {
            save(chamado);
        }
    }
    
    public void delete(Chamado chamado) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM atendimento WHERE id=? ");
            ps.setInt(1, chamado.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente id "+ chamado.getId()+" deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Chamado> getAll() {
         List<Chamado> chamados = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT a.id,a.titulo,"
                    + "c.nome,a.descricao, a.data_hora_abertura,a.status, r.nome AS nome_responsavel "
                    + "FROM ifsul.atendimento as a "
                    + "inner join ifsul.clientes AS c on a.id_cliente = c.id "
                    + "inner join ifsul.responsaveis as r on a.id_responsavel = r.id;");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Chamado chamado = new Chamado();
                chamado.setId(rs.getInt("id"));
                chamado.setTitulo(rs.getString("titulo"));
                chamado.setDescricao(rs.getString("descricao"));
                chamado.setNome_cliente(rs.getString("nome"));
                chamado.setDescricao(rs.getString("descricao"));
                chamado.setData_hora_abertura(rs.getDate("data_hora_abertura"));
                chamado.setNome_responsavel(rs.getString("nome_responsavel"));
                chamado.setStatus(rs.getString("status"));
                chamados.add(chamado);
            }
            return chamados;
        } catch (SQLException ex) {
            Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    };
    
    public List<Chamado> getOne(Chamado chamado) {
         List<Chamado> chamados = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT a.id,a.titulo,c.nome,a.descricao,a.data_hora_abertura,r.nome AS nome_responsavel, a.status "
                    + "FROM ifsul.atendimento AS a , ifsul.clientes AS c,ifsul.responsaveis AS r  "
                    + "WHERE a.id = c.id AND a.id_responsavel = r.id AND a.id= ?");
            ps.setInt(1, chamado.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                chamado.setId(rs.getInt("id"));
                chamado.setTitulo(rs.getString("titulo"));
                chamado.setNome_cliente(rs.getString("nome"));
                chamado.setDescricao(rs.getString("descricao"));
                chamado.setData_hora_abertura(rs.getDate("data_hora_abertura"));
                chamado.setNome_responsavel(rs.getString("nome_responsavel"));
                chamado.setStatus(rs.getString("status"));
                chamados.add(chamado);
            }
            return chamados;
        } catch (SQLException ex) {
            Logger.getLogger(Chamado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
