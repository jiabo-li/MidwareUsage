import pytest
import allure,allure_pytest
from base.base import Base

@allure.epic("Test list type")
class TestList():
    def setup_class(self):
        self.client = Base()

    @pytest.mark.run(order=1)
    def testListAdd(self):
        print("Test List set")
        self.client.redis.delete('l1')
        data = [1,2,3,4,5]
        for item in data:
            self.client.redis.rpush('l1',item)


    @pytest.mark.run(order=2)
    def testListGet(self):
        print("Test List set")
        len = self.client.redis.llen('l1')
        print(len)
        assert len == 5
        '''for i in range(5):
            v = self.client.redis.lpop('l1')
            print(v)'''

    @pytest.mark.run(order=3)
    def testListDelet(self):
        print("Test List set")

    def teardown_class(self):
        self.client.redis.close()