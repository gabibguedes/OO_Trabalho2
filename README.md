# Aprenda QEE

O programa Aprenda QEE é o resultado de um trabalho da disciplina de Orientação a
Objetos. Tem-se como objetivo a criação de uma ferramenta para auxiliar as aulas
de Qualidade de Energia Elétrica, ao trazer simulações dos conteúdos dados em sala.
A aplicação realiza calculos e apresenta gráficos para simular o fluxo de potência
fundamental e a distorção harmonica.


Para executar o programa, após o download abra a pasta ep2 no terminal e execute
os seguintes comandos:

* Linux:

    >cd src  
    javac view/Main.java  
    java view/Main  

Ou abra o projeto no eclipse e execute a Main.java dentro do pacote view.

## Menu Inicial

A tela do menu será a primeira a aparecer ao iniciar a execução do programa, nela
o usuário poderá escolher qual simulação deseja fazer pelos botões que redirecionam
para as proximas telas. O usuário poderá voltar a esse menu a qualquer momento pelo
botão "Voltar ao Menu Inicial" presente ao final de todas as telas de simulação.  

## Simular Fluxo de Potência Fundamental

Após escolher esta simulação o usuário deverá preencher os dados da amplitude e 
angulo da tensão e da corrente e, em seguida, apertar o botão "Simular Resultado"
no final da tela. Com isso, os graficos de onda e os resultados de potencia ativa, 
reativa, aparente e fator de potencia serão gerados. Além disso, o triangulo de 
potência também sera desenhado, nele teremos, em vermelho, a potência ativa, em 
verde a potência reativa e em azul o vetor resultante S.

O usuário poderá escolher também somente visualizar o gráfico de tensão ou de corrente.
Para isso, abaixo do campo do angulo de fase tem o botão "Simular Forma de Onda".

