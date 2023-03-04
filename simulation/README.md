# Vehicle movement simulation
## How to run the script:

Make sure that you have started backend and frontend before activating the script

Linux & MacOS: <br/>
- **virtualenv vehicle-simulation** <br/>
- **source vehicle-simulation/bin/activate** 

Windows: <br/>
- **virtualenv vehicle-simulation** <br/>
- **vehicle-simulation\Scripts\activate** -> using cmd <br/>
- **source vehicle-simulation\Scripts\activate** -> using git bash

After activating the virtualenv environment, install the following libraries: <br/>
- **pip3 install locust** <br/>
- **pip3 install requests**

Run script with the command: <br/>
- **locust -f simulation.py --headless**
