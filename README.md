# Olympic Event Tree
A Java program that tracks Olympic sports, events, and medalists with the use of a hierarchical tree. Supports queries to output specific information by recursively traversing the tree to gather information.

## Overview
This program reads data from a file input and builds a tree with the hierarchy:
Olympic year > Sports > Events > Medalists

- Sports and Events are maintained in alphabetical order.
- Each event contains three medalists in "name:country" format, kept in the order they were entered, to maintain gold-silver-bronze order.

### Methods
After the tree is built, the program reads a second file containing queries, then calls methods to output the desired information about events, athletes, and countries.
- Get events by sport
- Get winners and countries by sport and event
- Get gold medalist and country by sport and event
- Get athlete with most medals or gold medals
- Get country with most medals or gold medals
- Get sport and event by athlete


