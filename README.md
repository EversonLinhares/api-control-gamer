# Api Controle de gamers-players

## Requisitos
<ul>
 <li> Java 11 </li>
 <li> MySQL 8 </li>
</ul>

## Rodando a aplicação
 <ul>
 <li>Para rodar uma instância do banco MySQL com comando . "docker-compose up -d" , na pasta raiz do projeto.</li>
 <li>1 Rodar a aplicação com docker</li>
 <li>1.1 docker build -t api-control-gamer:latest .</li>
 <li>1.2 docker run -d -p 8080:8082 api-control-gamer </li>
 </br>
 <li>2 Rodar a aplicação através do jar pela IDE </li>
 <li>2.1 Gerando o jar da aplicação com o comando "mvn clean package"</li>
 <li>2.2 java -jar target/email-0.0.1-SNAPSHOT.jar</li>
 </br>
 <li>Obs: para rodar a aplicação da primeira forma, e subir instância MySQL, é necessário o docker instalado na máquina</li>
 </ul>

## Regras do projeto

## Usuarios / Perfil / Players
<h2> Entenda "Player" como cada Boneco que foi cadastrado pelo usuário </h2>
<ul>
<li>Usuario com perfil user, só pode visualizar/editar o "player" que foi cadastrado por ele.</li>
<li>Apenas Usuário com perfil administrador, pode visualizar todos players e usuarios cadastrados no sistema </li>
<li>Usuario com perfil administrador não pode editar/excluir um player cadastrado no sistema, apenas o próprio usuario que cadastrou</li>
<li>Apenas Usuario com perfil administrador, pode cadastrar/editar outro usuario administrador </li>
</ul>

## Observações
<ul>
<li>A aplicação Front-End se encontra no repositorio : <a>https://github.com/EversonLinhares/gamer-app</a> </li>
<li>Lá você terá os requisitos para rodar a aplicação Front-End e instruções para apontar para a api:  "api-control-gamer" sendo nosso Back-End</li>
</ul>