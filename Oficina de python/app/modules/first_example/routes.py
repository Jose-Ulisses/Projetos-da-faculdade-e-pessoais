from http.client import HTTPException
from app.modules.first_example.services import FirstExampleService
from app.utils.exceptions import DivisaoPorZeroException, OperacaoInvalidaException
from fastapi import APIRouter # type: ignore

route = APIRouter()
service = FirstExampleService()

@route.get("/ola", response_model = str, status_code = 200)
async def hello_world():
    """
    ## Diz oi!
    """

    return "Hello World!"

@route.post("/calculadora", response_model = float, status_code = 201)
async def calculadora(num1: float, num2: float, op: str):
    """
    ## Calcula uma operação entre dois numeros.

    ### Argumentos:
     > num1 (float)  
     > num2 (float)  
     > op (str)  

    ### rotorna:
     > resultado (float)  
    """
    try:
        return service.calcula(num1, num2, op)
    except OperacaoInvalidaException as erro:
        raise HTTPException(status_code = 422, detail = str(erro))
    except DivisaoPorZeroException as erro:
        raise HTTPException(status_code = 409, )