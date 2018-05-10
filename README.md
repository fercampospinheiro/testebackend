

Para rodar o projeto na sua máquina é necessário seguir os passos abaixo:

**Pré-requisitos (Links abaixo instalação)**

Instale na sua máquina, caso não possua.

<a href="https://haylson.wordpress.com/2017/06/28/maven-instalando-e-configurando-em-seus-projetos/">Maven 3.x</a> ,
<a href="https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html">Java 1.8 </a> ,
<a href="https://git-scm.com/book/pt-br/v1/Primeiros-passos-Instalando-Git">Git</a> (Se não for baixar o ZIP)

>Se não tiver o  maven instalado e o java 1.8 não poderá executar o MVN no terminal e plugin
> spring-boot
```
mvn -version
java -version  

```

**Obtendo o projeto**

O projeto pode ser acesso de 2 maneiras:

**Github**

Acesse o github e copie o link do repositório a seguir. 
```
git clone https://github.com/fercampospinheiro/testebackend.git
```
Abra o terminal e execute o comando git.

**Zip**

Acesse <a href="https://github.com/fercampospinheiro/testebackend">testebackend</a> e clique em "Clone e download".
Para projeto zip, descompacte o mesmo em um diretório. 
		
**Executando o projeto**

Navegue via Terminal(Linux ou Mac) ou CMD(windows) até a pasta do projeto.

```
mvn spring-boot:run
```

O projeto está em uma single page na url a seguir. A porta pode ser alterada no application.properties.
```
localhota:9090/players
```





