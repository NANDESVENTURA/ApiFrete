# ApiFrete
Api estudos consumo de Api externa e disparos de email automaticos

## Sobre
Esta api funciona como um servidor de uma tranportadora, aonde o cliente fornece os dados solicitados para cadastro do envio, além do 
peso da encomenda e o cep de envio da mesma. A API ja salva no banco de dados, com o valor do frete calculado, usando uma API externa 
do google para calcular a distancia do frete baseado no cep informado. Paralelo com isso, é disparado automaticamente para o email
fornecido pelo usuario um email com o ID do pedido dele e o status atual do mesmo. 
<br>

## Requisitos
- [x] CRUD de Entrega.

- [x] Autenticação de usuário.

- [x] Conexão MySQL.




## Começando
- Primeiramente, instale as dependências utilizando ```maven```. 
- Antes de começar dever ter:
    - Uma conexão MySQL.
- Colocar no arquivo ```application.properties``` os dados sensiveis a aplicação, como o secret para o token, o email de disparado e a key fornecida pelo serviço de email, a key da API do google
- para calculo da distancia e a conexão MySQL.
```application.properties
spring.datasource.url= < URL DE CONEXÃO DO BANCO >
spring.datasource.username=< USER DE CONEXÃO DO BANCO >
spring.datasource.password= < SENHA DE CONEXÃO DO BANCO >

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.token.secret= < SECRET DO TOKEN JWT >
api.google.key=<KEY FORNECIDA PELA API DO GOOGLE>

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<EMAIL DE DISPARO>
spring.mail.password=<PASSORWD FORNECIDA PELO SERVIÇO DE EMAIL>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

api.service.email.secret=<EMAIL DE DISPARO>
```
Com essas variáveis de ambiente configuradas podemos executar nossa aplicação.

## Executando
Para a execução, basta dar run no arquivo ```ApiAplication```. 
- Para criar as migrations no banco, basta criar os aquivos ```.sql``` na pasta ```db``` e rodar a aplicação,
as micrations são criadas automaticamente.

- As rotas dispostas pela API são:
- ```/users  POST``` : Para cadastro do usuário,informando o ```name```,```email``` e ```password``` pelo body.
- ```/login POST``` : Para autenticação do usuário,  passando o ```name``` e ```password``` pelo body.

<br>Todas abaixo precisam de autenticação


- ```//shipments POST```: Cadastra um pedido no banco de dados, passando os campos
"user_id","client_email","weight", um objeto "address" contendo "public_place","neighborhood","complement","number","state e "city".Também os campos "zip_code_origin" e "zip_code_destination".
- ```//shipments/:id GET``` : Retorna o pedido de acordo com o id passado pela rota.
- ```//shipments PUT``` : Altera o status do pedido, passando os campos "id" e "shipment_status" que so aceita "SENT","IN_TRANSIT" e "DELIVERED".



## Autor
*Ernandes Ventura Silva Neto*

[![Linkedin Badge](https://img.shields.io/badge/-Ernandes%20Ventura-6633cc?style=flat-square&logo=Linkedin&logoColor=black&link=https://www.linkedin.com/in/ernandes-ventura-892a88119/)](https://www.linkedin.com/in/ernandes-ventura-892a88119/)
