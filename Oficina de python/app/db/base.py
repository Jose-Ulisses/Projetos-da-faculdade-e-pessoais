from datetime import datetime
from typing import Self

from sqlalchemy import func, update, delete
from sqlalchemy.orm import Mapped
from sqlalchemy.orm import mapped_column

from .engine import get_async_session


class BaseMixin:
    __mapper_args__ = {'always_refresh': True}

    id: Mapped[int] = mapped_column(primary_key=True, autoincrement='auto')

    created_on: Mapped[datetime] = mapped_column(server_default=func.now())
    updated_on: Mapped[datetime] = mapped_column(onupdate=func.now(), nullable=True)

    @property
    def metadatetime(self):
        return self

    async def insert(self, refresh: bool = True) -> Self:
        async with get_async_session() as session:
            session.add(self)
            await session.commit()
            if refresh:
                await session.refresh(self)
                return self

    async def update(self, **kwargs) -> Self:
        async with get_async_session() as session:
            stmt = update(self.__class__).where(
                self.__class__.id == self.id).values(**kwargs)
            await session.execute(stmt)
            await session.commit()
            for key, value in kwargs.items():
                setattr(self, key, value)
            return self

    async def delete(self) -> Self:
        async with get_async_session() as session:
            stmt = delete(self.__class__).where(self.__class__.id == self.id)
            await session.execute(stmt)
            await session.commit()
            return self
