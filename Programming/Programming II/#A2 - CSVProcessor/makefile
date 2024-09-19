CC = gcc
CFLAGS =
LDFLAGS = 

# Arquivos-objeto
objects = csvreader.o io.o 

# Regras de construção
all: csvreader

csvreader: $(objects)
	$(CC) -o csvreader $(objects) $(LDFLAGS) -lm

io.o: io.c
	$(CC) -c $(CFLAGS) io.c

csvreader.o: csvreader.c
	$(CC) -c $(CFLAGS) csvreader.c

clean:
	rm -f $(objects) csvreader

purge: clean
	rm -f *.csv

.PHONY: all clean purge