# CircularLinkedListGame
This is a Java project that implements a generic circular linked list and demonstrates its functionality by iterating through each player in the list to model taking turns in a game. During their turn, each player rolls two dice and adds them to their score, and the first to reach 100 wins.

![image](https://github.com/user-attachments/assets/d42aa8d5-2306-441a-ac1b-25190f09a9fa)

I started by creating the class diagram in order to define each class’s fields, methods, interfaces, and relationships. I also made an object diagram to clarify the structure of the circular linked list and its nodes.

![image](https://github.com/user-attachments/assets/dff6a952-9cae-4470-91f1-f574f214c091)

The list is made up of generic Node objects which reference their data and the next Node. The final Node points to the first Node. This adds some complexity to removing and adding Nodes. Rather than for loops, iteration is performed with a generic Iterator since there are no indexes as there would be in an array.

![image](https://github.com/user-attachments/assets/561fd0d2-40fe-4b52-9b0f-ca70ec757b7d)

Once the linked list was complete and fully tested using JUnit, I implemented and tested the Player class. The FirstTo100.main() method runs the game, using random numbers to represent the dice rolls and outputting the results of each turn and the final winner. Each time main is run, it results in a different outcome.

![image](https://github.com/user-attachments/assets/c2fd5357-be38-4b86-985c-53b7ce2990aa)
