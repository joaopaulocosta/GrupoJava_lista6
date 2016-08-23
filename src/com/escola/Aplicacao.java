package com.escola;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Aplicacao {
	
	public static Scanner ler;
	
	/*funcao que cria o objeto aluno que sera incluido na lista, retorna null caso seja informado
	um numero de matricula fora do padrao (que não seja inteiro)*/
	public  static Aluno incluirAluno(){
		int matricula;
		String nome;
		Date data;
		//verifica se o numero de matricula é um inteiro
		matricula = leMatriculaAluno();
		if(matricula == -1)
			return null;
		
		System.out.println("Nome: ");
		nome = new String(ler.nextLine());
		System.out.println("Data de nascimento: ");
		String[] dataS = ler.nextLine().split("/");
		data = new Date( dataS[1] + "/" + dataS[0] + "/" + dataS[2]);	//gambiarra para adaptar a entrada ao nosso formato
		Aluno novoAluno = new Aluno(matricula,nome,data);

		return novoAluno;
	}
	
	/*funcao que cria o objeto professor que sera incluido na lista, retorna null caso seja informado
	um numero de cpf fora do padrao (que não seja inteiro)*/
	public  static Professor incluirProfessor(){
		long cpf;
		String nome;
		double salario;
		//verifica se o numero de matricula é um inteiro
		cpf = leCpf();
		if(cpf == -1)
			return null;
		
		System.out.println("Nome: ");
		nome = new String(ler.nextLine());
		
		System.out.println("Salario: ");
		try{
			salario = Double.parseDouble(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato do salario esta incorreto, tete novamente");
			return null;
		}
		
		
		
		
		int opcao;
		String titulo;
		
		System.out.println("O professor a ser incluido possui mestrado ou doutorado?");
		System.out.println("Informe a opção adequada:");
		System.out.println("1 - Mestrado");
		System.out.println("2 - Doutorado");
		opcao = Integer.parseInt(ler.nextLine());
		Professor novoProfessor;
		switch(opcao){
		case 1:
			System.out.println("Entre com o titulo da dissetação do mestrado: ");
			titulo = ler.nextLine();
			novoProfessor = new Mestre(cpf,nome,salario, titulo);
			break;

		case 2:
			System.out.println("Entre com o titulo da tese de doutorado: ");
			titulo = ler.nextLine();
			novoProfessor = new Doutor(cpf,nome,salario, titulo);
			break;
		default:
			System.out.println("Opcao invalida, operacao cancelada!");
			return null;
		}
		

		return novoProfessor;
	}
	
	/*funcao que cria o objeto Disciplina que sera incluido na lista, retorna null caso seja informado
	um codigo de disciplina fora do padrao (que não seja inteiro)*/
	public  static Disciplina incluirDisciplina(BD bd){
		int codigo, cargaHoraria;
		String nome;

		//verifica se o codigo da matricula é um inteiro
		codigo = leCodigoDisciplina();
		if(codigo == -1)
			return null;
		
		System.out.println("Nome: ");
		nome = new String(ler.nextLine());
		
		System.out.println("Carga Horaria: ");
		//verifica formato de carga horaria
		try{
			cargaHoraria = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de carga Horaria esta incorreto, tete novamente");
			return null;
		}
		
		long cpfProfessor = leCpf();	//lendo cpf do professor da disciplina
		
		if(cpfProfessor == -1)		//verificando entrada
			return null;
		
		
		//procura professor com cpf informado
		Professor professor = bd.getProfessor(cpfProfessor);
		if(professor == null){
			System.out.println("Não foi encontrado professor com cpf informado, tente novamente.");
			return null;
		}
		
		//criando objeto
		Disciplina novaDisciplina = new Disciplina(codigo, nome, cargaHoraria, professor);

		return novaDisciplina;
	}
	
	//funcao que retorna o numero da matricula de  um aluno retorna -1 caso não seja informado um inteiro 
	public static int leMatriculaAluno(){
		int matricula = -1;
		System.out.println("Entre com o codigo de matricula do aluno: ");
		try{
			matricula = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de matricula esta incorreto, tete novamente");
		}
		return matricula;	
	}
	
	//funcao que retorna o numero da matricula de  um aluno retorna -1 caso não seja informado um inteiro 
	public static long leCpf(){
		long cpf = -1;
		System.out.println("Entre com o cpf do professor: ");
		try{
			cpf = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de cpf esta incorreto, tete novamente");
		}
		return cpf;	
	}
	
	//funcao que retorna o numero da matricula de  um aluno retorna -1 caso não seja informado um inteiro 
	public static int leCodigoDisciplina(){
		int codigo = -1;
		System.out.println("Entre com o codigo da disciplina: ");
		try{
			codigo = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato do codigo esta incorreto, tete novamente");
		}
		return codigo;	
	}
	
	public static Matricula novaMatricula(BD bd){
		Matricula matricula;
		
		//para criação de matricula temos que ter um aluno e uma disciplina
		Aluno aluno;
		Disciplina disciplina;
		
		//lendo codigo de matricula do aluno
		int codigoAluno = leMatriculaAluno();
		
		//caso codigo não esteja no padrão operação é abortada
		if(codigoAluno == -1)
			return null;
			
		//acessa aluno correspondente ao codigo
		aluno = bd.getAluno(codigoAluno);
		
		//caso aluno não exista operação é abortada
		if(aluno == null){
			System.out.println("Aluno não encontrado, tente novamente");
			return null;
		}
		//lendo codigo da disciplina
		int codigoDisciplina = leCodigoDisciplina();
		
		//caso codigo não esteja no padrão operação é abortada
		if(codigoDisciplina == -1)
			return null;
		
		disciplina = bd.getDisciplina(codigoDisciplina);
		
		//caso disciplina não exista operação é abortada
		if(disciplina == null){
			System.out.println("Disciplina não econtrada, tente novamente");
			return null;
		}
		
		//recebendo a nota
		Integer nota = null;
		System.out.println("Entre com a nota do aluno, digite -1 caso ainda não exista");
		try{
			nota = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de nota esta incorreto, tete novamente");
			return null;
		}
		
		
		
		if(nota >= 60)
			System.out.println("APROVADO");
		else if(nota < 60 && nota >= 0)
			System.out.println("REPROVADO");
		else{
			System.out.println("EM CURSO");
			nota = null;
		}
		
		//criando objeto
		matricula = new Matricula(disciplina, aluno, nota);
		
		
		return matricula;
	}
	
	public static void inserirNota(BD bd){
		Matricula matricula;
		
		//para criação de matricula temos que ter um aluno e uma disciplina
		Aluno aluno;
		Disciplina disciplina;
		
		//lendo codigo de matricula do aluno
		int codigoAluno = leMatriculaAluno();
		
		//caso codigo não esteja no padrão operação é abortada
		if(codigoAluno == -1)
			return ;
			
		//acessa aluno correspondente ao codigo
		aluno = bd.getAluno(codigoAluno);
		
		//caso aluno não exista operação é abortada
		if(aluno == null){
			System.out.println("Aluno não encontrado, tente novamente.");
			return ;
		}
		//lendo codigo da disciplina
		int codigoDisciplina = leCodigoDisciplina();
		
		//caso codigo não esteja no padrão operação é abortada
		if(codigoDisciplina == -1)
			return;
		
		disciplina = bd.getDisciplina(codigoDisciplina);
		
		//caso disciplina não exista operação é abortada
		if(disciplina == null){
			System.out.println("Disciplina não econtrada, tente novamente.");
			return;
		}
		//criando objeto
		
		matricula = bd.getMatricula(codigoAluno, codigoDisciplina);
		
		if(matricula == null){
			System.out.println("Matricula não encontrada, tente novamente.");
			return;
		}
		
		System.out.println("Entre com a nota: ");
		
		//recebendo valor da nota
		Integer nota;
		try{
			nota = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de nota esta incorreto, tete novamente");
			return;
		}
		
		matricula.setPontuacao(nota);
		
		if(nota >= 60)
			System.out.println("APROVADO");
		else if(nota < 60)
			System.out.println("REPROVADO");

	}
	
	public static void main(String [] args){
		BD bd = new BD();
		ler = new Scanner(System.in);
		int menu = -1, matricula, codigo;
		long cpf;
		while(menu != 0){
			System.out.println("Menu");
			System.out.println("1 - Incluir aluno");
			System.out.println("2 - Excluir aluno");
			System.out.println("3 - Listar aluno");
			System.out.println("4 - Incluir Professor");
			System.out.println("5 - Excluir Professor");
			System.out.println("6 - Listar Professor");
			System.out.println("7 - Incluir Disciplina");
			System.out.println("8 - Excluir Disciplina");
			System.out.println("9 - Listar Disciplina");
			System.out.println("10 - Matricular");
			System.out.println("11 - Inserir Nota");
			System.out.println("12 - Emitir Relatorio");
			System.out.println("0 - Sair");
			
			menu = Integer.parseInt(ler.nextLine());
			
			switch(menu){
				case 1 : 
					System.out.println("Incluindo novo aluno...");
					Aluno aluno = incluirAluno();
					if(aluno != null)	//verifica se função retornou tipo correto
						bd.addAluno(aluno);
					break;
				case 2 :
					System.out.println("Exluindo aluno...");
					matricula = leMatriculaAluno();
					if(matricula != -1)	//verifica se função retornou tipo correto
						bd.excluirAluno(matricula);
					break;
				case 3 :
					System.out.println("Listando aluno...");
					bd.listarAluno();
					break;
				case 4 :
					System.out.println("Incluindo novo professor...");
					Professor novoProfessor = incluirProfessor();
					if(novoProfessor != null)	//verifica se função retornou tipo correto
						bd.addProfessor(novoProfessor);
					break;
				case 5 :
					System.out.println("Exluindo professor...");
					cpf = leMatriculaAluno();
					if(cpf != -1)	//verifica se função retornou tipo correto
						bd.excluirProfessor(cpf);
					break;
				case 6 :
					System.out.println("Listando professor...");
					bd.listarProfessor();
					break;
				case 7 :
					System.out.println("Incluindo nova Disciplina...");
					Disciplina novaDisciplina = incluirDisciplina(bd);
					if(novaDisciplina != null)	//verifica se função retornou tipo correto
						bd.addDisciplina(novaDisciplina);
					break;
				case 8 :
					System.out.println("Exluindo disciplina...");
					codigo = leCodigoDisciplina();
					if(codigo != -1)	//verifica se função retornou tipo correto
						bd.excluirDisciplina(codigo);
					break;
				case 9 :
					System.out.println("Listando disciplina...");
					bd.listarDisciplina();
					break;
				case 10:
					System.out.println("Criando matricula...");
					Matricula novaMatricula = novaMatricula(bd);
					if(novaMatricula != null)
						bd.addMatricula(novaMatricula);
					break;
				case 11:
					System.out.println("Inserindo nota...");
					inserirNota(bd);
					break;
					
				case 12:
					System.out.println(bd.gerarBackup());
					break;
			}
			
		}
		ler.close();	//fechando scanner
	}
	
}
