package com.escola;
import java.util.Scanner;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
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
	
	//funcao que cria uma nova matricula
	public static Matricula novaMatricula(BD bd){
		Matricula matricula = null;
		
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
		//utilizei o numero -1 para ativar a opção "EM CURSO"
		System.out.println("Entre com a nota do aluno, digite -1 caso ainda não exista");
		try{
			nota = Integer.parseInt(ler.nextLine());
		}
		catch(NumberFormatException e){
			System.out.println("Formato de nota esta incorreto, tente novamente");
			return null;
		}
		
		
		//criando objeto
		//verificando se exceção é ativada
		try{
			matricula = new Matricula(disciplina, aluno, nota);
			if(nota >= 60)
				System.out.println("APROVADO");
			else if(nota < 60 && nota >= 0)
				System.out.println("REPROVADO");
			else if(nota == -1){					//caso nota == -1 excessão não será ativada
				System.out.println("EM CURSO");
				nota = null;
			}
		}catch(NotaException e){
			System.out.println(e.getMessage());
		}
		
		return matricula;
	}
	
	//função que inseri a nota de matricula ja cadastrada
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
		
		try{
			matricula.setPontuacao(nota);
			if(nota >= 60)
				System.out.println("APROVADO");
			else if(nota < 60)
				System.out.println("REPROVADO");
		}catch(NotaException e){
			System.out.println(e.getMessage());
		}
		
		

	}
	
	//funcao generica para leitura de arquivo, retorna uma StringBuilder com o conteúdo
	public static StringBuilder lerArquivo(){
		
		StringBuilder conteudoArquivo = new StringBuilder();
		
		System.out.println("Entre com o nome do arquivo: ");
		String nomeArquivo = ler.nextLine();
		
		//leitura do arquivo de entrada
				try{
					
					File arquivo = new File(nomeArquivo);
					Reader in = new FileReader( arquivo);
					LineNumberReader reader = new LineNumberReader(in);
					while(reader.ready()){
						conteudoArquivo.append(reader.readLine() + "\n");
					}
					reader.close();
					in.close();
				
				//tratamento de escessoes
				} catch (java.io.FileNotFoundException e){
					System.out.println("Arq. nao existe. Causa: " + e.getMessage());
					return null;
				} catch (java.io.IOException e) {
					System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
					return null;
				}
		
		return conteudoArquivo;
}
	
	//funcao que trata o conteudo do arquivo e adiciona as disciplinas contidas nele
	public static void lerArquivoDisciplinas(BD bd){
		
		StringBuilder conteudoArquivo = lerArquivo();
		
		//aborta de leitura de arquivo não foi bem sucedida
		if(conteudoArquivo == null)
			return;
		
		//dividindo o stringBuffer em linhas
		String[] linhas = conteudoArquivo.toString().split("\n");
		for(String linha: linhas){
			try{	/*exceção de leitura de arquivo, caso alguma linha esteja fora do padrão ela sera
					escrita em um arquivo externo chamado logErros.txt */
				
				//divide a linha nas três partes que constituem a disciplina
				String[] pedacosDisciplina = linha.split(";");
	
				int codigo = Integer.parseInt(pedacosDisciplina[0]);
				
				String nome = pedacosDisciplina[1];
				
				int cargaHoraria = Integer.parseInt(pedacosDisciplina[2].trim());
				
				//cria nova Disciplina
				Disciplina novaDisciplina = new Disciplina(codigo ,nome ,cargaHoraria);
				
				bd.addDisciplina(novaDisciplina);
				
			}catch(Exception e){
				try {	//exceção de criação de arquivo
					FileWriter arq =  new FileWriter("logErros.txt", true);
					PrintWriter gravarArq = new PrintWriter(arq);
					gravarArq.printf("%s\r\n",linha);
					gravarArq.close();
					arq.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		
		}
	}
	
	//funcao que trata o conteudo do arquivo e adiciona os alunos contidas nele
	public static void lerArquivoAlunos(BD bd){
		
		StringBuilder conteudoArquivo = lerArquivo();
		
		//aborta de leitura de arquivo não foi bem sucedida
		if(conteudoArquivo == null)
			return;
		
		//dividindo o stringBuffer em linhas
		String[] linhas = conteudoArquivo.toString().split("\n");
		for(String linha: linhas){
			try{	/*exceção de leitura de arquivo, caso alguma linha esteja fora do padrão ela sera
					escrita em um arquivo externo chamado logErros.txt */
				
				//divide a linha nas três partes que constituem o objeto aluno
				String[] pedacosAluno = linha.split(";");
	
				int codMatricula = Integer.parseInt(pedacosAluno[0]);
				
				String nome = pedacosAluno[1];
				
				String[] dataS = pedacosAluno[2].split("/");
				Date data = new Date( dataS[1] + "/" + dataS[0] + "/" + dataS[2]);	

				
				//cria novo Aluno
				Aluno novoAluno = new Aluno(codMatricula ,nome ,data);
				
				bd.addAluno(novoAluno); 
				
			}catch(Exception e){
				try {	//exceção de criação de arquivo
					FileWriter arq =  new FileWriter("logErros.txt", true);
					PrintWriter gravarArq = new PrintWriter(arq);
					gravarArq.printf("%s\r\n",linha);
					gravarArq.close();
					arq.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		
		} 
	}
	
	//funcao que trata o conteudo do arquivo e adiciona os professores contidos nele
	public static void lerArquivoProfessores(BD bd){
		
		StringBuilder conteudoArquivo = lerArquivo();
		
		//aborta de leitura de arquivo não foi bem sucedida
		if(conteudoArquivo == null)
			return;
		
		//dividindo o stringBuffer em linhas
		String[] linhas = conteudoArquivo.toString().split("\n");
		for(String linha: linhas){
			try{	/*exceção de leitura de arquivo, caso alguma linha esteja fora do padrão ela sera
					escrita em um arquivo externo chamado logErros.txt */
				
				//divide a linha nas 5 partes de acordo com formato de arquito informado
				String[] pedacosProfessor = linha.split(";");
	
				long cpf = Long.parseLong(pedacosProfessor[1]);
				
				String nome = pedacosProfessor[2];
				
				double salario = Double.parseDouble(pedacosProfessor[3]);
				
				String titulo = pedacosProfessor[4];

				
				//cria novo professor de acordo com primeiro parametro
				Professor novoProfessor = null;
				if(pedacosProfessor[0].equals("M"))
					novoProfessor = new Mestre(cpf ,nome ,salario,titulo);
				else if(pedacosProfessor[0].equals("D"))
					novoProfessor = new Doutor(cpf ,nome ,salario,titulo);
				
				bd.addProfessor(novoProfessor); 
				
			}catch(Exception e){
				try {	//exceção de criação de arquivo
					FileWriter arq =  new FileWriter("logErros.txt", true);
					PrintWriter gravarArq = new PrintWriter(arq);
					gravarArq.printf("%s\r\n",linha);
					gravarArq.close();
					arq.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		
		} 
	}
	
	//funcao principal
	public static void main(String [] args){
		BD bd = new BD();
		ler = new Scanner(System.in);
		int menu = -1, matricula, codigo;
		long cpf;
		while(menu != 0){
			System.out.println("Menu - Selecione a opcao desejada:");
			System.out.println("1 - Incluir aluno, 2 - Excluir aluno, 3 - listar aluno");
			System.out.println("4 - Incluir Professor, 5 - Excluir Professor, 6 - listar Professor");
			System.out.println("7 - Incluir Disciplina, 8 - Excluir Disciplina, 9- Listar Disciplina");
			System.out.println("10 - Criar Matricula");
			System.out.println("11 - Inserir Nota");
			System.out.println("12 - Emitir Relatorio");
			System.out.println("13 - Importar Disciplinas de Arquivo");
			System.out.println("14 - Importar Alunos de Arquivo");
			System.out.println("15 - Importar Professores de Arquivo");
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
				case 13:
					System.out.println("Lendo arquivo de disciplinas...");
					lerArquivoDisciplinas(bd);
					break;
				case 14:
					System.out.println("Lendo arquivo de alunos...");
					lerArquivoAlunos(bd);
					break;
				case 15:
					System.out.println("Lendo arquivo de professores...");
					lerArquivoProfessores(bd);
					break;
				default:
					if(menu != 0)
						System.out.println("Opção inválida");
			}
			
		}
		ler.close();	//fechando scanner
	}
	
}
