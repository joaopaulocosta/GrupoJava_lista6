package com.escola;

public class NotaException extends Exception {
	private double nota;
	
	public NotaException(double nota){
		this.nota = nota;
	}
	
	@Override
	public String getMessage(){
		return "Nota "+ nota + " esta fora do padrao, so é possível valores "
				+ "entre 0 e 100";
	}
	
}
