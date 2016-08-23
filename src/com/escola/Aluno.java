package com.escola;
import java.util.Date;
import java.text.SimpleDateFormat;



public final class Aluno {
	
//atributos--------------------------------------------------------------------------------------------------------------
	private final int matricula;

	private String nome;

	private Date dataNascimento;
	
	//private ArrayList<Matricula> listaMatriculas;
	
//Construtor------------------------------------------------------------------------------------------------------------
	public Aluno(int matricula, String nome, Date dataNascimento){
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		//this.listaMatriculas = new ArrayList<Matricula>();
	}
	
//Metodos acessores----------------------------------------------------------------------------------------------------
	public int getMatricula() {
		return this.matricula;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}
	
	public void setDataNascimento(Date data){
		this.dataNascimento = data;
	}
	
//Metodos Sobrescritos-------------------------------------------------------------------------------------------------
	@Override
	public String toString(){
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yy");
		return this.matricula+ ";" + this.nome + ";" + sdt.format(this.dataNascimento);
		//padrao de string pedido no enunciado
	}
	
	@Override
	public boolean equals(Object o){
		if( o != null && o instanceof Aluno){
			Aluno p = (Aluno) o;
			return p.matricula == this.matricula && p.nome.equals(this.nome) && 
					p.dataNascimento.equals(this.dataNascimento);
		}
		return false;
	}
	

}
