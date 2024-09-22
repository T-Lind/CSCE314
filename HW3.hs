
-- CSCE 314 [Sections 598, 599] Programming Languages Fall 2024
-- Hyunyoung Lee
-- Homework Assignment 3 (Total 100 points) Due on Monday 9/23/24 at 11:59 p.m.

-- Problem 1 (5 points)
-- Student Name: Tiernan Lindauer
-- UIN: 134003853
-- Resources: Haskell Textbook

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char


-- *** Read textbook Chapters 5, 6, and 7, and the problem specifications
-- *** and requirements in hw3.pdf carefully!

-- Problem 2 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a]  -- function declaration
merge a [] = a  -- if second list is empty, return first list
merge [] b = b  -- if first list is empty, return second list
merge (a:as) (b:bs)  -- take two lists w/ heads and tails
  | a < b = a : merge as (b:bs)  -- if a < b, add a to the list and merge the rest
  | otherwise = b : merge (a:as) bs  -- if b <= a, add b to the list and merge the rest



-- Problem 3 (Chapter 6, Exercise 8) (5+10=15 points)
---- Question 3.1 (5 points)
halve :: [a] -> ([a], [a])  -- function declaration
halve xs = splitAt (length xs `div` 2) xs  -- split the list in half


---- Question 3.2 (10 points)
msort :: Ord a => [a] -> [a]
msort [] = []  -- if empty just return empty list
msort [x] = [x]  -- no sorting required for 1 element either
msort xs = merge (msort ys) (msort zs)  -- merge the two halves
  where (ys, zs) = halve xs  -- split the list in half



-- Problem 4 (10+10+10=30 points)
---- Question 4.1 (10 points)
mergeRev :: Ord a => [a] -> [a] -> [a]  -- mergeRev will be a helper function used for reversing the merge
mergeRev a [] = a  -- if second list is empty, return first list
mergeRev [] b = b  -- if first list is empty, return second list
mergeRev (a:as) (b:bs)  -- take two lists w/ heads and tails
  | a > b     = a : mergeRev as (b:bs)  -- if a > b, add a to the list and merge the rest
  | otherwise = b : mergeRev (a:as) bs  -- if b >= a, add b to the list and merge the rest

mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]  -- function declaration
mergeBy _ a [] = a  -- if second list is empty, return first list
mergeBy _ [] b = b  -- if first list is empty, return second list
mergeBy pred (a:as) (b:bs)  -- take predicate as well as two lists to merge by
  | pred a b  = a : mergeBy pred as (b:bs)  -- if pred is true add a to the list and merge the rest
  | otherwise = b : mergeBy pred (a:as) bs  -- if pred is false add b to the list and merge the rest


---- Question 4.2 (10 points) 
msortBy :: (a -> a -> Bool) -> [a] -> [a]  -- function declaration
msortBy _ [] = []  -- if empty just return empty list
msortBy _ [x] = [x]  -- no sorting required for 1 element either
msortBy pred xs = mergeBy pred (msortBy pred ys) (msortBy pred zs)  -- use mergeBy
  where (ys, zs) = halve xs  -- split the list in half


---- Question 4.3 (10 points)
{- Write your answer for Question 4.3 within this block comment.
-- Should be detailed step-by-step.
msortBy (>) [7,5,1,4,2]
halve [7,5,1,4,2] = ([7,5], [1,4,2])

msortBy (>) [7,5]
msortBy (>) [1,4,2]

halve [7,5] = ([7], [5])
halve [1,4,2] = ([1], [4,2])

msortBy (>) [7] = [7]
msortBy (>) [5] = [5]
msortBy (>) [1] = [1]

msortBy (>) [4,2]
halve [4,2] = ([4], [2])
msortBy (>) [4] = [4]
msortBy (>) [2] = [2]

mergeBy (>) [4] [2]
mergeBy (>) [] [2] = [2]

mergeBy (>) [1] [4,2]
mergeBy (>) [1] [2]
mergeBy (>) [1] [] = [1]

mergeBy (>) [7] [5]
mergeBy (>) [] [5] = [5]

mergeBy (>) [7,5] [4,2,1]
mergeBy (>) [5] [4,2,1]
mergeBy (>) [] [4,2,1] = [4,2,1]

result = [7,5,4,2,1]
-}


-- Problem 5 (10+5+10=25 points)
---- Question 5.1 (10 points)
myInsert :: Ord a => a -> [a] -> [a]  -- function declaration
myInsert x [] = [x]  -- if empty just return x
myInsert x (y:ys)  -- take an element and a list
  | x <= y    = x : y : ys  -- if x <= y, insert x before y
  | otherwise = y : myInsert x ys  -- if x > y, insert x in the rest of the list

---- Question 5.2 (5 points)
mySort :: Ord a => [a] -> [a]  -- function declaration
mySort = foldr myInsert []  -- use foldr to process the list from right to left

---- Question 5.3 (10 points)
{- Write your answer for Question 5.3 within this block comment.
-- Should be detailed step-by-step.
foldr processes R->L
[]
[2]                     -- inserts elements using myInsert
[2, 4]
[1, 2, 4]
[1, 2, 4, 5]
[1, 2, 4, 5, 7]
-}



-- Problem 6 (Chapter 7, Exercise 9) (10+5=15 points)
---- Question 6.1 (10 points)
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]  -- function declaration
altMap _ _ [] = []  -- if empty just return empty list
altMap f0 f1 (x:xs) = f0 x : altMap f1 f0 xs  -- apply f0 to x and f1 to the rest of the list

---- Question 6.2 (5 points)
{- Write your answer for Question 6.2 within this block comment.
-- Should be detailed step-by-step.

altmap alternates between two functions f0 + f1,
altmap ('div' 3) (*5) [1,2,3,4,5,6,7,8,9]
f0 = ('div' 3)
f1 = (*5)

('div' 3) 1 = 0
(*5) 2 = 10
('div' 3) 3 = 1
(*5) 4 = 20
('div' 3) 5 = 1
(*5) 6 = 30
('div' 3) 7 = 2
(*5) 8 = 40
('div' 3) 9 = 3

result = [0,10,1,20,1,30,2,40,3]
-}




    
myTestList =
  TestList [

      "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"
    , "merge 3" ~: merge [1,3,5,7,9] [2,4,6] ~=? [1,2,3,4,5,6,7,9]

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 3" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"
    , "msort 4" ~: msort [3,2,1,5,4] ~=? [1,2,3,4,5]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
    , "mergeBy 3" ~: mergeBy (>) [5,1] [6,4,3] ~=? [6,5,4,3,1]
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""
    , "msortBy 4" ~: msortBy (>) [3,2,1,5,4] ~=? [5,4,3,2,1]

    , "myInsert 1" ~: myInsert 'o' "Hw are you?" ~=? "How are you?"
    , "myInsert 2" ~: myInsert 'c' "abdefg" ~=? "abcdefg"
    , "myInsert 3" ~: myInsert 3 [2,4,6] ~=? [2,3,4,6]

    , "mySort 1" ~: mySort "Jack be quick" ~=? "  Jabcceikkqu"
    , "mySort 2" ~: mySort "Howdy all!" ~=? " !Hadllowy"

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"
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
