from fastapi import FastAPI

from app.modules.first_example.routes import route as first_example_router


def create_routes(app: FastAPI) -> None:

    app.include_router(first_example_router, tags=['First Example!'])
