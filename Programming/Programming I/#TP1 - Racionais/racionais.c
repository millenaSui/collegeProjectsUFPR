#include <stdio.h>

typedef struct rac {
    int num;
    int den;
} tipoAbsDados;

int verificaDenNum(tipoAbsDados racional) {
    if (racional.den == 0) {
        printf("Entrada inválida\n");
        return 1;
    }
    else if (racional.num == 0) {
        printf("0\n");
    }
    else if (racional.den == 1) {
        printf("%d\n", racional.num);
    }
    else {
        printf("%d/%d\n", racional.num, racional.den);
    };
    return 0;
}

int leRetornaRacional(tipoAbsDados racional) {
    scanf("%d %d", &racional.num, &racional.den);
    if (racional.den != 0) {
        verificaDenNum(racional);
        printf("%d/%d\n", racional.num, racional.den);
        return 0;
    }
    else {
        printf("Entrada inválida");
        return 1;
    };
}

int calculaMDC (int valor1, int valor2){
    int aux = valor1 % valor2;
	while (aux != 0) {
		valor1 = valor2;
		valor2 = aux;
		aux = valor1 % valor2;
	};
	int MDC = valor2;
	return MDC;
}

tipoAbsDados simplificacaoFracionaria(tipoAbsDados racionalResultante, tipoAbsDados racionalSimplificada){
    int MDC = calculaMDC(racionalResultante.num, racionalResultante.den);
    racionalSimplificada.num = racionalResultante.num / MDC;
    racionalSimplificada.den = racionalResultante.den / MDC;
    return racionalSimplificada;
}

int somaFracionaria(tipoAbsDados racional1, tipoAbsDados racional2, tipoAbsDados racionalResultante, tipoAbsDados racionalSimplificada) {
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante.num = ((racionalResultante.den / racional1.den) * racional1.num) + ((racionalResultante.den / racional2.den) * racional2.num);
    racionalSimplificada = simplificacaoFracionaria(racionalResultante, racionalSimplificada);
    printf("O resultado da soma dos racionais é: ");
    verificaDenNum(racionalSimplificada);
    return 0;
}

int subtracaoFracionaria(tipoAbsDados racional1, tipoAbsDados racional2, tipoAbsDados racionalResultante, tipoAbsDados racionalSimplificada) {
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante.num = ((racionalResultante.den / racional1.den) * racional1.num) - ((racionalResultante.den / racional2.den) * racional2.num);
    racionalSimplificada = simplificacaoFracionaria(racionalResultante, racionalSimplificada);
    printf("O resultado da subtração dos racionais é: ");
    verificaDenNum(racionalSimplificada);
    return 0;
}

int multiplicacaoFracionaria(tipoAbsDados racional1, tipoAbsDados racional2, tipoAbsDados racionalResultante, tipoAbsDados racionalSimplificada) {
    racionalResultante.num = racional1.num * racional2.num;
    racionalResultante.den = racional1.den * racional2.den;
    racionalResultante = simplificacaoFracionaria(racionalResultante, racionalSimplificada);
    printf("O resultado da multiplicação dos racionais é: ");    
    verificaDenNum(racionalResultante);
    return 0;
}

int divisaoFracionaria(tipoAbsDados racional1, tipoAbsDados racional2, tipoAbsDados racionalResultante, tipoAbsDados racionalSimplificada) {
    racionalResultante.num = racional1.num * racional2.den;
    racionalResultante.den = racional1.den * racional2.num;
    racionalResultante = simplificacaoFracionaria(racionalResultante, racionalSimplificada);
    printf("O resultado da divisão dos racionais é: ");
    verificaDenNum(racionalResultante);
    return 0;
}

int main() {
    tipoAbsDados racional1, racional2, racionalResultante, racionalSimplificada;
    scanf("%d %d", &racional1.num, &racional1.den);
    verificaDenNum(racional1);
    if (racional1.den != 0) {
        scanf("%d %d", &racional2.num, &racional2.den);
        verificaDenNum(racional2);
        while (racional1.den != 0 && racional2.den != 0) {
            somaFracionaria(racional1, racional2, racionalResultante, racionalSimplificada);
            subtracaoFracionaria(racional1, racional2, racionalResultante, racionalSimplificada);
            multiplicacaoFracionaria(racional1, racional2, racionalResultante, racionalSimplificada);
            divisaoFracionaria(racional1, racional2, racionalResultante, racionalSimplificada);
            scanf("%d %d", &racional1.num, &racional1.den);
            verificaDenNum(racional1);
            if (racional1.den != 0) {
                scanf("%d %d", &racional2.num, &racional2.den);
                verificaDenNum(racional2);
            };    
        };
    };    
    return 0;
}