from pydantic import BaseModel
from datetime import datetime


docs_example = {
    "created_on": "2020-01-01T00:00:00.000001",
    "updated_on": "2020-01-01T00:00:00.000001"
}


class MetaDatetimeSchema(BaseModel):
    created_on: datetime | None 
    updated_on: datetime | None 

    class Config:
        orm_mode = True


class BaseSchema(BaseModel):

    class Config:
        orm_mode = True
