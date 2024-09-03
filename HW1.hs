
-- CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
-- Homework Assignment 1 (Total 105 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Tiernan Lindauer
-- UIN: 134003853
-- Resources: N/A

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where
import Test.HUnit
import System.Exit

mySum :: [Int] -> Int
mySum []     = 0
mySum (x:xs) = x + mySum xs

-- Problem 2 (5+15 = 20 points)
qsort1 :: Ord a => [a] -> [a]
---- Question 2.1 (5 points)
qsort1 [] = []
qsort1 (x:xs) = qsort1 [y | y <- xs, y >= x] ++ [x] ++ qsort1 [y | y <- xs, y < x]

---- Question 2.2 (15 points)
{-
The qsort1 function implements the quicksort algorithm in descending order.
Here's how it works:
1. Base case: If the list is empty, return an empty list.
2. Recursive case:
   a. Choose the first element (x) as the pivot.
   b. Create a list of elements greater than or equal to x using a list comprehension.
   c. Create a list of elements less than x using a list comprehension.
   d. Recursively sort both lists.
   e. Concatenate the sorted larger elements, the pivot, and the sorted smaller elements.

The time complexity of this implementation is O(n^2) in the worst case (when the list is already sorted),
and O(n log n) on average. The space complexity is O(n) due to the list comprehensions.

A more efficient implementation could use in-place sorting and a better pivot selection strategy.
-}

-- Problem 3 (10 points)
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n - 1) + lucas (n - 2)

-- Problem 4 (10 points)
factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)

-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)
semifactorial :: Int -> Int
semifactorial n
    | n <= 1 = 1
    | otherwise = n * semifactorial (n - 2)

---- Question 5.2 (10 points)
{-
The semifactorial function, also known as double factorial, is defined as:
n!! = n * (n-2) * (n-4) * ... * (1 or 2)

It differs from the regular factorial in that it only multiplies every other number.
For example:
5!! = 5 * 3 * 1 = 15
6!! = 6 * 4 * 2 = 48

The implementation uses recursion with a base case for n <= 1 and a recursive case
that multiplies n with the semifactorial of (n-2).

Time complexity: O(n/2) ≈ O(n)
Space complexity: O(n/2) ≈ O(n) due to the recursion stack
-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int
myfactorial n = product [1..n]

-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a
term n x = x ^ n

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a
polynaive coeffs degree x = sum [c * term n x | (c, n) <- zip coeffs [degree, degree-1..0]]

---- Question 6.3 (10 points)
{-
The polynaive function implements polynomial evaluation using the naive method.
Given a list of coefficients [a_n, a_{n-1}, ..., a_1, a_0], it computes:
P(x) = a_n * x^n + a_{n-1} * x^(n-1) + ... + a_1 * x + a_0

The implementation uses a list comprehension to:
1. Pair each coefficient with its corresponding power (using zip).
2. Calculate each term using the term function.
3. Sum all the terms.

Time complexity: O(n^2), where n is the degree of the polynomial.
This is because for each term, we're calculating x^k, which takes O(k) time.

Space complexity: O(n) for the list comprehension.

A more efficient method would be Horner's method, which has O(n) time complexity:
P(x) = a_0 + x(a_1 + x(a_2 + ... + x(a_{n-1} + x*a_n)...))

Horner's method implementation:
polyHorner :: Num a => [a] -> a -> a
polyHorner coeffs x = foldr (\a b -> a + x * b) 0 coeffs

This would be more efficient for high-degree polynomials or when evaluating
the polynomial for many x values.
-}

myTestList =
  TestList [
      "qsort1 1" ~: qsort1 [3, 2, 5, 1, 8] ~=? [8,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    , "qsort1 3" ~: qsort1 "Oh, happy day!" ~=? "yypphhdaaO,!  "
    , "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1
    , "lucas 3" ~: lucas 4 ~=? 7
    , "factorial 1" ~: factorial 3 ~=? 6
    , "factorial 2" ~: factorial 5 ~=? 120
    , "factorial 3" ~: factorial 10 ~=? 3628800
    , "semifactorial 1" ~: semifactorial 3 ~=? 3
    , "semifactorial 2" ~: semifactorial 5 ~=? 15
    , "semifactorial 3" ~: semifactorial 10 ~=? 3840
    , "myfactorial 1" ~: myfactorial 3 ~=? 6
    , "myfactorial 2" ~: myfactorial 5 ~=? 120
    , "myfactorial 3" ~: myfactorial 10 ~=? 3628800
    , "term 1" ~: term 3 2 ~=? 8
    , "term 2" ~: term 4 5 ~=? 625
    , "term 3" ~: term 10 3 ~=? 59049
    , "polynaive 1" ~: polynaive [2,-1,3,5] 3 2 ~=? 23
    , "polynaive 2" ~: polynaive [1,3,0,7,2] 4 5 ~=? 1037
    , "polynaive 3" ~: polynaive [(1/3),1,-2,0,2,1,(1/2)] 6 3 ~=? 345.5
    , "polynaive 4" ~: polynaive [3,-4,2,7] 3 2 ~=? 19
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)

codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess
