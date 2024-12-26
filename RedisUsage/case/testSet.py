import pytest
import allure,allure_pytest
from base.base import Base

@allure.epic("Test set type")
class TestSet():
    def setup_class(self):
        self.client = Base()
        print("prepare test")
    @pytest.mark.run(order=1)
    def testSetAdd(self):
        print("Test Set add")
        self.client.redis.delete('s1')
        self.client.redis.delete('s2')
        self.client.redis.sadd('s1',33,34,35)
        self.client.redis.sadd('s2', 33, 34, 36)

    @pytest.mark.run(order=2)
    def testSetGet(self):
        print("Test Set get")
        len= self.client.redis.scard('s1')
        assert  len == 3
        mem = self.client.redis.smembers('s1')
        assert mem == {b'33',b'34',b'35'}
        s1diff = self.client.redis.sdiff('s1','s2')
        assert s1diff == {b'35'}
        s2diff = self.client.redis.sdiff('s2', 's1')
        assert s2diff == {b'36'}


    @pytest.mark.run(order=3)
    def testSetDelet(self):
        print("Test Set delete")

    def teardown_class(self):
        self.client.redis.close()
        print("end test")