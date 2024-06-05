import asyncio

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .views import create_routes
from .db.engine import create_all, drop_all

app = FastAPI(
    title="FastAPI mini curso",
    version="0.1.0"
)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

create_routes(app)

@app.on_event("startup")
async def startup():
    pass
    # await drop_all()
    # await create_all() 