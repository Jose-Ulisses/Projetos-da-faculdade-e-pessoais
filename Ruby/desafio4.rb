class Livro
    attr_accessor :titulo, :autor
    attr_reader :disponivel

    def initialize(titulo, autor)
        @titulo = titulo
        @autor = autor
        @disponivel = true
    end

    def mostrar 
        p "#{@titulo}, #{@autor} - #{@disponivel}"
    end

    def emprestar 
        @disponivel = false
    end

    def devolver
        @disponivel = true
    end
end

class Biblioteca
    attr_accessor :catalogo

    def initialize
        @catalogo = []
    end

    def adicionar_livro(livro)
        @catalogo << livro
        p "Livro adicionado no catálogo!"
    end

    def listar_livros
        @catalogo.each do |livro|
            status = livro.disponivel ? "disponível" : "já emprestado!"
            p "#{livro.titulo}, #{livro.autor} - #{status}"
        end
    end 

    def emprestar_livro(titulo)
        #buscar o livro
        livro = @catalogo.find{ |livro| livro.titulo && livro.disponivel }
        if livro
            livro.emprestar
        else
            p "livro ja está emprestado!"
        end
    end

    def devolver_livro(titulo)
        livro = @catalogo.find{ |livro| livro.titulo && livro.disponivel }
        if livro
            livro.devolver
        else
            p "livro devolvido!"
        end

    end
end

livro1 = Livro.new("tolkien", "SDA")
livro2 = Livro.new("A1", "Ruby")

biblioteca = Biblioteca.new
biblioteca.adicionar_livro(livro1)
biblioteca.adicionar_livro(livro2)

p "flag 1"

biblioteca.listar_livros
biblioteca.emprestar_livro("tolkien")
biblioteca.listar_livros

p "flag 2"

biblioteca.emprestar_livro("A1")
biblioteca.listar_livros