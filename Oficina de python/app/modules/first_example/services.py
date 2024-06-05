
from app.utils.exceptions import DivisaoPorZeroException, OperacaoInvalidaException


class FirstExampleService():
    
    def calcula(self, num1: float,num2: float, op: str):
        match op:
            case '+': return num1 + num2
            case '-': return num1 - num2
            case '*': return num1 * num2
            case '/':
                if num2 == 0:
                    raise DivisaoPorZeroException(Exception)

                return num1 / num2
            case _: raise OperacaoInvalidaException("Essa operação é inválida!")