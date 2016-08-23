
package com.escola;

import java.util.ArrayList;
import java.util.List;

public abstract class Professor {
	
	//atributos-----------------------------------------------------------------------
	private final  Long cpf;

	private String nome;

	private double salario;
	
	//Construtor----------------------------------------------------------------------
	public Professor(Long cpf, String nome, Double salario){
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;

	}
	
	//Metodos acessores----------------------------------------------------------------
	public Long getCpf() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	//Metodos Sobrescritos----------------------------------------------------------------
			@Override
			public String toString(){
				return this.cpf + ";" + this.nome + ";" + this.getSalario() + ";";
				//padrao de string pedido no enunciado
			}
			
			@Override
			public boolean equals(Object o){
				if( o != null && o instanceof Professor){
					Professor p = (Professor) o;
					return p.cpf == this.cpf && p.nome.equals(this.nome) && 
							p.salario == this.salario;
				}
				return false;
			}
	
}
