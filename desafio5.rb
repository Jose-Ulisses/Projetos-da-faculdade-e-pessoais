class Carro
    attr_accessor :marca, :modelo
    attr_reader :disponivel

    def initialize(marca, modelo)
        @marca = marca
        @modelo = modelo
        @disponivel = true
    end

    def alugado
        @disponivel = false
        p "Carro alugado com sucesso!"
    end

    def devolver
        @disponivel = true
        p "Carro devolvido com sucesso!"
    end
end

class Concessionaria
    attr_accessor :catalogo

    def initialize 
        @catalogo = []
    end

    def adicionar_carro(carro)
        @catalogo << carro
        puts "\n"
        p "Carro adicionado!"
    end

    def listar_carros
        @catalogo.each do |carro|
            status = carro.disponivel ? "disponível" : "já alugado!"
            p "#{carro.marca}, #{carro.modelo} - #{status}"
            puts "\n"
        end
    end


    def alugar_carro(modelo)
        carro = @catalogo.find{ |carro| carro.modelo == modelo && carro.disponivel }
        if carro
            carro.alugado
        else
            p "Carro já está alugado ou não existe!"
        end
    end

    def devolver_carro(modelo)
        carro = @catalogo.find{ |carro| carro.modelo && !carro.disponivel }
        if carro
            carro.devolver
        else
            p "Carro já foi devolvido ou não existe!"
        end
    end

end

concessionaria = Concessionaria.new

while(0 == 0)
    puts "\n\n"
    p "---  Aluguel de carros!  ---"
    p "[1] - Adicionar carro"
    p "[2] - Alugar carro"
    p "[3] - Listar carros"
    p "[4] - Devolver carro"
    p "[5] - Sair"
    
    op = gets.chomp.to_i

    case op
        when 1
            puts "\n"
            p "Informe a marca: "
            marca = gets.chomp
            p "informe o modelo: "
            modelo = gets.chomp
            carro = Carro.new(marca, modelo)

            concessionaria.adicionar_carro(carro)

        when 2
            puts "\n"
            p "informe o modelo a ser alugado: "
            modelo = gets.chomp
            concessionaria.alugar_carro(modelo)

        when 3
            concessionaria.listar_carros

        when 4
            puts "\n"
            p "Informe o modelo a ser devolvido: "
            modelo = gets.chomp
            concessionaria.devolver_carro(modelo)

        when 5
            p "Saindo..."
            break
    end
end