import time

import requests
import json

from locust import HttpUser, task, between, events
from random import randrange


class QuickstartUser(HttpUser):
    host = 'http://localhost:8081'
    wait_time = between(0.5, 2)

    @task
    def simulate(self):
        self.login_drivers()

        self.client.put(f"/api/ride/4/start")
        self.client.put(f"/api/ride/9/start")
        time.sleep(15)
        self.client.put(f"/api/ride/10/start")

        while True:
            time.sleep(1)

    # boki and zlata will not be logged in, so that you can test that manually
    def login_drivers(self):
        self.client.post('/api/user/login', json={
            'email': 'danka@gmail.com',
            'password': '123'
        }).json()
        self.client.post('/api/user/login', json={
            'email': 'stepa@gmail.com',
            'password': '123'
        }).json()
        time.sleep(4)
        self.client.post('/api/user/login', json={
            'email': 'smilja@gmail.com',
            'password': '123'
        }).json()
        self.client.post('/api/user/login', json={
            'email': 'duki@gmail.com',
            'password': '123'
        }).json()
        time.sleep(3)
        #todo try adding authorization?