module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

squareList :: [Int] -> [Int]
squareList = map (\x -> x * x)

oddNumbers :: [Int] -> [Int]
oddNumbers = filter odd

applyTwice :: (a -> a) -> a -> a
applyTwice f x = f (f x)

compose :: (b -> c) -> (a -> b) -> (a -> c)
compose f g = f . g  -- second is applied first, like f1(f0(x)). by why does f0 f1 not work?

myMap :: (a -> b) -> [a] -> [b]
myMap f = foldr (\x acc -> f x : acc) []


main = do
  let x = [1..10]
  putStr("Input List: ")
  putStrLn(show(x))
  putStr("Squared List: ")
  putStrLn(show(squareList x))
  putStr("Odd List: ")
  putStrLn(show(oddNumbers x))
  putStr("Apply (*2) twice: ")
  putStrLn(show(map (applyTwice (*2)) x))
  putStr("Compose (*2) . (+3) and apply mapped: ")
  putStrLn(show(map (compose (*2) (+3)) x))
  putStr("Use myMap to map apply (*2) twice")
  putStrLn(show(myMap (applyTwice (*2)) x))