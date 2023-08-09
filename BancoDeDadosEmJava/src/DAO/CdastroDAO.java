/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CadastroDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class CdastroDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CadastroDTO> lista = new ArrayList<>();

    public void cadastrarConsulta(CadastroDTO objcadastrodto) {
        String sql = "insert into atendimento_clinvet.consultas_cadastradas(nome_tutor,email_tutor,telefone_tutor,nome_animal,idade_animal,servico_desejado,nivel_emergencia) values(?,?,?,?,?,?,?)";
        

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objcadastrodto.getNome_tutor());
            pstm.setString(2, objcadastrodto.getEmail_tutor());
            pstm.setString(3, objcadastrodto.getTelefone_tutor());
            pstm.setString(4, objcadastrodto.getNome_animal());
            pstm.setString(5, objcadastrodto.getIdade_animal());
            pstm.setString(6, objcadastrodto.getServico_desejado());
            pstm.setString(7, objcadastrodto.getNivel_emergencia());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "CdastroDAO" + erro);
        }

    }
    
    
    public ArrayList <CadastroDTO> PesquisarConsultaCadastrada(){
        String sql = "select * from atendimento_clinvet.consultas_cadastradas";
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                CadastroDTO objCadastroDTO = new  CadastroDTO();
                objCadastroDTO.setNome_tutor(rs.getString("nome_tutor"));
                objCadastroDTO.setEmail_tutor(rs.getString("email_tutor"));
                objCadastroDTO.setTelefone_tutor(rs.getString("telefone_tutor"));
                objCadastroDTO.setNome_animal(rs.getString("nome_animal"));
                objCadastroDTO.setIdade_animal(rs.getString("idade_animal"));
                objCadastroDTO.setServico_desejado(rs.getString("servico_desejado"));
                objCadastroDTO.setNivel_emergencia(rs.getString("nivel_emergencia"));
                
                lista.add(objCadastroDTO);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CdastroDAO Pesquisar: " + erro);
        }
        return lista;
    }
    
}
