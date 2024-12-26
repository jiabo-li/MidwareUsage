import pytest
import allure,allure_pytest
from base.base import Base

@allure.epic("Test hash type")
class TestHash():
    def setup_class(self):
        self.client = Base()

    @pytest.mark.run(order=1)
    def testHashAdd(self):
        print("Test Hash set")
        self.client.redis.hset('h1','k1','v1')
        self.client.redis.hset('h1', 'k2', 'v2')

    @pytest.mark.run(order=2)
    def testHashGet(self):
        print("Test Hash set")
        v1 = self.client.redis.hget('h1','k1')
        v2 = self.client.redis.hget('h1', 'k2')

        assert v1 == b'v1' and v2 == b'v2'

    @pytest.mark.run(order=3)
    def testHashDelet(self):
        print("Test Hash set")
        self.client.redis.hdel('h1','k1')
        self.client.redis.hdel('h1', 'k2')
        v1 = self.client.redis.hget('h1', 'k1')
        v2 = self.client.redis.hget('h1', 'k2')

        assert v1 == None and v2 == None
    def teardown_class(self):
        self.client.redis.close()