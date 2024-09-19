#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "libconjunto.h"
#include "libfila.h"
#include "liblef.h"

/*valores de cada evento*/
#define CHEGADA 1
#define SAIDA 2
#define MISSAO 3
#define FIM 4

/*entidades e atributos*/
typedef struct {
    int id;
    int paciencia;
    int idade;
    int experiencia;
    conjunto_t *conjunto_de_habilidades;
} heroi_t;

typedef struct {
    int id;
    int lotacao_maxima;
    conjunto_t *herois_no_lugar;
    fila_t *fila;
    /*coordenada da 
    localizacao*/
    int loc_x;
    int loc_y;
} local_t;

typedef struct {
    int tempo_atual;
    heroi_t *herois;
    local_t *locais;
    conjunto_t *habilidades_existentes;
    int n_herois;
    int n_locais;
    int n_tamanho_mundo;
    int n_missoes;
    int fim_do_mundo;
} mundo_t;

/*lista de funcoes utilizadas*/
int aleat(int x, int y);
int local_lotado(int id_local, mundo_t *vought);
int decide_permanencia(int id_heroi, int id_local, mundo_t *vought);
int LEF_vazia(lef_t *l);
int max(int x, int y);
int tempo_permanencia(int id_heroi, int id_local, mundo_t *vought);
int velocidade(int id_heroi, mundo_t *vought);
int distancia(int origem, int destino, mundo_t *vought);
int tempo_deslocamento(int id_heroi, int id_local_origem, int id_local_destino, mundo_t *vought);
conjunto_t *uniao_de_habilidades(int cont_locais, conjunto_t *equipe_atual, mundo_t *vought);
conjunto_t *equipe_apta(int cont_locais, conjunto_t *missao, local_t **local_missao, conjunto_t *equipe_atual, conjunto_t *equipe_apta, mundo_t *vought);
conjunto_t *escolhe_equipe(int id_missao, conjunto_t *missao, local_t *local_missao, mundo_t *vought);
void incrementa_experiencia_herois(local_t local_missao, conjunto_t *menor_equipe, mundo_t *vought);

/*inicializa entidades e atributos*/
heroi_t init_heroi(int id, conjunto_t *conjunto_de_habilidades) {
    heroi_t sup;
    sup.id = id;
    sup.experiencia = 0;
    sup.paciencia = aleat(0, 100);
    sup.idade = aleat(18, 100);
    sup.conjunto_de_habilidades = cria_subcjt_cjt(conjunto_de_habilidades, aleat(2, 5));
    return sup;
}

local_t init_local(int id, int tamanho_mundo) {
    local_t l;
    l.id = id;
    l.lotacao_maxima = aleat(5, 30);
    l.loc_x = aleat(0, tamanho_mundo-1);
    l.loc_y = aleat(0, tamanho_mundo-1);
    l.herois_no_lugar = cria_cjt(l.lotacao_maxima);
    l.fila = cria_fila();
    return l;
}

mundo_t *init_mundo(lef_t *lista_eventos) {
    int i;
    mundo_t *vought;
    vought = malloc(sizeof(mundo_t));

    /*inicializa valores dos atributos e entidades*/
    (*vought).tempo_atual = 0;
    (*vought).n_tamanho_mundo = 20000;

    int n_habilidades = 10;
    (*vought).habilidades_existentes = cria_cjt(n_habilidades);

    (*vought).n_herois = n_habilidades * 5;
    (*vought).herois = malloc((*vought).n_herois * sizeof(heroi_t));

    (*vought).n_locais = (*vought).n_herois / 6;
    (*vought).locais = malloc((*vought).n_locais * sizeof(local_t));

    (*vought).fim_do_mundo = 34944;
    (*vought).n_missoes = (*vought).fim_do_mundo / 100;

    /*completa vetor de habilidades*/
    for (i = 0; i < n_habilidades; i++)
        insere_cjt((*vought).habilidades_existentes, i);

    /*completa vetor de herois*/
    for (i = 0; i < (*vought).n_herois; i++)
        (*vought).herois[i] = init_heroi(i, (*vought).habilidades_existentes);

    /*completa vetor de locais*/
    for (i = 0; i < (*vought).n_locais; i++)
        (*vought).locais[i] = init_local(i, (*vought).n_tamanho_mundo);

    /*cria evento de chegada e insere na LEF*/
    for (i = 0; i < (*vought).n_herois; i++) {
        evento_t evento_chegada = {aleat(0, 96*7), CHEGADA, ((*vought).herois[i]).id, aleat(0, (*vought).n_locais-1)};
        adiciona_ordem_lef(lista_eventos, &evento_chegada);
    }

    /*cria evento de missao e insere na LEF*/
    for (i = 0; i < (*vought).n_missoes; i++) {
        evento_t evento_missao = {aleat(0, (*vought).fim_do_mundo), MISSAO, i, 0};
        adiciona_ordem_lef(lista_eventos, &evento_missao);
    }

    /*cria evento de fim de mundo e insere na LEF*/
    evento_t evento_fim = {(*vought).fim_do_mundo, FIM, 0, 0};
    adiciona_ordem_lef(lista_eventos, &evento_fim);

    return vought;
}

/*funcoes listadas*/
int aleat(int x, int y) {
    return rand() % (y + 1 - x) + x;
}

int local_lotado(int id_local, mundo_t *vought) {
    if (cardinalidade_cjt((*vought).locais[id_local].herois_no_lugar) >= (*vought).locais[id_local].lotacao_maxima)
        return 1;
    return 0;
}

int decide_permanencia(int id_heroi, int id_local, mundo_t *vought) {
    if ((*vought).herois[id_heroi].paciencia/4 > tamanho_fila((*vought).locais[id_local].fila))
        return 1;
    return 0;
}

int LEF_vazia(lef_t *l) {
    if ((*l).Primeiro == NULL)
        return 1;
    return 0;
}

int max(int x, int y) {
    if (x > y)
        return x;
    return y;
}

int tempo_permanencia(int id_heroi, int id_local, mundo_t *vought) {
    return max(1, (*vought).herois[id_heroi].paciencia/10 + aleat(-2, 6));
} /*calcula TPL*/

int velocidade(int id_heroi, mundo_t *vought) {
    return 100 - max(0, (*vought).herois[id_heroi].idade - 40);
} 

int distancia(int origem, int destino, mundo_t *vought) {
    int x = (*vought).locais[destino].loc_x - (*vought).locais[origem].loc_x;
    int y = (*vought).locais[destino].loc_y - (*vought).locais[origem].loc_y;
    int quad = (x * x) + (y * y);
    return sqrt((double)quad);
}

int tempo_deslocamento(int id_heroi, int id_local_origem, int id_local_destino, mundo_t *vought) {
    return distancia(id_local_origem, id_local_destino, vought)/velocidade(id_heroi, vought);
} /*calcula TDL*/

conjunto_t *uniao_de_habilidades(int cont_locais, conjunto_t *equipe_atual, mundo_t *vought) {
    int i, id_heroi;
    conjunto_t *equipe_aux;

    /*inicia iterador com o primeiro heroi do local, adicionando 
    suas habilidades ao conjunto da equipe analisada*/
    inicia_iterador_cjt((*vought).locais[cont_locais].herois_no_lugar);
    incrementa_iterador_cjt((*vought).locais[cont_locais].herois_no_lugar, &id_heroi);
    equipe_atual = copia_cjt((*vought).herois[id_heroi].conjunto_de_habilidades);

    /*faz a uniao das habilidades do restante dos herois no local e
    libera a memoria utilizada em cada passo da uniao*/
    for (i = 1; i < cardinalidade_cjt((*vought).locais[cont_locais].herois_no_lugar); i++) {
        equipe_aux = equipe_atual;
        incrementa_iterador_cjt((*vought).locais[cont_locais].herois_no_lugar, &id_heroi);
        equipe_atual = uniao_cjt(equipe_atual, (*vought).herois[id_heroi].conjunto_de_habilidades);
        destroi_cjt(equipe_aux);
    }

    return equipe_atual;
}

conjunto_t *equipe_apta(int cont_locais, conjunto_t *missao, local_t **local_missao, conjunto_t *equipe_atual, conjunto_t *equipe_apta, mundo_t *vought) {
    
    /*se as habilidades requeridas para a missao estao contidas no 
    conjunto de habilidades dos herois da equipe analisada*/
    if (contido_cjt(missao, equipe_atual)) {

        /*se ainda nao ha menor equipe apta OU se equipe analisada eh menor que a menor equipe apta 
        previamente escolhida, menor equipe eh substituida por equipe que esta sendo analisada*/
        if (vazio_cjt(equipe_apta) || cardinalidade_cjt(equipe_atual) < cardinalidade_cjt(equipe_apta)) {
                **local_missao = (*vought).locais[cont_locais];
                destroi_cjt(equipe_apta);
                equipe_apta = copia_cjt(equipe_atual);
        }
    }
    return equipe_apta;
}

conjunto_t *escolhe_equipe(int id_missao, conjunto_t *missao, local_t *local_missao, mundo_t *vought) {
    int cont_locais = ((*vought).n_locais) - 1;

    /*cria conjunto de menor equipe e conjunto auxiliar para 
    auxiliar na analise de possivel equipe secundaria*/
	conjunto_t *menor_equipe = cria_cjt(cardinalidade_cjt((*vought).habilidades_existentes));
	conjunto_t *equipe_atual;
	
    /*analisa equipes (herois presentes) em cada local do mundo
    se ha herois no local, analisa se eh possivel formar equipe apta
    se nao ha herois no local, pula para o proximo local a ser analisado*/
	while (cont_locais >= 0) {
		if (!(vazio_cjt((*vought).locais[cont_locais].herois_no_lugar))) {

            /*faz a uniao das habilidades dos herois no local analisado e as imprime*/
            equipe_atual = uniao_de_habilidades(cont_locais, equipe_atual, vought);
            printf("%6d:MISSAO %3d HAB_EQL %d:", (*vought).tempo_atual, id_missao, (*vought).locais[cont_locais].id);
            imprime_cjt(equipe_atual);

            /*em caso de existir apenas uma equipe, verifica se ela eh apta, em caso de mais de uma 
            equipe, compara qual eh a menor, a retorna e destroi o conjunto da equipe menos apta*/
            menor_equipe = equipe_apta(cont_locais, missao, &local_missao, equipe_atual, menor_equipe, vought);
            destroi_cjt(equipe_atual);
        }
        cont_locais--;
	}
	return menor_equipe;
}

void incrementa_experiencia_herois(local_t local_missao, conjunto_t *menor_equipe, mundo_t *vought) {
    int i, id_heroi;

    inicia_iterador_cjt(local_missao.herois_no_lugar);
    for (i = 0; i < cardinalidade_cjt(local_missao.herois_no_lugar); i++) {
        incrementa_iterador_cjt(local_missao.herois_no_lugar, &id_heroi);
        ((*vought).herois[id_heroi].experiencia)++;
    }
}

/*tratamento de eventos*/
void evento_chegada(int id_heroi, int id_local, lef_t *lista_eventos, mundo_t *vought) {

    /*imprime chegada do heroi*/
    printf("%6d:CHEGA HEROI %2d Local %d (%2d/%2d), ", (*vought).tempo_atual, id_heroi, id_local, 
    cardinalidade_cjt((*vought).locais[id_local].herois_no_lugar), (*vought).locais[id_local].lotacao_maxima);

    /*em caso de local lotado, heroi decide entre ficar na fila
    (caso tenha paciencia) ou ir embora (caso nao tenha)*/
    if (local_lotado(id_local, vought)) {
        if (decide_permanencia(id_heroi, id_local, vought)) {
            insere_fila((*vought).locais[id_local].fila, id_heroi);
            printf("FILA %d\n", tamanho_fila((*vought).locais[id_local].fila));
        } else {
            evento_t evento_saida = {(*vought).tempo_atual, SAIDA, id_heroi, id_local};
            adiciona_ordem_lef(lista_eventos, &evento_saida);
            printf("DESISTIR\n");
        }
        return;

    /*caso contrario, heroi entra no local, decide seu tempo de 
    permanencia e escalona evento de saida ao final do tempo decidido*/    
    } else {
        insere_cjt((*vought).locais[id_local].herois_no_lugar, id_heroi);
        printf("ENTRA\n");
        evento_t evento_saida = {(*vought).tempo_atual + tempo_permanencia(id_heroi, id_local, vought), SAIDA, id_heroi, id_local};
        adiciona_ordem_lef(lista_eventos, &evento_saida);
    }
}

void evento_saida(int id_heroi, int id_local, lef_t *lista_eventos, mundo_t *vought) {
    int destino, id_heroi_fila;

    /*se heroi esta no local, o retira*/
    if (pertence_cjt((*vought).locais[id_local].herois_no_lugar, id_heroi)) {
        retira_cjt((*vought).locais[id_local].herois_no_lugar, id_heroi);

        /*imprime saida do heroi*/
        printf("%6d:SAIDA HEROI %2d Local %d (%2d/%2d)\n", (*vought).tempo_atual, id_heroi, id_local,
        cardinalidade_cjt((*vought).locais[id_local].herois_no_lugar), (*vought).locais[id_local].lotacao_maxima);

        /*se fila nao esta vazia, cria evento de chegada pro novo heroi 
        a entrar no local e insere no inicio da LEF*/
        if (!(vazia_fila((*vought).locais[id_local].fila))) {
            retira_fila((*vought).locais[id_local].fila, &id_heroi_fila);
            printf(", REMOVE FILA HEROI %d\n", id_heroi_fila);
            evento_t chegada = {(*vought).tempo_atual, CHEGADA, id_heroi_fila, id_local};
            adiciona_inicio_lef(lista_eventos, &chegada);
        }     
    }

    /*escolhe aleatoriamente local de destino para heroi que saiu e insere evento na LEF*/
    destino = aleat(0, (*vought).n_locais-1);
    evento_t chegada = {(*vought).tempo_atual + tempo_deslocamento(id_heroi, id_local, destino, vought)/15, CHEGADA, id_heroi, id_local};
    adiciona_ordem_lef(lista_eventos, &chegada);
}

void evento_missao(int id_missao, lef_t *lista_eventos, mundo_t *vought) {

    /*cria missao como subconjunto de habilidades existentes no mundo e a imprime*/
    conjunto_t *missao = cria_subcjt_cjt((*vought).habilidades_existentes, aleat(3, 6));
    printf("%6d:MISSAO %3d HAB_REQ ", (*vought).tempo_atual, id_missao);
    imprime_cjt(missao);
    
    /*escolhe menor equipe apta para realizar a missao*/
    local_t local_missao;
    conjunto_t *menor_equipe = escolhe_equipe(id_missao, missao, &local_missao, vought);
    printf("%6d:MISSAO %3d ", (*vought).tempo_atual, id_missao);
    
    /*se equipe_atual apta existe, imprime seus herois 
    e atualiza a experiencia de cada um deles*/
    if (!(vazio_cjt(menor_equipe))) {
        printf("HER_EQS %d:", local_missao.id);
        imprime_cjt(local_missao.herois_no_lugar);
        incrementa_experiencia_herois(local_missao, menor_equipe, vought);
          
    /*se equipe_atual apta nao existe, recria evento 
    missao entre tempo atual e fim do mundo*/
    } else {
        printf("IMPOSSIVEL\n");
        evento_t recria_evento = {aleat((*vought).tempo_atual, (*vought).fim_do_mundo), MISSAO, id_missao, 0};
        adiciona_ordem_lef(lista_eventos, &recria_evento);
    }
    destroi_cjt(missao);
    destroi_cjt(menor_equipe);
}

void evento_fim(lef_t **lista_eventos, mundo_t *vought) {
    int i;
    printf("%d: FIM\n", (*vought).fim_do_mundo);

    /*imprime experiencia final de cada heroi e
    destroi conjunto herois, habilidades, locais e fila*/
    for (i = 0; i < (*vought).n_herois; i++) {
        printf("HEROI %2d EXPERIENCIA %2d\n", (*vought).herois[i].id, (*vought).herois[i].experiencia);
        destroi_cjt((*vought).herois[i].conjunto_de_habilidades);
    }
    free((*vought).herois);

    for (i = 0; i < (*vought).n_locais; i++) {
        destroi_cjt((*vought).locais[i].herois_no_lugar);
        destroi_fila((*vought).locais[i].fila);
    }
    free((*vought).locais);

    /*liberacao de allocs do mundo e destruicao da LEF*/
    destroi_cjt((*vought).habilidades_existentes);
    free(vought);
    *lista_eventos = destroi_lef(*lista_eventos);
}


/*programa principal*/
int main () {
    srand(time(0));
    lef_t *lista_eventos = cria_lef();
    mundo_t *vought = init_mundo(lista_eventos);
    evento_t *evento_atual;

    while (lista_eventos && (evento_atual = obtem_primeiro_lef(lista_eventos))) {
        (*vought).tempo_atual = (*evento_atual).tempo;

        switch ((*evento_atual).tipo) {
            case CHEGADA:
                evento_chegada((*evento_atual).dado1, (*evento_atual).dado2, lista_eventos, vought);
                break;
            case SAIDA:
                evento_saida((*evento_atual).dado1, (*evento_atual).dado2, lista_eventos, vought);
                break;
            case MISSAO:
                evento_missao((*evento_atual).dado1, lista_eventos, vought);
                break;
            case FIM:
                evento_fim(&lista_eventos, vought);
                break;
        }
        free(evento_atual);
    }
    return 0;
}