The goals are:

1. Learn how to solve (simple) problems in different programming systems
2. Learn how to write sufficiently efficient implementations in different programming systems
3. Compare performance of difference programming systems (nano-benchmarking). This is done on a standard mit range notebook and by no means meant as times to beat. The relative values however should have some significance (be it only that my current implementation for a language sucks and should be radically different).

There are implementations of the same problems for different languages within the individual languages.

## Problems

### Problem 1

> If we list all the natural numbers below 10 that are mu>ltiples of 3 or 5, we get 3,5,6, and 9. The sum of these multiples is 23.

> Find the sum of all the multiples of 3 or 5 below 1000.

For measurement find the sum of all multiples of 3 or 5 below 10,000,000 (23,333,331,666,668 which is less than 2^63-1).

1. Mathematica: 1.09 seconds