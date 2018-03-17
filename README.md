## Bugs in procedural skills the buggy repair step story

Buggy: simulates a student who makes mistakes

> This is the first approach to explains student errors.


Build `gradle build`  
Run `java -jar ./build/libs/buggy.jar`

### Exemples:

|   |  |
|--:|:--|
| 143<br> -28<br> -------<br> 125 | When the student needs to borrow, he adds 10 to the top digit of the current <br> column without subtracting 1 from the next column to the left.   |
| 143<br> -24<br> -------<br> 121 | The student substracts the smaller digit in each column from<br>the larger digit regardless of which is on top.  |
| 1 300<br> -522<br> -------<br> 878 | When borrowing from a column whose top digit is 0,<br> the student writes 9 but does not continue borrowing from the column to the left of the 0.  |
| 140<br> -21<br> -------<br> 121 | Whenever the top digit in a column is 0,<br> the student writes the bottom digit in the answer.   |

