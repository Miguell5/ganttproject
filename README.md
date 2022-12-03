-----> Iniciar o sub repositorio SE2223_59797_60441_60677_60816_60971 <----- 
Para visualizar os ficheiro da pasta project é necessário inicializar o submodule

git submodule init

git submodule update --remote

-----> Alterar o ficheiro excel a importar <-----
 
    1º Na classe HelpMenu.java inserir o nome do novo ficheiro (package: net.sourceforge.ganttproject.action.help)
    2º Na pasta ganttproject-builder/dist-bin inserir o novo ficheiro excel

-----> Regras de formatação do ficheiro excel <-----

    1º Os recursos têm de estar na primeira folha do ficheiro
    2º Os campos dos recursos têm de estar na seguinte ordem:
        - Nome
        - Telefone
        - Email
        - Preço/hora
        - Função
    3º O utilizador pode inserir qualquer função para os seus recursos. Se esta existir, será usada uma função
        do sistema, caso contrário, uma nova função será criada no sistema, podendo esta ser então atribuida depois
        a outros recursos
 
-----> Identificação do grupo <-----
Miguell5 -> Miguel Agostinho 60677
dannythe21st/ Daniel Eugénio -> Daniel Eugénio 59797
guibati -> Guilherme Abrantes 60971
rcosta349 -> Rafael Costa 60441
fjsilveira -> Francisco Silveira 60816
