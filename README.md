# Live Football World Cup Scoreboard
This is a simple library implementation for a Live Football World Cup Scoreboard that supports starting new matches, updating scores, finishing matches, and retrieving a summary of matches in progress.
## Overview
this is java based application write with TDD approach with Java 17 and JUnit 5.
## Task descriptions:
                    You are working in a sports data company, and we would like you to develop a new Live Football
World Cup Scoreboard library that shows all the ongoing matches and their scores.
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

For example, if following matches are started in the specified order and their scores
respectively updated:
a. Mexico 0 - Canada 5
b. Spain 10 - Brazil 2
c. Germany 2 - France 2
d. Uruguay 6 - Italy 6
e. Argentina 3 - Australia 1

The summary should be as follows:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
## Features
- Start a new match with an initial score of 0-0.
- Update the score of an ongoing match.
- Finish an ongoing match and remove it from the scoreboard.
- Get a summery
