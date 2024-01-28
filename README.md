<div align="center">
<h1 style="color: red; text-decoration: underline;"><strong>B6B36OMO -- Objektové modelování</strong></h1>
<h2 style="color: red; text-decoration: underline;">Semestrální práce</h2>
<h2 style="color: red; text-decoration: underline;">Smart Home</h2>
<h6>Autoři: 
<a href="https://gitlab.fel.cvut.cz/paspoiva/">Pasportnikov Ivan</a>, 
<a href="https://gitlab.fel.cvut.cz/mashkvla/">Mashkin Vladimir</a>
</h6>
<h1><a href="https://gitlab.fel.cvut.cz/paspoiva/b231_b6b36omo-semestralka/-/blob/main/DESCRIPTION.pdf">DESCRIPTION</a></h1>
<h2 style="color: red; text-decoration: underline;">UML Class Diagram</h2>
<img src="diagrams/SmartHouse.png" alt="UML Class Diagram">
<h2 style="color: red; text-decoration: underline;">Use Case Diagram</h2>
<img src="diagrams/useCase.png" align="center" height="600" width="515" alt="Use Case Diagram"/>

<h2 style="color: red; text-decoration: underline;">Design patterns</h2>
<div align="left">
<dl>
<dt>Singleton</dt>
<dd>
Used to store the basic system state and control of the system (start and end of the simulation, links to the main parts of the system).
</dd>

<dt>Event Bus</dt>
<dd>
Any entity can both subscribe to events and generate events. This allows for a fairly flexible expansion of the system and the addition of new logic.
</dd>

<dt>Observer</dt>
<dd>
In our system, entities whose logic depends on time are subscribed to "time".
</dd>

<dt>State machine</dt>
<dd>
Each device and inhabitant have different states in which their behaviour may differ.
</dd>
</dl>

<dt>Chain of Responsibility</dt>
<dd>
This pattern works in conjunction with the Event Bus, which allows for more flexible event handling.
</dd>

<dt>Builder (nested builder & Factory)</dt>
<dd>
This pattern is used to create the configuration of the house.
</dd>

<dt>Factory method</dt>
<dd>
Used in conjunction with the builder to create items, devices, entrances and houseResidents.
</dd>

<dt>An abstract factory</dt>
<dd>
Used in conjunction with the builder to create different types of sensors (ouside & inside).
</dd>

<dt>Proxy</dt>
<dd>
In our case, the SHSystem class has a "start" method which, after some logic, triggers the "start" method of the Simulation class.
</dd>
</div>

<h2 style="color: red; text-decoration: underline;">Functional requirements</h2>

<div align="left">
<h3>From the assignment:</h3>
<dl>
<dt>F1 - DONE </dt>

<dd>All objects(devices and etc) are located in the objects package.</dd>
<dt>F2 - 50/50 </dt>
<dd>Our devices have no content and fillings. Only electricity consumption and impacting temperature in some cases. Didn't have time for that:( </dd>
<dt>F3 - DONE </dt>
<dt>F4 - DONE </dt>
<dt>F5 - DONE </dt>
<dt>F6 - DONE </dt>
<dt>F7 - DONE </dt>
<dt>F8 - DONE </dt>
<dt>F9 - DONE </dt>
<dt>F10 - DONE </dt>
<h3>Extra:</h3>
<dt>F11 - DONE </dt>
<dd>Real-time simulation with acceleration capability.</dd>
<dt>F12 - DONE </dt>
<dd>The possibility of postponing tasks if no one was able to solve the problem at the time (for example: feeding the pet).</dd>
<dt>F13 - DONE </dt>
<dd>The devices have a limited lifespan and can break down not only because of someone's use, but also because of the passage of time.</dd>
</div>

<h2 style="color: red; text-decoration: underline;">Config</h2>
<p align="left">
The configuration is defined by means of a json file, which is then parsed.
</p>
