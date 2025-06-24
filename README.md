# Sistema Didático de Cadastro de Usuários em Java

## 📋 Descrição

Este é um programa completo em Java que demonstra de forma didática e prática todos os principais conceitos da linguagem Java e paradigmas de programação. O sistema implementa um cadastro de usuários com validação de dados, persistência em arquivo e simulação de serialização binária.

## 🎯 Objetivos Educacionais

Este projeto foi desenvolvido para ilustrar os seguintes conceitos:

### 1️⃣ Tipos de Dados
- **Tipos Primitivos**: `int`, `double`, `boolean`, `char`, `byte`, `short`, `long`, `float`
- **Tipos por Referência**: `String`, `ArrayList`, `Map`, classes personalizadas

### 2️⃣ Orientação a Objetos (Paradigma OO)
- Classes, atributos privados, métodos públicos
- Construtores e encapsulamento
- Herança e polimorfismo
- Interface implementada (`Validador`)
- Sobrescrita de métodos (`@Override`)
- Sobrecarga de métodos

### 3️⃣ Estruturas de Controle
- Estruturas condicionais: `if`, `else if`, `else`, `switch`
- Estruturas iterativas: `while`, `do-while`, `for`, enhanced `for`
- Uso de `break`, `continue` e `return`

### 4️⃣ Expressões Regulares
- Validação de emails usando `Pattern` e `Matcher`
- Validação de CPF com formato específico

### 5️⃣ Entrada e Saída
- `Scanner` para leitura via console
- `BufferedReader` e `BufferedWriter` para arquivos texto
- `FileOutputStream` e `FileInputStream` para dados binários

### 6️⃣ Bibliotecas Externas (Simuladas)
- Simulação de Apache Commons para validação
- Simulação de Gson para conversão JSON

### 7️⃣ Tratamento de Exceções
- `try-catch` e `try-with-resources`
- Tratamento de exceções de I/O e validação

### 8️⃣ Boas Práticas
- Código modular e orientado a objetos
- Comentários explicativos
- Nomes descritivos de variáveis e métodos

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Terminal ou prompt de comando

### Compilação
```bash
# Navegue até o diretório do projeto
cd ProjetoTrabalhoJava

# Compile o programa
javac src/SistemaCadastro.java
```

### Execução
```bash
# Execute o programa
java -cp src SistemaCadastro
```

## 📱 Funcionalidades do Sistema

### Menu Principal
1. **Cadastrar Usuário** - Adiciona novo usuário com validação completa
2. **Listar Usuários** - Exibe todos os usuários cadastrados
3. **Buscar Usuário** - Busca usuário por email
4. **Salvar Dados** - Salva dados em arquivo texto e binário
5. **Carregar Dados** - Carrega dados do arquivo texto
6. **Estatísticas** - Exibe estatísticas do sistema
7. **Sair** - Encerra o programa

### Tipos de Usuários
O sistema automaticamente classifica os usuários em:
- **Usuário Padrão**: 18-64 anos
- **Usuário Menor**: < 18 anos (com responsável)
- **Usuário Idoso**: ≥ 65 anos (com plano de saúde)

### Validações Implementadas
- **Nome**: Não pode ser vazio
- **Email**: Formato válido (usando regex)
- **Idade**: Entre 1 e 150 anos
- **CPF**: Formato 000.000.000-00 (usando regex)

## 📁 Estrutura do Projeto

```
ProjetoTrabalhoJava/
├── src/
│   └── SistemaCadastro.java    # Programa principal
├── README.md                   # Este arquivo
├── usuarios.txt               # Arquivo de dados (criado automaticamente)
└── usuarios.bin               # Arquivo binário (criado automaticamente)
```

## 🔧 Arquivos Gerados

Durante a execução, o programa cria:
- `usuarios.txt`: Arquivo texto com dados dos usuários em formato CSV
- `usuarios.bin`: Arquivo binário com dados serializados

## 📚 Conceitos Demonstrados em Detalhes

### Classes e Herança
```java
// Classe base
class Usuario implements Validador, Serializable

// Herança
class UsuarioMenor extends Usuario
class UsuarioIdoso extends Usuario
```

### Interface
```java
interface Validador {
    boolean validar();
    String getMensagemErro();
}
```

### Polimorfismo
- Método `getTipoUsuario()` sobrescrito em cada subclasse
- Método `toString()` personalizado para cada tipo de usuário

### Expressões Regulares
```java
// Validação de email
String padrao = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

// Validação de CPF
String padrao = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
```

### Tratamento de Exceções
```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
    // Operações de I/O
} catch (IOException e) {
    // Tratamento de erro
}
```

## 🎓 Valor Educacional

Este projeto serve como:
- **Exemplo prático** de todos os conceitos fundamentais de Java
- **Demonstração** de boas práticas de programação
- **Material didático** para aprendizado de POO
- **Referência** para desenvolvimento de sistemas similares

## 📝 Exemplo de Uso

```
=== SISTEMA DIDÁTICO DE CADASTRO DE USUÁRIOS ===
Demonstração dos conceitos de Java e Programação Orientada a Objetos

==================================================
MENU PRINCIPAL
==================================================
1 - Cadastrar Usuário
2 - Listar Usuários
3 - Buscar Usuário
4 - Salvar Dados
5 - Carregar Dados
6 - Estatísticas
7 - Sair
Sistema: Categoria Avançada

Escolha uma opção: 1

=== CADASTRO DE USUÁRIO ===
Nome completo: João Silva
Email: joao@email.com
Idade: 25
CPF (formato: 000.000.000-00): 123.456.789-00
Usuário cadastrado com sucesso!
Tipo de usuário: Padrão
```

## 🤝 Contribuições

Este projeto foi desenvolvido como trabalho acadêmico para demonstrar os conceitos de programação em Java. Sinta-se à vontade para usar como referência ou base para seus próprios projetos educacionais.

---

**Desenvolvido para fins educacionais** 📚 