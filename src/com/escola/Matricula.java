package com.escola;

import java.io.Serializable;

public final class Matricula implements Serializable {
	
	//atributos-----------------------------------------------------------------------
	private Integer pontuacao;
	
	private Disciplina disciplina;

	private Aluno aluno;
	
	//Construtor----------------------------------------------------------------------
	
	//construtor com tratamento de exceção
	public Matricula(Disciplina disciplina, Aluno aluno, Integer pontuacao) throws NotaException{
		this.disciplina = disciplina;
		this.aluno = aluno;
		
		
		if(pontuacao != null){		//evitando exceção de ponteiro
			if(pontuacao > 100 || pontuacao <= -1)
				throw new NotaException(pontuacao);
			else
				this.pontuacao = pontuacao;
		}
	}
	
	//Metodos acessores----------------------------------------------------------------
	public Integer getPontuacao() {
		return this.pontuacao;
	}
	
	public Aluno getAluno(){
		return this.aluno;
	}
	
	public Disciplina getDisciplina(){
		return this.disciplina;
	}

	public void setPontuacao(Integer pontuacao) throws NotaException {
		
		if(pontuacao > 100 || pontuacao < 0)
			throw new NotaException(pontuacao);
		else
			this.pontuacao = pontuacao;
	}
	
	//Metodos Sobrescritos----------------------------------------------------------------
	@Override
	public String toString(){
		return  this.disciplina.getCodigo() + ";" + this.aluno.getMatricula() + ";" 
				+ this.getPontuacao();
	}
	
	@Override
	public boolean equals(Object o){
		if( o != null && o instanceof Matricula){
			Matricula p = (Matricula) o;
			return p.pontuacao == this.pontuacao && p.aluno.equals(this.aluno) && 
					p.disciplina.equals(this.disciplina);
		}
		return false;
	}
	
}
