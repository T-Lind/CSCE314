
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
-- above is example

-- Problem 2 (5+15 = 20 points)
qsort1 :: Ord a => [a] -> [a]  -- Function signature for the qsort1 function.
---- Question 2.1 (5 points)
qsort1 [] = []  -- This is a base case for the qsort1 function of qsort1([]) = [].
qsort1 (x:xs) = qsort1 larger ++ [x] ++ qsort1 smaller  -- This line takes the head and tail, then calls recursively on the set of larger numbers, inserts the head, then calls recursively on the set of smaller numbers.
  where
    larger = [y | y <- xs, y >= x]  -- This line creates a list of all numbers in the tail that are greater than or equal to the head.
    smaller = [y | y <- xs, y < x]  -- This line creates a list of all numbers in the tail that are less than the head.

---- Question 2.2 (15 points)
{-
If qsort1 is invoked with [15,8,11,6,10], the function will be called like this:
qsort1 [15,8,11,6,10]
qsort1 [] ++ [15] ++ qsort1 [8,11,6,10]
[] ++ [15] ++ qsort1 [11,10] ++ [8] ++ qsort1 [6]
[] ++ [15] ++ [] ++ [11] ++ [10] ++ [8] ++ [] ++ [6] ++ []
= [15,11,10,8,6]

The function qsort1 is called four times recursively, excluding the initial function call.
The values of x, smaller, and larger at each level of recursive call are as follows:
1 (initial, not counted). x = 15, smaller = [8,11,7,10], larger = []
2. x = none, larger = [], smaller = []
3. x = 8, larger = [11,10], smaller = [6]
4. x = 11, larger = [], smaller = [10]
5. x = 6, larger = [], smaller = []

Hopefully the flow here makes sense to you.
-}

-- Problem 3 (10 points)
lucas :: Int -> Int  -- Function signature for the lucas function.
lucas 0 = 2  -- This is a base case for the lucas function of lucas(0) = 2.
lucas 1 = 1  -- This is a base case for the lucas function of lucas(1) = 1.
lucas n = lucas (n - 1) + lucas (n - 2)  -- This is the recursive case for the lucas function, which is the sum of the two previous numbers in the sequence. This then calls recursively until the base cases are reached.

-- Problem 4 (10 points)
factorial :: Int -> Int  -- Function signature for the factorial function.
factorial 0 = 1  -- This is a base case for the factorial function of factorial(0) = 1.
factorial n = n * factorial (n - 1)  -- This simply computes the factorial, or n * (n - 1) * (n - 2) * ... * 1, of the input n until it hits the base case of n = 0.

-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)
semifactorial :: Int -> Int  -- Function signature for the semifactorial function.
semifactorial n  -- start of function
    | n <= 1 = 1  -- This is a base case for the semifactorial function of semifactorial(0) = 1 and semifactorial(1) = 1.
    | True = n * semifactorial (n - 2)  -- This computes the semifactorial, or n * (n - 2) * (n - 4) * ... * 1, of the input n until it hits the base case of n = 1. So if n is odd it will multiply all the odd numbers up to n, and if n is even it will multiply all the even numbers up to n.

---- Question 5.2 (10 points)
{-
If semifactorial were invoked with an input of 14, the function would be recursively called like this:
semifactorial 14
14 * semifactorial 12
14 * 12 * semifactorial 10
14 * 12 * 10 * semifactorial 8
14 * 12 * 10 * 8 * semifactorial 6
14 * 12 * 10 * 8 * 6 * semifactorial 4
14 * 12 * 10 * 8 * 6 * 4 * semifactorial 2
14 * 12 * 10 * 8 * 6 * 4 * 2 * semifactorial 0
14 * 12 * 10 * 8 * 6 * 4 * 2 * 1
Or, in other words, it would be called seven times before reaching the base case, excluding the initial fnction call.
-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int  -- Function signature for the myfactorial function.
myfactorial n  -- start of function
    | n <= 1 = 1  -- This is a base case for the myfactorial function of myfactorial(0) = 1 and myfactorial(1) = 1.
    | True = semifactorial n * semifactorial (n - 1)  -- This computes the factorial of the input n by multiplying the semifactorial of n and the semifactorial of n - 1. This then covers all the numbers below n.


-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a  -- Function signature for the term function.
term n x = x ^ n  -- Simply computes x^n for the input n and x.

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a  -- Function signature for the polynaive function.
polynaive coeffs degree x = sum [c * term n x | (c, n) <- zip coeffs [degree, degree-1..0]]  -- This computes the polynomial given coefficients, degree, and x. It zips the coeffs with the degree, then calls term on each pair of coefficient and degree, then multiplies the coefficient by the term, and finally sums all the terms together.

---- Question 6.3 (10 points)
{-
When this function polynaive is called with [2, -4, -1, 1] 3 4, the function will be called like this:
polynaive [2, -4, -1, 1] 3 4
2 * 4^3 + -4 * 4^2 + -1 * 4^1 + 1 * 4^0
= 61, as indicated in the homework pdf.
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
