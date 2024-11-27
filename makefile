# Definições do compilador e opções de compilação

# Compilador Java
JAVAC = javac
JAVA = java

# Modo de compilação (debug ou release)
MODE ?= debug

ifeq ($(MODE), release)
    JAVAC_OPTIONS = -encoding UTF-8 -Xlint:all -parameters -Xlint:deprecation -Xdoclint:none -g:none
else
    JAVAC_OPTIONS = -g -Xlint:all -parameters -Xlint:deprecation -verbose -sourcepath $(SRC_DIRS)
endif

# Diretórios para código-fonte e saída
SRC_DIRS = src
OUTPUT_DIR = bin

# Definir o nome do executável
OUTPUT_NAME = $(notdir $(CURDIR))
OUTPUT = $(OUTPUT_DIR)/$(OUTPUT_NAME)

# Variáveis para comandos e sistema operacional
ifeq ($(OS),Windows_NT)
    RM = rmdir /q /s
    MKDIR = mkdir
    Cleanup = cls
else
    RM = rm -rf
    MKDIR = mkdir -p
    Cleanup = clear
endif

# Encontrar todos os arquivos .java nos diretórios de src
SOURCES = $(wildcard $(SRC_DIRS)/**/*.java)

# Regras principais
all: $(OUTPUT_DIR) classes

# Criar diretórios se não existirem
$(OUTPUT_DIR):
	@$(MKDIR) $@

# Compilar todos os arquivos .java em uma única linha
classes:
	@echo "Compilando arquivos Java..."
	@$(JAVAC) $(JAVAC_OPTIONS) -d $(OUTPUT_DIR) $(SOURCES)

# Regra para executar o programa
run: all
	@$(Cleanup)
	@echo "Executando o programa..."
	@$(JAVA) -cp $(OUTPUT_DIR) app.App

# Regra para limpeza dos diretórios de saída
clean:
	@echo "Deletando arquivos .class..."
	@if exist "$(OBJ_DIR)\*.class" (del "$(OBJ_DIR)\*.class")
	@echo "Deletando a pasta $(OUTPUT_DIR)..."
	@$(RM) "$(OUTPUT_DIR)" || echo "Pasta não encontrada."

# Regra para gerar documentação (exemplo com javadoc)
docs:
	@echo "Gerando documentação..."
	@javadoc -d docs $(SOURCES)
	@echo "Documentação gerada com sucesso!"

# Regra para inicializar a estrutura de diretórios
init:
	@$(MKDIR) "$(SRC_DIRS)" "$(OUTPUT_DIR)" || echo "Alguns diretórios já existem."
	@$(Cleanup)

.PHONY: all clean run docs init
