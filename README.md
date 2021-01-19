# Artificial-Intelligence
Intelligence is ability to acquire and apply the knowledge, in an autonomous fashion. AI is the study of how to make machines intelligent like humans. Machine must think, act, behave, learn and respond to the change in its environment like a human. Making machines adaptive to their environment is the purpose of the AI.

Following topics are covered in this repository:

- [Non-AI and AI Techniques in Tic-Tac-Toe Game](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/1__Non%20AI%20and%20AI%20Techniques%20in%20Tic-Tac-Toe%20Game)
- [8 Puzzle Game using A\* Algorithm](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/2__8%20Puzzle%20game%20using%20A_star%20Algorithm)
- [Minimax Algorithm in Tic-Tac-Toe Game](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/3__Minimax%20Algorithm%20in%20Tic-Tac-Toe%20Game)
- [SWI-Prolog](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/4__SWI-Prolog)
- [Single Perceptron Learning](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/5__Single%20Perceptron%20Learning)
- [Fuzzy Set Design](https://github.com/SagarSikchi/Artificial-Intelligence/tree/main/6__Fuzzy%20Set%20Design)


### 1. Non-AI and AI techniques in Tic-Tac-Toe Game

In this, the Tic-Tac-Toe Game is implemeted using both the Non-AI and AI techniques. In this program the best possible move is determined after giving the board position of the game.  
**_The Proble Statement_:** Find the best possible move after the given board position of Tic-Tac-Toe game using the both Non-AI and AI techniques.


### 2. 8 Puzzle Game using A\* Algorithm

A* algorithm is the Informed Search Algorithm used to find the shortest path with cost function. Cost function is implemented using the heuristic value and actual cost value.

Consider, the cost function is F'(N) given as-

    F'(N) = G(N) + H'(N)
         
    where, 
    F'(N) = Cost Function (Approximate value). It is the cost value to reach from initial state to final state.
    G(N)  = It is the actual value to reach from initial state to current state.
    H'(N) = It is the approximate heuristic value to reach from current state to final state.
 

In this program, Euclidean Distance is used as an heuristic function. The two lists namely **OPEN** and **CLOSED** are implemented internally in this project. The OPEN list contains the states which are generated but not processed. While CLOSED list contains the states which are generated and proocessed(created further successors of this current state).

To know more about A* algorithm, check out thee links given below:

   - [**Introduction to the A\* Algorithm**](https://www.redblobgames.com/pathfinding/a-star/introduction.html)
   - [**A\* Search Algorithm**](https://www.geeksforgeeks.org/a-search-algorithm/)
   
**_The Proble Statement_:** Find the best possible path from the given initial board position of 8 Puzzle game to the given goal position of the game.


### 3. Minimax Algorithm in Tic-Tac-Toe Game

**_The Proble Statement_:** Implement the 2-ply search Minimax Algorithm in the Tic-Tac-Toe game.


### 4. SWI-Prolog

[**Prolog**](https://en.wikipedia.org/wiki/Prolog) is a logic programming language associated with artificial intelligence and computational linguistics.Prolog has its roots in first-order logic, a formal logic, and unlike many other programming languages, Prolog is intended primarily as a declarative programming language: the program logic is expressed in terms of relations, represented as facts and rules. A computation is initiated by running a query over these relations. The language was developed and implemented in Marseille, France, in 1972 by Alain Colmerauer with Philippe Roussel, based on Robert Kowalski's procedural interpretation of Horn clauses. Prolog was one of the first logic programming languages and remains the most popular such language today, with several free and commercial implementations available.

Checkout more on the [geeksforgeeks](https://www.geeksforgeeks.org/prolog-an-introduction/). and [this](https://www.youtube.com/playlist?list=PLEJXowNB4kPy3_qhGksOO8ch_Di7T8_9E) Youtube playlist.


### 5. Single Perceptron Learning

Implemented the **Single Perceptron Learning**. Checkout more on this topic [here](https://towardsdatascience.com/perceptron-learning-algorithm-d5db0deab975).


### 6. Fuzzy Set Design

**_The Proble Statement_:** Design the fuzzy set for the real number 'x' close to the number 5 such that x is in between 0 and 10 inclusive. Take 10 values of the x for each _gamma value(sensitive parameter)_ ranging from 1 to 4.  
Checkout more about the fuzzy sets [here](https://www.mygreatlearning.com/blog/fuzzy-logic-tutorial/).
