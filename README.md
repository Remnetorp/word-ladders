Algorithm to solve finding the path between two places. Every place or node is a five-letter word. There exist a path from one node to another node if the last four letter in the first node exist in the second node. The same rules applies but in the other direction if there exists a path back from the second node to the first node. The program solving the algorithm should take a query containing a starting word and a ending word, and the program will return the length of the shortest path. 

---------------------------------------------------------------------------------------------------------------------------------------------

The syntax of the input file should follow the following conditions: The first row of the input consists two integers N,Q, the number of words we consider and the number of queries. Then follows N lines containing one five-letter word each. After this Q lines follow containing two space-separated five-letter words each, which are the queries. You can also find answers to the input files in same folder as example data.

To run the algorithm, use following lines:
(1) $ javac WordLadders.java
(2) $ java WordLadders < data-input/data1.in
(3) $ java WordLadders < data-input/data5.in output

