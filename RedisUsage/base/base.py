import redis

class Base:
    def __init__(self):

        self.host = ""
        self.port = 6379
        self.password = "123456"
        self.redis = self.connect()

    def connect(self):
        con = redis.Redis(host=self.host, port=self.port, db=0, password=self.password)
        return con