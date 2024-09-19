#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"
#include "aplicacoes.h"

int main() {
    char linha[1024];
    char *aplicacao = NULL; //Ação a ser executada
    char *nomeBase = NULL; //Nome do arquivo base
    char *textoPrefixo = NULL; //Nome do arquivo texto ou prefixo
    FILE *base = NULL;
    FILE *texto = NULL;
    nodo *raizTrie = inicializaTrie();

    /*Executa programa até que usuário entre com uma linha vazia*/
    while ((fgets(linha, sizeof(linha), stdin)) != NULL) {
        
        /*Armazena strings necessárias para a 
        execução do comando inserido pelo usuário*/
        aplicacao = strtok(linha, " \n");
        nomeBase = strtok(NULL, " \n");
        textoPrefixo = strtok(NULL, " \n");

        if (strcmp(aplicacao, "insere") == 0) {

            /*Se a aplicação for inserção, usa o arquivo base (ou cria um novo, 
            caso necessário) correspondente ao nome fornecido pelo usuário na 
            entrada e insere nele as palavras disponíveis no arquivo texto. 
            Caso não encontre o arquivo texto, retorna erro na saída stderr.*/
            base = fopen(nomeBase, "w");
            texto = fopen(textoPrefixo, "r");

            if (texto)
                insereTextoNaTrie(base, texto, textoPrefixo, raizTrie);

            else
                fprintf(stderr, "Arquivo texto não encontrado.\n");
            
            fclose(texto);
            fclose(base);

        } else if (strcmp(aplicacao, "procura") == 0) {

            /*Se a aplicação for procura, busca no arquivo base fornecido as 
            palavras que iniciem com o prefixo e as imprime (sucedidas pelo 
            nome do arquivo que as originou). Caso não encontre o arquivo 
            base, retorna erro na saída stderr.*/
            base = fopen(nomeBase, "r");

            if (base)
                procuraPalavrasPorPrefixo(base, textoPrefixo);
                
            else
                fprintf(stderr, "Arquivo base não encontrado.\n");
            
            fclose(base);

        } else {
            /*Se formato de entrada inserido estiver incorreto, imprime na saída 
            de erros o formato requerido para correto funcionamento do programa*/
            fprintf(stderr, "Formato de entrada requerido para inserção:\ninsere arquivoBase arquivoTexto\n\n");
            fprintf(stderr, "Formato de entrada requerido para busca:\nprocura arquivoBase prefixo\n");
        }
    }
    
    destroiTrie(raizTrie);

    return 0;
}

