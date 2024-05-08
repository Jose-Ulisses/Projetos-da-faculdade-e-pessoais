array_soma = []
while(0 == 0) do
    p "Numero: "
    n = gets.chomp.to_i
    break if n == 0
    array_soma << n
end

resultado = 0
#1° opção
#array_soma.each do |array_soma|
#   resultado += array_soma
#end
#puts "resultado da soma: #{resultado}"

#2° opção
#p array_soma.inject(:+)

#3° opção
p array_soma.sum