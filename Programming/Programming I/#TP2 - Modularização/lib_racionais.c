#include <stdio.h>
#include "lib_racionais.h"

racional leRacional(racional racionalLido) {
    scanf("%d %d", &racionalLido.num, &racionalLido.den);
    return racionalLido;
}

int validaRacional(racional fracao) {
    if (fracao.den == 0) {
        printf("Entrada inválida\n");
        return 1;
    }
    else {
        return 0;
    };
}

int imprimeRacional(racional fracao) {
    if (fracao.num == 0) {
        printf("0\n");
    }
    else if (fracao.den == 1) {
        printf("%d\n", fracao.num);
    }
    else {
        printf("%d/%d\n", fracao.num, fracao.den);
    };
    return 0;
}

int calculaMDC (int valorUm, int valorDois) {
    int aux = valorUm % valorDois;
	while (aux != 0) {
		valorUm = valorDois;
		valorDois = aux;
		aux = valorUm % valorDois;
	};
	int MDC = valorDois;
	return MDC;
}

racional simplificaRacional(racional racionalResultante, racional racionalSimplificada) {
    int MDC = calculaMDC(racionalResultante.num, racionalResultante.den);
    racionalSimplificada.num = racionalResultante.num / MDC;
    racionalSimplificada.den = racionalResultante.den / MDC;
    return racionalSimplificada;
}

int somaFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada) {
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante.num = ((racionalResultante.den / racional1.den) * racional1.num) + ((racionalResultante.den / racional2.den) * racional2.num);
    racionalSimplificada = simplificaRacional(racionalResultante, racionalSimplificada);
    imprimeRacional(racionalSimplificada);
    return 0;
}

int subtraiFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada) {
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante.num = ((racionalResultante.den / racional1.den) * racional1.num) - ((racionalResultante.den / racional2.den) * racional2.num);
    racionalSimplificada = simplificaRacional(racionalResultante, racionalSimplificada);
    imprimeRacional(racionalSimplificada);
    return 0;
}

int multiplicaFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada) {
    racionalResultante.num = racional1.num * racional2.num;
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante = simplificaRacional(racionalResultante, racionalSimplificada);
    imprimeRacional(racionalResultante);
    return 0;
}

int divideFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada) {
    racionalResultante.num = racional1.num * racional2.den;
    racionalResultante.den = racional1.den * racional2.num;
    racionalResultante = simplificaRacional(racionalResultante, racionalSimplificada);
    imprimeRacional(racionalResultante);
    return 0;
}