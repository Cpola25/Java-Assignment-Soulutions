Simple Cache Read Me
---------------------

* Overview
* Name: Lizbeth Trujillo 
* Class: Computer Architecture 4310 

  * This program's goal was to implement a Simple Cache that could use
    * Direct Mapping
    * 2/4 Way Associativity 
    * Full Associativity
  * Another goal was to implement these caching types with LRU replacement. 
  
* Using this program: 
  * Running: 
    * To run an input for this program you will be required to input:
      * Number of input address blocks
      * Replacement algorithm choice (for now only option is LRU)
      * Address block sequence
      * Associativity preference (Direct, Set, Full)
        * if you choose Set you will be prompted to choose between a 2 way or a 4-way set. 

* Implementation General Explanation: 
  * Direct Mapped: 
    * For the implementation of my Direct Mapped function I used a LinkedHashMap
      * Each entry into this map consisted of <Key =(n-bit offset value), Vaule = "MEM[Queried number]" >
      * Using a LinkedHashMap I ensured that there would be no duplicate entry for the same offset value.
  
  * Set Associative: 
    * 2-Way: 
      * For this implementation I used 2 stack structures to help me track LRU. I had one stack for the 0 set and one for the 1 set. 
      To save the cache content I used Array lists. If the array list reached the size limit of 2 then I would 
      go to my Stack and pop off the element at index 0 and then replace that element value in my list. 
    * 4-Way: 
      * This implementation was similar to the 2-way but instead I implemented 4 stacks
      for 00, 01, 10 , 11 and 4 arraylists to store elements with similar offsets. 
      When any of these lists reached the limit of size 4 then I would pop off the element of 
      the stack tracking LRU for that particular offset value and replace accordingly in the respective list. 
      
  * Fully Associative: 
    * For this implementation I had an arraylist of size (offset bit number ^ 2) spots available for new cache queries. 
    For this one I had a single stack tracking LRU between all inputs instead of a specific offset pattern. 
    