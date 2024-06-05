puts "Qual é a sua idade?"
idade = gets.chomp.to_i
puts "Qual é o seu nome?"
nome = gets.chomp

if idade >= 18
    puts "#{nome} é maior de idade"
else
    puts "#{nome} é menor de idade"
end

numeros = [1,2,3,4,5]
numeros.each do |numero|
    puts "Numero: #{numero}"
end



