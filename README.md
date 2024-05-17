# Sistema de Empréstimo de Ferramentas para Amigos

Este é um sistema de empréstimo de ferramentas para amigos, desenvolvido em Java e utilizando o MySQL (MariaDB) como banco de dados. O sistema permite cadastrar amigos, cadastrar ferramentas, realizar empréstimos, realizar a devolução das ferramentas emprestadas e oferece alguns relatórios e funcionalidades adicionais.

## 🛠 Pré-requisitos

Antes de executar o sistema, certifique-se de ter instalado os seguintes componentes:
- IDE para Alterar Configurações do Banco de Dados: [Baixar JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows), [Baixar Visual Studio Code](https://code.visualstudio.com/download)
- [Java JDK 22](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
- [MySQL Workbench ou Gerenciador de Banco de Dados de sua Preferência](https://dev.mysql.com/downloads/workbench/)
- [MariaDB Server ou MySQL Server](https://mariadb.org/download/?t=mariadb&p=mariadb&r=11.3.2&os=windows&cpu=x86_64&pkg=msi&mirror=fder)
- [Git](https://git-scm.com/downloads)
- [Maven](https://maven.apache.org/download.cgi)

## Configuração do Banco de Dados e 🚀 Executando o Projeto

É preciso alterar as seguintes configrações de banco de dados de acordo com seu ambiente:
- serverName = "localhost";    Caminho do servidor do BD
- mydatabase = "a3db";     Nome do seu banco de dados
- username = "root";       Nome de um usuário de seu BD
- password = "";      Sua senha de acesso
- port = "3306"; Porta default do Banco de dados

1. Clone o projeto para o seu ambiente local, escolha alguma pasta em seu computador e abra o terminal, e de o seguinte comando:
- git clone https://github.com/jvlerner/GerenciamentoEmprestimosCRUD.git

   
2. Abra o arquivo [a3db.sql](https://github.com/jvlerner/maven-adm-application/blob/master/a3db.sql)
- Copie e cole o código SQL no seu Sistema de Gerenciamento de Banco de Dados e execute. Após criar o database a3db e suas respectivas tabelas, podemos prosseguir.


3. Para buildar o Aplicativo Desktop após alterar as configurações de banco de dados utilizando sua IDE, siga esse passo a passo para [instalar o Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#build-the-project) 
- Execute o seguindo comando na pasta raiz do projeto:
- mvn package

   
4. Pronto agora é só abrir seu terminal e executar o arquivo jar.
- "/target/GerenciamentoEmprestimosCRUD-1.0-SNAPSHOT-jar-with-dependencies.jar"

  
5. Caso tenha dado algum erro:
- Certifique-se de substituir `serverName`, `username` e `password` pelas configurações correspondentes ao seu ambiente.


## ⚙️ Funcionalidades

O sistema possui as seguintes funcionalidades:

- Cadastrar amigo
- Cadastrar ferramenta
- Realizar empréstimo
- Realizar a devolução da ferramenta emprestada
- Relatório de todos os empréstimos com filtros(devolvido, emprestado, pesquisar)
- Relatório da quantidade de empréstimos realizados a pessoas
