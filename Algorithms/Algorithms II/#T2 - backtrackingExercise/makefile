parametrosCompilacao=-Wall #-Wshadow
nomePrograma=main

all: $(nomePrograma)

$(nomePrograma): main.o
	gcc -o $(nomePrograma) main.o $(parametrosCompilacao)

main.o: main.c
	gcc -c main.c $(parametrosCompilacao)

clean:
	rm -f *.o *.gch $(nomePrograma)
