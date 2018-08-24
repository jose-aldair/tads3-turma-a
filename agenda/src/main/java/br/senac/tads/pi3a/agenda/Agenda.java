/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3a.agenda;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose.arcruz
 */
public class Agenda {
    private Connection obterConexao() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendabd",
                "root",
                "");
        return conn;
    }
    public void listar(){
        String querysql = "SELECT ID, NOME, DTNASCIMENTO FROM PESSOA";
        try(Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(querysql);
                ResultSet resultados = stmt.executeQuery()){
            while(resultados.next()){
                long id = resultados.getInt("ID");
                String nome = resultados.getString("NOME");
                Date dtNascimento = resultados.getDate("DTNASCIMENTO");
                System.out.println(id + " " + nome + " " + dtNascimento);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.listar();
    }
}
