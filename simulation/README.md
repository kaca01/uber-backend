## Vehicle movement simulation
### How to run the script:

Make sure that you have started backend and frontend before activating the script

**Linux & MacOS**:
- virtualenv vehicle-simulation
- source vehicle-simulation/bin/activate

**Windows**:
- virtualenv vehicle-simulation
- vehicle-simulation\Scripts\activate -> using cmd
- source vehicle-simulation\Scripts\activate -> using git bash

After activating the virtualenv environment, install the following libraries:
- pip3 install locust
- pip3 install requests

Run script with the command:
- locust -f simulation.py --headless
