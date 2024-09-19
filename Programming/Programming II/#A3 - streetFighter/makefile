# Nome do executável
TARGET = StreetFighter

# Compilador
CC = gcc

# Flags de compilação e linkagem
CFLAGS = $(shell pkg-config allegro-5 allegro_main-5 allegro_font-5 allegro_primitives-5 allegro_image-5 --cflags)
LDFLAGS = $(shell pkg-config allegro-5 allegro_main-5 allegro_font-5 allegro_primitives-5 allegro_image-5 --libs)

# Arquivos fonte
SRCS = StreetFighter.c Personagem.c Joystick.c Menus.c Ataque.c

# Arquivos objeto
OBJS = $(SRCS:.c=.o)

# Regra principal: cria o executável a partir dos arquivos objeto
$(TARGET): $(OBJS)
	$(CC) -o $(TARGET) $(OBJS) $(LDFLAGS)

# Regra para compilar arquivos .c em .o
%.o: %.c
	$(CC) -c $< -o $@ $(CFLAGS)

# Limpeza dos arquivos objeto e do executável
clean:
	rm -f $(OBJS) $(TARGET)

# Regra para recompilar tudo do zero
rebuild: clean $(TARGET)