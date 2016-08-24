package com.escola;
import java.io.Serializable;
import java.util.ArrayList;

public final class Disciplina implements Serializable {
	
	//atributos-----------------------------------------------------------------------
	private int codigo;

	private String nome;

	private int cargaHoraria;
	

	private Professor professor;
	
	//Construtor----------------------------------------------------------------------
	public Disciplina(int codigo, String nome, int cargaHoraria, Professor professor){
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.professor = professor;
	}
	
	//construtor para disciplina sem professor (disciplinas lidas do arquivo)
	public Disciplina(int codigo, String nome, int cargaHoraria){
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.professor = null;
	}
	
	//Metodos acessores----------------------------------------------------------------
	public int getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return this.cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	//Metodos Sobrescritos----------------------------------------------------------------
		@Override
		public String toString(){
			if(this.professor != null)
				return this.codigo + ";" + this.nome + 
						";" + this.getCargaHoraria() + ";" + this.professor.getCpf();
			else
				return this.codigo + ";" + this.nome + 
						";" + this.getCargaHoraria() ;
		}
		
		@Override
		public boolean equals(Object o){
			if( o != null && o instanceof Disciplina){
				Disciplina p = (Disciplina) o;
				return p.codigo == this.codigo && p.nome.equals(this.nome) && 
						p.cargaHoraria == this.cargaHoraria;
			}
			return false;
		}
		
}
