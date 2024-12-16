# Nome do arquivo principal (Main.java)
MAIN = Main

# Regras padrão
.PHONY: all compile run prune clean

# Regra principal
all: compile

# Compila o programa
compile:
	javac $(MAIN).java

# Executa o programa
run: compile
	java $(MAIN)

# Remove todos os arquivos .class no diretório atual e subdiretórios
prune:
	find . -name "*.class" -delete

# Limpeza geral (pode incluir outros arquivos ou operações, se necessário)
clean: prune
