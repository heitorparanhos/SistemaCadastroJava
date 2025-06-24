# Exemplo de Uso do Sistema de Cadastro

## 🚀 Como Testar o Sistema

### 1. Compilação e Execução

```bash
# Compilar o programa
javac src/SistemaCadastro.java

# Executar o programa
java -cp src SistemaCadastro
```

### 2. Fluxo de Teste Recomendado

#### Passo 1: Cadastrar Usuários de Diferentes Tipos

**Usuário Padrão (18-64 anos):**
```
Escolha uma opção: 1

=== CADASTRO DE USUÁRIO ===
Nome completo: João Silva
Email: joao.silva@gmail.com
Idade: 25
CPF (formato: 000.000.000-00): 123.456.789-00
```

**Usuário Menor (< 18 anos):**
```
Escolha uma opção: 1

=== CADASTRO DE USUÁRIO ===
Nome completo: Maria Santos
Email: maria.santos@hotmail.com
Idade: 15
CPF (formato: 000.000.000-00): 987.654.321-00
```

**Usuário Idoso (≥ 65 anos):**
```
Escolha uma opção: 1

=== CADASTRO DE USUÁRIO ===
Nome completo: Pedro Oliveira
Email: pedro.oliveira@yahoo.com
Idade: 70
CPF (formato: 000.000.000-00): 456.789.123-00
```

#### Passo 2: Listar Usuários
```
Escolha uma opção: 2

=== LISTA DE USUÁRIOS ===
Usuario{nome='João Silva', email='joao.silva@gmail.com', idade=25, cpf='123.456.789-00', tipo='Padrão'}
Usuario{nome='Maria Santos', email='maria.santos@hotmail.com', idade=15, cpf='987.654.321-00', tipo='Menor de Idade', responsavel='Responsável não informado'}
Usuario{nome='Pedro Oliveira', email='pedro.oliveira@yahoo.com', idade=70, cpf='456.789.123-00', tipo='Idoso', temPlanoSaude=false}
Total de usuários: 3
```

#### Passo 3: Buscar Usuário
```
Escolha uma opção: 3

=== BUSCA DE USUÁRIO ===
Digite o email para buscar: joao.silva@gmail.com
Usuário encontrado:
Usuario{nome='João Silva', email='joao.silva@gmail.com', idade=25, cpf='123.456.789-00', tipo='Padrão'}
```

#### Passo 4: Salvar Dados
```
Escolha uma opção: 4

=== SALVANDO DADOS ===
Dados salvos com sucesso em 'usuarios.txt'
Dados binários salvos em 'usuarios.bin'
```

#### Passo 5: Verificar Estatísticas
```
Escolha uma opção: 6

=== ESTATÍSTICAS DO SISTEMA ===
Total de usuários: 3
Média de idade: 36.67
Usuários Gmail: 1
Outros usuários: 2
Taxa de sucesso: 95.5%
Versão do sistema: 1.2
Categoria: A
Máximo de usuários: 1000
Tempo de execução: 12345 ms
```

#### Passo 6: Carregar Dados (após reiniciar)
```
Escolha uma opção: 5

=== CARREGANDO DADOS ===
Carregados 3 usuários do arquivo.
```

## 📋 Dados de Teste Sugeridos

### Emails Válidos para Teste:
- `usuario@gmail.com`
- `teste@hotmail.com`
- `exemplo@yahoo.com`
- `admin@empresa.com.br`

### CPFs Válidos para Teste:
- `123.456.789-00`
- `987.654.321-00`
- `456.789.123-00`
- `789.123.456-00`

### Idades para Testar Diferentes Tipos:
- **15 anos** → Usuário Menor
- **25 anos** → Usuário Padrão
- **70 anos** → Usuário Idoso

## 🔍 Testes de Validação

### Teste de Email Inválido:
```
Email: email_invalido
Email inválido! Use o formato: usuario@dominio.com
Email: usuario@dominio
Email inválido! Use o formato: usuario@dominio.com
Email: usuario@dominio.com
```

### Teste de CPF Inválido:
```
CPF (formato: 000.000.000-00): 12345678900
CPF inválido! Use o formato: 000.000.000-00
CPF (formato: 000.000.000-00): 123.456.789
CPF inválido! Use o formato: 000.000.000-00
CPF (formato: 000.000.000-00): 123.456.789-00
```

### Teste de Idade Inválida:
```
Idade: 0
Idade deve estar entre 1 e 150 anos.
Idade: 200
Idade deve estar entre 1 e 150 anos.
Idade: 25
```

## 📁 Arquivos Gerados

Após executar o sistema, você encontrará:

### usuarios.txt
```
João Silva,joao.silva@gmail.com,25,123.456.789-00
Maria Santos,maria.santos@hotmail.com,15,987.654.321-00
Pedro Oliveira,pedro.oliveira@yahoo.com,70,456.789.123-00
```

### usuarios.bin
Arquivo binário com dados serializados (não legível em texto).

## 🎯 Conceitos Demonstrados em Ação

### 1. Polimorfismo
Observe como o mesmo método `getTipoUsuario()` retorna valores diferentes:
- Usuário Padrão: "Padrão"
- Usuário Menor: "Menor de Idade"
- Usuário Idoso: "Idoso"

### 2. Herança
As classes `UsuarioMenor` e `UsuarioIdoso` herdam de `Usuario` e adicionam atributos específicos:
- `UsuarioMenor`: campo `responsavel`
- `UsuarioIdoso`: campo `temPlanoSaude`

### 3. Expressões Regulares
As validações de email e CPF usam padrões regex específicos:
- Email: `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$`
- CPF: `^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$`

### 4. Tratamento de Exceções
O sistema trata erros de:
- Entrada inválida (NumberFormatException)
- Arquivos não encontrados (FileNotFoundException)
- Erros de I/O (IOException)
- Validação de dados (IllegalArgumentException)

### 5. Estruturas de Controle
- **while**: Loop principal do menu
- **for-each**: Iteração sobre listas
- **if-else**: Decisões baseadas na idade
- **switch**: Categorização do sistema
- **break/continue**: Controle de fluxo nas estatísticas

## 🚨 Possíveis Erros e Soluções

### Erro de Compilação:
```
javac: command not found
```
**Solução**: Instale o Java JDK

### Erro de Execução:
```
Error: Could not find or load main class SistemaCadastro
```
**Solução**: Execute `java -cp src SistemaCadastro` (não apenas `java SistemaCadastro`)

### Arquivo não encontrado:
```
Arquivo 'usuarios.txt' não encontrado.
```
**Solução**: Primeiro salve dados (opção 4) antes de tentar carregar (opção 5)

---

**Dica**: Use este guia para testar todos os recursos do sistema e entender como cada conceito de Java é aplicado na prática! 🎓 