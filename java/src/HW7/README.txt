/* Written by Hyunyoung Lee for CSCE 314 Students Homework 7 Problem 1

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

===================
=====   Part 1   ======
===================

To compile the code, use the following commands:

```sh
javac HW7/Market.java
javac HW7/SimBox.java
javac HW7/SimMain.java
```

To execute the code, use the following commands:

```sh
java HW7.Main
java HW7.SimMain
```

Expected output for `Main` (Problem 2):

```
Here's what I bought
Apple
Gala
Apple
Apple
Gala
Fruit
Enjoy!
```

Expected output for `SimMain` (Problem 3) depends... Here are two example outputs:

Example 1:
```
From Homer to Marge: My doctor said don't walk.
From Marge to Homer: That was a traffic signal!
From Bart to Homer: There’s a 4:30 in the morning now?
From Homer to Bart: D'oh!
From Bart to Homer: flooding the message queue...
...
```

Example 2:
```
From Homer to Marge: My doctor said don't walk.
From Marge to Homer: That was a traffic signal!
From Bart to Homer: There’s a 4:30 in the morning now?
From Homer to Bart: D'oh!
From Bart to Homer: flooding the message queue...
...
```

The output changes b/c fo the messages are processed by different threads, leading to non-deterministic order.

===================
=====   Part 2   ======
===================

Wildcards in Java generics make for more flexible /reusable code.
In `Market.java`, the `sell` method uses `Collection<? extends T>` to accept any collection of items that are of type
`T` or its subclasses. This allows us to sell a collection of `Apple` to a `Market<Fruit>`. Similarly, the `buy` method
uses `Collection<? super T>` to allow adding items to any collection that can hold `T` or its superclasses.

Without wildcards, we would have to use exact type matches, which would limit the flexibility.
For example, we couldn't sell a `Collection<Apple>` to a `Market<Fruit>` because `Collection<Apple>` is not a
`Collection<Fruit>`. Wildcards allow us to work with a broader range of types, making the code more versatile.

===================
=====   Part 3   ======
===================

Synchronization is necessary to ensure that multiple threads can safely access shared resources without causing data
corruption or inconsistency. In `SimBox`, the shared message queue (`messages`) is accessed by multiple `SimBox`
instances. Synchronizing access to this queue ensures that messages are added and removed atomically, preventing data
corruption. Similarly, the private message queue (`myMessages`) is synchronized to ensure that messages are moved safely
from the shared queue to the private queue.

Our implementation does not create the possibility of deadlock because we always acquire and release locks in a
consistent order. We first lock the `messages` queue and then the `myMessages` queue. This consistent locking order
prevents circular dependencies between threads, which is a common cause of deadlock.
