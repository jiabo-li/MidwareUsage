import time
import os

if __name__ == '__main__':
    os.system('pytest')
    os.system('allure generate report/ -o report/html --clean')