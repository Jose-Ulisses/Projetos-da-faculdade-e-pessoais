array_soma = []
while(0 == 0) do
    p "Numero: "
    n = gets.chomp.to_i
    break if n == 0
    array_soma << n
end

p "Escolha uma operação(+ - * /): "
op = gets.chomp

case op
    when '+'
        p array_soma.inject(:+)
    when '-'
        p array_soma.inject(:-)
    when '*'
        p array_soma.inject(:*)
    when '/'
        p array_soma.inject(:/)
    end