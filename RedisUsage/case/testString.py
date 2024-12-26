import pytest
import allure,allure_pytest
from base.base import Base

@allure.epic("Test string type")
class TestString():
    def setup_class(self):
        self.client = Base()

    @pytest.mark.run(order=1)
    def testStringAdd(self):
        print("Test string set")

        self.client.redis.set("key1","value1")
        value = self.client.redis.get("key1")
        assert value == b"value1"

    @pytest.mark.run(order=2)
    def testStringGet(self):
        print("Test string set")
        value = self.client.redis.get('key1')
        assert value == b'value1'

        value =self.client.redis.get('key2')
        assert value == None

    @pytest.mark.run(order=3)
    def testStringDelet(self):
        print("Test string set")
        self.client.redis.delete('key1')
        value = self.client.redis.get('key1')
        assert value == None


    def teardown_class(self):
        self.client.redis.close()