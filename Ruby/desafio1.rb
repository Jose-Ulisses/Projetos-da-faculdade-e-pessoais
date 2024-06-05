puts "Informe um ano:"
ano = gets.chomp.to_i

if ano % 4 == 0
    puts "o ano é bissexto!"
else   
    puts "o ano não é bissexto!"
end