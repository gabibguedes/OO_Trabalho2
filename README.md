NOME: Gabriela Barrozo Guedes  
MATRÍCULA: 16/0121612  

# Aprenda QEE

O programa Aprenda QEE é o resultado de um trabalho da disciplina de Orientação a
Objetos. Tem-se como objetivo a criação de uma ferramenta para auxiliar nas aulas
de Qualidade de Energia Elétrica ao trazer simulações dos conteúdos dados em sala.
A aplicação realiza calculos e apresenta gráficos para simular o fluxo de potência
fundamental e a distorção harmonica.  

Para executar o programa, após o download, abra o projeto no eclipse e execute a 
aplicação a partir da classe Main.java dentro do pacote view ou abra a pasta ep2
no terminal e execute os seguintes comandos:  

* Linux:

    >cd src  
    javac view/Main.java  
    java view/Main  


## Menu Inicial

A tela do menu será a primeira a aparecer ao iniciar a execução do programa, nela
o usuário poderá escolher qual simulação deseja fazer pelos botões que redirecionam
para as proximas telas. O usuário poderá voltar a esse menu a qualquer momento pelo
botão "Voltar ao Menu Inicial" presente ao final de todas as telas de simulação.  

## Simular Fluxo de Potência Fundamental

Após escolher esta simulação o usuário deverá preencher os dados da amplitude e 
angulo da tensão e da corrente e, em seguida, apertar o botão "Simular Resultado"
no final da tela. Com isso, os gráficos de onda e os resultados de potência ativa, 
reativa, aparente e fator de potência serão gerados. Além disso, o triangulo de 
potência também será desenhado, nele teremos, em vermelho, a potência ativa, em 
verde a potência reativa e em azul o vetor resultante S.

O usuário poderá escolher também somente visualizar o gráfico de tensão ou o de
corrente. Para isso, abaixo do campo do angulo de fase tem o botão "Simular Forma
de Onda".

## Simular Distorção Harmonica

Ao escolher esta simulação o usuário deverá colocar os valores de amplitude e angulo
da componente fundamental, escolher a quantidade de harmônicos, definir a paridade
e em seguida apertar o botão "OK", com isso, aparecerá uma lista de harmônicos, onde
devem ser preenchidos todos os valores de amplitude, angulo e ordem harmônica para, 
em seguida, apertar o botão "Simular Resultado". Aparecerá então todos os gráficos 
de onda e a equação da série de Fourier.  

Pode-se também simular apenas a forma de onda da componente fundamental, clicando
no botão "Simular Forma de Onda".

## Observações:

Os valores da amplitude da corrente na simulação do fluxo de potência fundamental
deverá ser entre -100 e +100. Todas as outras amplitudes devem estar entre -220 e
+220. Os angulos devem ser entre -180° e + 180°.  

O programa Aprenda QEE foi construído com o auxílio da IDE eclipse.  
