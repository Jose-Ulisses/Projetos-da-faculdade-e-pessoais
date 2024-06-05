# Mini curso de FastAPI

Esse código serve como uma introdução a construção de REST APIs usando Python com FastAPI + SQLAlchemy. Esse código está direcionado à execução de um mini-curso ministrado aos alunos do curso de Ciência da Computação da PUC Minas, campus Poços de Caldas. 

## Antes de começar

Esse curso presume conhecimento prévio de programação em Python. Conhecimento de bancos de dados é desejável, mas não requerido.

### Pré-requisitos

Para poder executar o programa, deve-se instalar:
- [Python 3.10+](https://www.python.org/downloads/)
- [Poetry](https://python-poetry.org/docs/#installation)
- [PostgreSQL](https://www.postgresql.org/download/)

### Instalando

Após clonar o repositório, instale as dependências com:

    poetry install

Para executá-lo, é necessário rodar o seguinte comando:

    poetry run uvicorn app.main:app --port 8000 --reload

### Happy coding!