JSF Issue Tracker Project
=========================

Projeto simples de uma aplicação de Issue Tracker com `JSF 2.x`, Primefaces, `CDI(OpenWebBean 1.1.4)`, `CODI(1.0.5)`, Conventions(0.8-SNAPSHOT) e `Hibernate 4.1.4` com o objetivo de explanar as principais features de cada tecnologia e como integra-las de maneira produtiva em um projeto real. 

Esta versão é um fork do projeto https://github.com/rponte/jsf-issuetracker-project que é construído durante os cursos e treinamentos de **JSF 2, Spring e Hibernate** ministrados pela [TriadWorks](http://www.triadworks.com.br).

Caso tenha interesse ou alguma dúvida sobre os cursos e treinamentos da TriadWorks, entre em contato com (http://www.triadworks.com.br/contatos.html).

Configurando o projeto e banco de dados.
----------------------------------------

Por padrão o projeto está configurado para o banco de dados `PostgreSQL`, mas já que se trata de uma aplicação com `Hibernate`, você pode simplesmente configura-lo para trabalhar com qualquer outro banco.

Os passos básicos são:

1. Importe o projeto no [Eclipse Java EE IDE for Web Developers (Indigo)](http://www.eclipse.org/downloads/) ou superior; 
2. Adicione o JDBC Driver no diretório `/WebContent/WEB-INF/lib` caso não pretenda utilizar o `PostgreSQL`;
3. crie o datasource - no tomcat adicione uma entrada no arquivo TOMCAT_HOME/conf/context.xml (exemplo é encontrado na pasta etc/snippets/config/context.xml) 
4. Crie o banco de dados `issuetracker` com a ferramenta de sua preferência (como o `PGAdmin`, no caso do `PostgreSQL`);
5. Faça o deploy no `Apache Tomcat 7.x` e inicie o servidor;
6. Acesse a aplicação através da url [http://localhost:8080/issuetracker](http://localhost:8080/issuetracker) ;
7. Faça o login com o usuário admin senha admin(no primeiro acesso esse usuario é criado);

Gerando .war da aplicação
------------------------
1. Para gerar o `.war` da aplicação basta executar o ant script (`build.xml`) no Eclipse ou na linha de comando:

		$ ant

2. Após ter executado o ant script o `.war` será gerado em `/target/war/snapshot/issuetracker.war`;

Informações adicionais
------------------------

* O schema do banco de dados, `issuetracker`, será criado pelo `Hibernate` ao iniciar a aplicação pela primeira vez;
* Dentro do diretório `/etc/lib` você encontra todas as libs e dependências organizadas de cada framework;
* Dentro do diretório `/etc/lib/jdbc-drivers` é possível encontrar alguns drivers já disponíveis, como `MySQL`, `PostgreSQL` e `Oracle`;
* Dentro do diretório `/etc/mockups` você encontra os mockups (esboços) das telas da aplicação;
* Como eu sou um péssimo Web designer, o design da aplicação foi copiado (e levemente modificado) do projeto [vraptor-blank-project](http://vraptor.caelum.com.br/en) da Caelum;

Mais informações
----------------

**TriadWorks**
- http://www.triadworks.com.br
- http://www.triadworks.com.br/servico.html

**Rafael Ponte**
- Meu [blog](http://www.rponte.com.br)
- Meu Twitter [@rponte](http://twitter.com/#!/rponte)

**Rafael Pestano**
- Meu [blog](http://rpestano.wordpress.com/)
- Meu Twitter [@realpestano](http://twitter.com/#!/realpestano)
- Mini framework JavaEE6 que mantenho (http://code.google.com/p/jsf-conventions-framework/) e utilizado neste projeto

**JSF Group**
- https://groups.google.com/group/javasf/