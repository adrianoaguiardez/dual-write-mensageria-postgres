# Projeto
    <h1 align="center"> Implementando o Dual Write com Mensageria PostgreSQL </h1>
    
### 

1. Java 17 instalado
2. Postgres 14+

### Como executar projeto:

1. Build

    mvn clean package

2. Run
 
 Na pasta target na raiz do projeto executar: 
 java -jar dual-writes-0.0.1-SNAPSHOT.jar

## Json 
<h5>Cliente</h5>
{
    "nome": "Seu nome"
}

<h5>Produto</h5>
{
    "nome": "Bola de Futebol"
}
<h5>Pedido</h5>

{
    "cliente": {
        "id" : 1
    },
    "produtos": [
        {
        "produto" : {
            "id" : 1
        },
        "quantidade" : 2
    }
    ]
}


## Repositório GitHub

    https://github.com/adrianoaguiardez/dual-write-mensageria-postgres  