from os import getenv
from pathlib import Path

from pydantic import BaseSettings


class Settings(BaseSettings):
    POSTGRES_URL: str = getenv("POSTGRES_URL", default="sqlite+aiosqlite:///database.db")

    class Config:
        case_sensitive = True
        env_file = f"{Path().absolute()}/.env"


settings = Settings()
