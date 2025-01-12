package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {

		ContatoDAO contatoDao = new ContatoDAO();

		Contato contato = new Contato();
		contato.setNome("Maria Morena");
		contato.setTelefone("(81) 98902-1283");
		contato.setDataCadastro(new Date());

		//contatoDao.save(contato);
		
		//atualizar o contato
		Contato c1 = new Contato();
		c1.setNome("Maria Morena Ramos");
		c1.setTelefone("(81) 98374-2134");
		c1.setDataCadastro(new Date());
		c1.setId(0);
		
		contatoDao.update(c1);
		
		//deletar contato
		contatoDao.deleteByID(0);
			 
		//view de todos registros do banco de dados
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato:"+c.getNome()+ " | " +c.getTelefone());
		}
	}

}
