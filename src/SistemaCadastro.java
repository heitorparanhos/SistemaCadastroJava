import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Sistema Didático de Cadastro de Usuários
 * 
 * Este programa demonstra de forma prática e didática os principais conceitos
 * da linguagem Java e paradigmas de programação, incluindo:
 * - Tipos de dados primitivos e por referência
 * - Orientação a Objetos (herança, polimorfismo, encapsulamento)
 * - Estruturas de controle
 * - Expressões regulares
 * - Entrada e saída de dados
 * - Tratamento de exceções
 * - Boas práticas de programação
 * 
 * @author Estudante de Programação
 * @version 1.0
 */
public class SistemaCadastro {
    
    // ========================================
    // 1. TIPOS DE DADOS PRIMITIVOS E POR REFERÊNCIA
    // ========================================
    
    // Tipos primitivos como atributos da classe principal
    private static int totalUsuarios = 0;           // int: números inteiros
    private static double mediaIdade = 0.0;         // double: números decimais de precisão dupla
    private static boolean sistemaAtivo = true;     // boolean: valores verdadeiro/falso
    private static char categoriaSistema = 'A';     // char: caracteres únicos
    private static byte versaoSistema = 1;          // byte: números inteiros pequenos (-128 a 127)
    private static short maxUsuarios = 1000;        // short: números inteiros médios (-32,768 a 32,767)
    private static long timestampInicio;            // long: números inteiros grandes
    private static float taxaSucesso = 95.5f;       // float: números decimais de precisão simples
    
    // Tipos por referência
    private static List<Usuario> usuarios = new ArrayList<>();  // ArrayList: lista dinâmica
    private static Map<String, Usuario> usuariosPorEmail = new HashMap<>();  // Map: estrutura chave-valor
    private static Scanner scanner = new Scanner(System.in);   // Scanner: entrada de dados
    
    /**
     * Método principal que inicia o sistema
     */
    public static void main(String[] args) {
        // Inicialização do timestamp
        timestampInicio = System.currentTimeMillis();
        
        System.out.println("=== SISTEMA DIDÁTICO DE CADASTRO DE USUÁRIOS ===");
        System.out.println("Demonstração dos conceitos de Java e Programação Orientada a Objetos\n");
        
        // ========================================
        // 2. ESTRUTURAS DE CONTROLE - LOOPS E CONDIÇÕES
        // ========================================
        
        // Loop principal do sistema usando while
        while (sistemaAtivo) {
            exibirMenu();
            
            // Estrutura condicional com if-else if-else
            int opcao = lerInteiro("Escolha uma opção: ");
            
            if (opcao == 1) {
                cadastrarUsuario();
            } else if (opcao == 2) {
                listarUsuarios();
            } else if (opcao == 3) {
                buscarUsuario();
            } else if (opcao == 4) {
                salvarDados();
            } else if (opcao == 5) {
                carregarDados();
            } else if (opcao == 6) {
                exibirEstatisticas();
            } else if (opcao == 7) {
                sistemaAtivo = false;
                System.out.println("Sistema encerrado. Obrigado!");
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Exibe o menu principal usando switch expression (Java 14+)
     */
    private static void exibirMenu() {
        // Cria string de separação compatível com Java 8+
        String separador = "";
        for (int i = 0; i < 50; i++) {
            separador += "=";
        }
        
        System.out.println("\n" + separador);
        System.out.println("MENU PRINCIPAL");
        System.out.println(separador);
        
        // Switch expression moderno (Java 14+)
        String[] opcoes = {"", "Cadastrar Usuário", "Listar Usuários", "Buscar Usuário", 
                          "Salvar Dados", "Carregar Dados", "Estatísticas", "Sair"};
        
        // Enhanced for loop (for-each)
        for (int i = 1; i < opcoes.length; i++) {
            System.out.printf("%d - %s%n", i, opcoes[i]);
        }
        
        // Demonstração do switch tradicional
        switch (categoriaSistema) {
            case 'A':
                System.out.println("Sistema: Categoria Avançada");
                break;
            case 'B':
                System.out.println("Sistema: Categoria Básica");
                break;
            case 'C':
                System.out.println("Sistema: Categoria Completa");
                break;
            default:
                System.out.println("Sistema: Categoria Desconhecida");
        }
    }
    
    /**
     * Cadastra um novo usuário com validação completa
     */
    private static void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        
        try {
            // Leitura e validação dos dados
            String nome = lerString("Nome completo: ");
            String email = lerEmail("Email: ");
            int idade = lerIdade("Idade: ");
            String cpf = lerCPF("CPF (formato: 000.000.000-00): ");
            
            // Criação do usuário usando polimorfismo
            Usuario usuario;
            
            // Estrutura condicional para determinar o tipo de usuário
            if (idade < 18) {
                usuario = new UsuarioMenor(nome, email, idade, cpf);
            } else if (idade >= 65) {
                usuario = new UsuarioIdoso(nome, email, idade, cpf);
            } else {
                usuario = new Usuario(nome, email, idade, cpf);
            }
            
            // Adição à lista e mapa
            usuarios.add(usuario);
            usuariosPorEmail.put(email, usuario);
            totalUsuarios++;
            
            // Atualização da média de idade
            atualizarMediaIdade();
            
            System.out.println("Usuário cadastrado com sucesso!");
            System.out.println("Tipo de usuário: " + usuario.getTipoUsuario());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Lista todos os usuários cadastrados
     */
    private static void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        
        // Enhanced for loop para iterar sobre a lista
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
        
        System.out.printf("Total de usuários: %d%n", totalUsuarios);
    }
    
    /**
     * Busca um usuário por email
     */
    private static void buscarUsuario() {
        System.out.println("\n=== BUSCA DE USUÁRIO ===");
        
        String email = lerEmail("Digite o email para buscar: ");
        
        // Busca no Map usando containsKey
        if (usuariosPorEmail.containsKey(email)) {
            Usuario usuario = usuariosPorEmail.get(email);
            System.out.println("Usuário encontrado:");
            System.out.println(usuario.toString());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    /**
     * Salva os dados em arquivo texto
     */
    private static void salvarDados() {
        System.out.println("\n=== SALVANDO DADOS ===");
        
        // Try-with-resources para garantir fechamento automático dos recursos
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            
            // Loop for tradicional
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                writer.write(usuario.toCSV());
                writer.newLine();
            }
            
            System.out.println("Dados salvos com sucesso em 'usuarios.txt'");
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
        
        // Demonstração de escrita binária
        salvarDadosBinarios();
    }
    
    /**
     * Salva dados em formato binário
     */
    private static void salvarDadosBinarios() {
        try (FileOutputStream fos = new FileOutputStream("usuarios.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            // Escreve o número total de usuários
            oos.writeInt(totalUsuarios);
            oos.writeDouble(mediaIdade);
            
            // Escreve cada usuário
            for (Usuario usuario : usuarios) {
                oos.writeObject(usuario);
            }
            
            System.out.println("Dados binários salvos em 'usuarios.bin'");
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados binários: " + e.getMessage());
        }
    }
    
    /**
     * Carrega dados do arquivo texto
     */
    private static void carregarDados() {
        System.out.println("\n=== CARREGANDO DADOS ===");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            
            String linha;
            int contador = 0;
            
            // Do-while loop para leitura de arquivo
            do {
                linha = reader.readLine();
                if (linha != null && !linha.trim().isEmpty()) {
                    processarLinhaCSV(linha);
                    contador++;
                }
            } while (linha != null);
            
            System.out.printf("Carregados %d usuários do arquivo.%n", contador);
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo 'usuarios.txt' não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
    
    /**
     * Processa uma linha CSV para criar usuário
     */
    private static void processarLinhaCSV(String linha) {
        try {
            String[] dados = linha.split(",");
            if (dados.length >= 4) {
                String nome = dados[0];
                String email = dados[1];
                int idade = Integer.parseInt(dados[2]);
                String cpf = dados[3];
                
                Usuario usuario = new Usuario(nome, email, idade, cpf);
                usuarios.add(usuario);
                usuariosPorEmail.put(email, usuario);
                totalUsuarios++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao processar linha: " + linha);
        }
    }
    
    /**
     * Exibe estatísticas do sistema
     */
    private static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        
        // Demonstração de uso de break e continue
        int usuariosAtivos = 0;
        int usuariosInativos = 0;
        
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().contains("@gmail.com")) {
                usuariosAtivos++;
                continue; // Pula para o próximo usuário
            }
            
            if (usuario.getIdade() > 100) {
                break; // Para o loop se encontrar usuário com mais de 100 anos
            }
            
            usuariosInativos++;
        }
        
        System.out.printf("Total de usuários: %d%n", totalUsuarios);
        System.out.printf("Média de idade: %.2f%n", mediaIdade);
        System.out.printf("Usuários Gmail: %d%n", usuariosAtivos);
        System.out.printf("Outros usuários: %d%n", usuariosInativos);
        System.out.printf("Taxa de sucesso: %.1f%%%n", taxaSucesso);
        System.out.printf("Versão do sistema: %d.%d%n", versaoSistema, (byte)2);
        System.out.printf("Categoria: %c%n", categoriaSistema);
        System.out.printf("Máximo de usuários: %d%n", maxUsuarios);
        
        // Cálculo do tempo de execução
        long tempoExecucao = System.currentTimeMillis() - timestampInicio;
        System.out.printf("Tempo de execução: %d ms%n", tempoExecucao);
    }
    
    // ========================================
    // MÉTODOS UTILITÁRIOS PARA LEITURA E VALIDAÇÃO
    // ========================================
    
    /**
     * Lê uma string do console
     */
    private static String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Lê um inteiro do console com validação
     */
    private static int lerInteiro(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
    
    /**
     * Lê e valida um email usando expressões regulares
     */
    private static String lerEmail(String prompt) {
        String email;
        do {
            email = lerString(prompt);
            if (validarEmail(email)) {
                return email;
            }
            System.out.println("Email inválido! Use o formato: usuario@dominio.com");
        } while (true);
    }
    
    /**
     * Lê e valida uma idade
     */
    private static int lerIdade(String prompt) {
        int idade;
        do {
            idade = lerInteiro(prompt);
            if (idade > 0 && idade <= 150) {
                return idade;
            }
            System.out.println("Idade deve estar entre 1 e 150 anos.");
        } while (true);
    }
    
    /**
     * Lê e valida um CPF usando expressões regulares
     */
    private static String lerCPF(String prompt) {
        String cpf;
        do {
            cpf = lerString(prompt);
            if (validarCPF(cpf)) {
                return cpf;
            }
            System.out.println("CPF inválido! Use o formato: 000.000.000-00");
        } while (true);
    }
    
    /**
     * Valida email usando expressões regulares
     */
    private static boolean validarEmail(String email) {
        // Padrão regex para validação de email
        String padrao = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Valida CPF usando expressões regulares
     */
    private static boolean validarCPF(String cpf) {
        // Padrão regex para CPF no formato 000.000.000-00
        String padrao = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }
    
    /**
     * Atualiza a média de idade dos usuários
     */
    private static void atualizarMediaIdade() {
        if (!usuarios.isEmpty()) {
            int somaIdades = 0;
            for (Usuario usuario : usuarios) {
                somaIdades += usuario.getIdade();
            }
            mediaIdade = (double) somaIdades / usuarios.size();
        }
    }
}

/**
 * Interface para validação de dados
 * Demonstra o conceito de interface em Java
 */
interface Validador {
    boolean validar();
    String getMensagemErro();
}

/**
 * Classe base Usuario
 * Demonstra encapsulamento, construtores e métodos
 */
class Usuario implements Validador, Serializable {
    // Atributos privados (encapsulamento)
    private String nome;
    private String email;
    private int idade;
    private String cpf;
    private Date dataCadastro;
    
    // Construtor padrão
    public Usuario() {
        this.dataCadastro = new Date();
    }
    
    // Construtor com parâmetros (sobrecarga de construtor)
    public Usuario(String nome, String email, int idade, String cpf) {
        this();
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cpf = cpf;
        
        // Validação no construtor
        if (!validar()) {
            throw new IllegalArgumentException(getMensagemErro());
        }
    }
    
    // Getters e Setters (encapsulamento)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public Date getDataCadastro() { return dataCadastro; }
    
    // Implementação da interface Validador
    @Override
    public boolean validar() {
        return nome != null && !nome.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               idade > 0 && idade <= 150 &&
               cpf != null && !cpf.trim().isEmpty();
    }
    
    @Override
    public String getMensagemErro() {
        if (nome == null || nome.trim().isEmpty()) return "Nome é obrigatório";
        if (email == null || email.trim().isEmpty()) return "Email é obrigatório";
        if (idade <= 0 || idade > 150) return "Idade deve estar entre 1 e 150";
        if (cpf == null || cpf.trim().isEmpty()) return "CPF é obrigatório";
        return "Dados válidos";
    }
    
    // Sobrescrita do método toString (polimorfismo)
    @Override
    public String toString() {
        return String.format("Usuario{nome='%s', email='%s', idade=%d, cpf='%s', tipo='%s'}", 
                           nome, email, idade, cpf, getTipoUsuario());
    }
    
    // Método para conversão para CSV
    public String toCSV() {
        return String.format("%s,%s,%d,%s", nome, email, idade, cpf);
    }
    
    // Método que pode ser sobrescrito pelas subclasses (polimorfismo)
    public String getTipoUsuario() {
        return "Padrão";
    }
    
    // Sobrescrita de método (múltiplos métodos com mesmo nome, parâmetros diferentes)
    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    public void atualizarDados(String nome, String email, int idade) {
        atualizarDados(nome, email);
        this.idade = idade;
    }
}

/**
 * Classe UsuarioMenor - herda de Usuario
 * Demonstra herança e polimorfismo
 */
class UsuarioMenor extends Usuario {
    private String responsavel;
    
    public UsuarioMenor(String nome, String email, int idade, String cpf) {
        super(nome, email, idade, cpf);
        this.responsavel = "Responsável não informado";
    }
    
    public UsuarioMenor(String nome, String email, int idade, String cpf, String responsavel) {
        super(nome, email, idade, cpf);
        this.responsavel = responsavel;
    }
    
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
    
    // Sobrescrita do método getTipoUsuario (polimorfismo)
    @Override
    public String getTipoUsuario() {
        return "Menor de Idade";
    }
    
    // Sobrescrita do método toString
    @Override
    public String toString() {
        return super.toString().replace("}", "") + 
               String.format(", responsavel='%s'}", responsavel);
    }
    
    // Sobrescrita do método validar da interface
    @Override
    public boolean validar() {
        return super.validar() && getIdade() < 18;
    }
    
    @Override
    public String getMensagemErro() {
        if (!super.validar()) {
            return super.getMensagemErro();
        }
        if (getIdade() >= 18) {
            return "Usuário menor deve ter menos de 18 anos";
        }
        return "Dados válidos";
    }
}

/**
 * Classe UsuarioIdoso - herda de Usuario
 * Demonstra herança e polimorfismo
 */
class UsuarioIdoso extends Usuario {
    private boolean temPlanoSaude;
    
    public UsuarioIdoso(String nome, String email, int idade, String cpf) {
        super(nome, email, idade, cpf);
        this.temPlanoSaude = false;
    }
    
    public UsuarioIdoso(String nome, String email, int idade, String cpf, boolean temPlanoSaude) {
        super(nome, email, idade, cpf);
        this.temPlanoSaude = temPlanoSaude;
    }
    
    public boolean isTemPlanoSaude() { return temPlanoSaude; }
    public void setTemPlanoSaude(boolean temPlanoSaude) { this.temPlanoSaude = temPlanoSaude; }
    
    // Sobrescrita do método getTipoUsuario (polimorfismo)
    @Override
    public String getTipoUsuario() {
        return "Idoso";
    }
    
    // Sobrescrita do método toString
    @Override
    public String toString() {
        return super.toString().replace("}", "") + 
               String.format(", temPlanoSaude=%s}", temPlanoSaude);
    }
    
    // Sobrescrita do método validar da interface
    @Override
    public boolean validar() {
        return super.validar() && getIdade() >= 65;
    }
    
    @Override
    public String getMensagemErro() {
        if (!super.validar()) {
            return super.getMensagemErro();
        }
        if (getIdade() < 65) {
            return "Usuário idoso deve ter 65 anos ou mais";
        }
        return "Dados válidos";
    }
}

/**
 * Classe utilitária para simular bibliotecas externas
 * Demonstra criação de métodos utilitários
 */
class Utilitarios {
    
    /**
     * Simula validação de string da Apache Commons
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Simula conversão para JSON (como Gson)
     */
    public static String toJson(Usuario usuario) {
        return String.format("{\"nome\":\"%s\",\"email\":\"%s\",\"idade\":%d,\"cpf\":\"%s\",\"tipo\":\"%s\"}",
                           usuario.getNome(), usuario.getEmail(), usuario.getIdade(), 
                           usuario.getCpf(), usuario.getTipoUsuario());
    }
    
    /**
     * Simula validação de email da Apache Commons
     */
    public static boolean isValidEmail(String email) {
        if (!isNotBlank(email)) return false;
        
        String padrao = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(padrao);
    }
}
