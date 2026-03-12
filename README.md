# Olympic Event Tree
A Java program that tracks Olympic sports, events, and medalists using a hierarchical tree. Supports queries that output specific information by recursively traversing the tree.

## Overview
This program reads data from a data file and builds a tree with the hierarchy:
```
Olympic year > Sports > Events > Medalists
```
Each line of the data file input is formatted as:
```
parent child child ...
```
- Sports and Events are maintained in alphabetical order.
- Each event contains three medalists in "name:country" format, kept in the order they were entered to maintain gold-silver-bronze order.

### Methods
Once the tree is built, the program reads a second file containing queries and calls methods to output the desired information about events, athletes, and countries.
- Get events by sport
- Get winners and countries by sport and event
- Get gold medalist and country by sport and event
- *Get athlete with most medals or gold medals
- *Get country with most medals or gold medals
- *Get sport and event by athlete

### Recursion
Methods marked with '*' utilize recursion to traverse through the tree.
- Every node of the tree is checked, and specific information is collected.

## Technologies
- Java
- Tree data structure
- Recursion
- File input processing

### Example Data Input
```
2024 Artistic_Gymnastics Canoe_Slalom Swimming
Artistic_Gymnastics Womens_All_Around Womens_Floor_Exercise Womens_Uneven_Bars Womens_Vault
Canoe_Slalom Mens_Canoe_Single Womens_Canoe_Single Womens_Kayak_Single
Swimming Mens_200m_Breaststroke Mens_200m_Butterfly Mens_200m_Individual_Medley
Womens_All_Around Simone_Biles:USA Rebeca_Andrade:Brazil Sunisa_Lee:USA
Womens_Floor_Exercise Rebeca_Andrade:Brazil Simone_Biles:USA Ana_Barbosu:Romania
Womens_Uneven_Bars Kaylia_Nemour:Algeria Qiyuan_Qiu:China Sunisa_Lee:USA
Womens_Vault Simone_Biles:USA Rebeca_Andrade:Brazil Jade_Carey:USA
Mens_Canoe_Sinle Nicolas_Gestin:France Adam_Burgess:Great_Britain Matej_Benus:Slovakia
Womens_Canoe_Single Jessica_Fox:Australia Elena_Lilik:Germany Evy_Leibfarth:USA
Womens_Kayak_Single Jessica_Fox:Australia Klaudia_Kinga_Zwolinska:Poland Kimberley_Woods:Great_Britain
Mens_200m_Breaststroke Leon_Marchand:France Izaac_Stubblety-Cook:Australia Caspar_Corbeau:Netherlands
Mens_200m_Butterfly Leon_Marchand:France Kristof_Milak:Hungary Ilya_Kharun:Canada
Mens_200m_Individual_Medley Leon_Marchand:France Duncan_Scott:Great_Britain Shun_Wang:China

```

### Example Query Input
```
GetEventsBySport Canoe_Slalom
GetWinnersAndCountriesBySportAndEvent Artistic_Gymnastics Womens_Uneven_Bars
GetGoldMedalistAndCountryBySportAndEvent Swimming Mens_200m_Breaststroke
GetAthleteWithMostMedals
GetAthleteWithMostGoldMedals
GetCountryWithMostMedals
GetCountryWithMostGoldMedals
GetSportAndEventByAthlete Ana_Barbosu

```

### Example Output
```
GetEventsBySport Canoe_Slalom Mens_Canoe_Single Womens_Canoe_Single
Womens_Kayak_Single
GetWinnersAndCountriesBySportAndEvent Artistic_Gymnastics
Womens_Uneven_Bars Kaylia_Nemour:Algeria Qiyuan_Qiu:China Sunisa_Lee:USA
GetGoldMedalistAndCountryBySportAndEvent Swimming Mens_200m_Breaststroke
Leon_Marchand:France
GetAthleteWithMostMedals 3 Leon_Marchand Rebeca_Andrade Simone_Biles
GetAthleteWithMostGoldMedals 3 Leon_Marchand
GetCountryWithMostMedals 7 USA
GetCountryWithMostGoldMedals 4 France
GetSportAndEventByAthlete Ana_Barbosu
Artistic_Gymnastics:Womens_Floor_Exercise

```
