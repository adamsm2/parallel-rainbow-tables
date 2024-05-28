# parallel-rainbow-tables
This app creates a rainbow table using a given number of threads. Rainbow table as a map structure in which KEY - the last element of a chain, VALUE - the first element. The first element of each chain is randomly generated plain text. Chain_length parameter describes how many times reduction and encryption functions will be used on each table element before adding to the table.

## Tests
Ł - chain_length

T - table_size

Liczba wątków - threads_number

Times is milliseconds

![image](https://github.com/adamsm2/parallel-rainbow-tables/assets/95346590/f33f137e-797e-4156-8a84-5a3b000b9916)
## Example chart
![image](https://github.com/adamsm2/parallel-rainbow-tables/assets/95346590/643c1485-ad3b-4160-acba-d2f02d1969d7)
## Amdahl's law
![image](https://github.com/adamsm2/parallel-rainbow-tables/assets/95346590/3159cef2-80e6-432a-8153-a4bd6aae40d0)
## Effective acceleration achieved 
![image](https://github.com/adamsm2/parallel-rainbow-tables/assets/95346590/65309872-172f-4ce2-978f-f4c4ab907d4b)


