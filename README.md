


Pré-requisitos (Clique em cima para ver instalação)

	Você precisa das seguintes ferremantas instaladas na sua máquina

	<a href="https://haylson.wordpress.com/2017/06/28/maven-instalando-e-configurando-em-seus-projetos/">Maven 3.x</a
	<a href="https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html">Java 1.8 </a>
	<a href='https://git-scm.com/book/pt-br/v1/Primeiros-passos-Instalando-Git'>Git</a> (Se não for baixar o ZIP)

1- Executando o projeto
	
	1.1 - Obtedo o projeto
	a) Clonando via git ou fazendo o fork do projeto
		Instale o git na sua máquina. Mais detlhes aqui <a href='https://git-scm.com/book/pt-br/v1/Primeiros-passos-Instalando-Git'>aqui</a>.   
		
		>git clone https://github.com/fercampospinheiro/testebackend.git

	b) Baxaindo o zip 
		Acesse <a href="https://github.com/fercampospinheiro/testebackend">testebackend</a> e clique em Clone e download 

	1.2 - Executando

			Se baixou o zip, descompacte o mesmo em um diretório. 
			Navegue via Terminal(Linux ou Mac) ou CMD(windows) e navegue até a pasta do projeto descompactado ou clonado,
			execute o comando do maven pra gerar o pacote tipo jar do projeto.

			Obs: 	Se não tiver o  maven instalado e o java 1.8 não poderá dar o comando a seguir. 
					Verifique se o maven esta instalado com : >mvn -version
					E o java com: >java -version     

			O Comando a seguir subirá a aplicação na porta >localhota:9090
			Basta acessaor a url > localhota:9090/player/form
			
			<strong>COMANDO</strong>
			>mvn spring-boot:run
