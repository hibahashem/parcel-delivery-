# parcel-delivery-
We are trying to find the optimal path to deliver the parcels of a delivery company
Problem modeling:
The city you live in can be shown as a grid that is the dimensions of a rectangular city,
Each cell has either a track (.) or is occupied by a building (#), and you only have one truck (T.)
We'll assume that you also have k packages that you want to deliver where i package has pi pickup location and Di . delivery location
There are 6 operations you can do while roaming
1 - move (up)
2 - move (down)
3 - Move (to the right)
4 - Move (to the left)
5- Receive a parcel.
6- Delivery of parcel.
The goal is to deliver all packages and return to the starting location at the lowest possible cost.
We achieved the goal using smart search algorithms
The first algorithm is the ucs algorithm
The second algorithm is the A* algorithm, where we define a set of functions that perform an empirical computation
heuristic of a situation
