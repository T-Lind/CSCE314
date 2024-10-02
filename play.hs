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

myzip :: [a] -> [b] -> [(a, b)]
myzip _ [] = []
myzip [] _ = []
myzip (x:xs) (y:ys) = [(x, y)] ++ myzip xs ys

safezip :: [a] -> [b] -> Maybe [(a, b)]
safezip xs ys
  | length xs /= length ys = Nothing
  | otherwise = Just (myzip xs ys)

{-
  Example questions
  Q: Type of map (+1) [1, 2, 3]
  A: [Int]

  Q: Type of (\x -> x * 2) 5
  A: Int

  Q: Type of not True
  A: Bool

  Q: Type of head [1, 2, 3]
  A: Int
-}
mySum :: Num a => [a] -> a
mySum (x:xs) = x + mySum xs

getSecondElement :: [a] -> Maybe a
getSecondElement xs
  | length xs < 2 = Nothing
  | otherwise = Just (xs!!1)


factorial :: Int -> Int
factorial 0 = 1
factorial x = x * factorial x-1

isPalindrome :: Eq a => [a] -> Bool
isPalindrome [] = True
isPalindrome [_] = True
isPalindrome xs
  | xs!!0 == last xs = isPalindrome (tail (init xs))
  | otherwise = False

map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = f x : map' f xs

filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' f (x:xs)
  | f x = x : filter' f xs
  | otherwise = filter' f xs

-- Rewrite addOne x = x + 1 using a lambda expression:
addOne = \x -> x + 1

doubleEvens :: Integral a => [a] -> [a]
doubleEvens xs = map (*2) (filter' even xs)

prodFoldr :: Num a => [a] -> a
prodFoldr = foldr (*) 1

reverseFoldl :: Num a => [a] -> [a]
reverseFoldl = foldl (flip (:)) []

data Shape = Circle Double | Rectangle Double Double

instance Show Shape where
  show (Circle r) = "Circle with radius " ++ show r
  show (Rectangle w h) = "Rectangle with width " ++ show w ++ " and height " ++ show h

area :: Shape -> Double
area (Circle r) = pi * r^2
area (Rectangle w h) = w * h

instance Eq Shape where
  s1 == s2 = area(s1) == area(s2)

class Describable a where
  describe :: a -> String

instance Describable Int where
  describe x = "This is the number " ++ show x

instance Describable Bool where
  describe True = "This is True"
  describe False = "This is False"

instance Describable Shape where
  describe (Circle _) = "This is a circle."
  describe (Rectangle _ _) = "This is a rectangle."

data Person = Person {
  name :: String,
  age :: Int,
  occupation :: String
}

instance Show Person where
  show (Person n a o) = n ++ ", " ++ show a ++ " years old, works as " ++ o

data Tree a = Leaf a | TreeNode (Tree a) (Tree a)

treeSize :: Tree a -> Int
treeSize (Leaf _) = 1
treeSize (TreeNode left right) = treeSize left + 1 + treeSize right

treeMap :: (a -> b) -> Tree a -> Tree b
treeMap f (Leaf l) = Leaf (f l)
treeMap f (TreeNode left right) = TreeNode (treeMap f left) (treeMap f right)

data BST a = EmptyBST | BSTNode a (BST a) (BST a)

bstSize :: BST a -> Int
bstSize EmptyBST = 0
bstSize (BSTNode _ left right) = 1 + bstSize left + bstSize right

bstMap :: (a -> b) -> BST a -> BST b
bstMap _ EmptyBST = EmptyBST
bstMap f (BSTNode a left right) = BSTNode (f a) (bstMap f left) (bstMap f right)

data MyList a = EmptyList | Cons a (MyList a)

myLength :: MyList a -> Int
myLength EmptyList = 0
myLength (Cons _ xs) = 1 + myLength xs

tree1 :: Tree Int
tree1 = TreeNode
          (TreeNode
            (Leaf 1)
            (Leaf 2)
          )
          (TreeNode
            (Leaf 3)
            (Leaf 4)
          )

bst1 :: BST Int
bst1 = BSTNode
        5
        (BSTNode
          3
          EmptyBST
          EmptyBST
        )
        (BSTNode
          7
          (BSTNode
            6
            EmptyBST
            EmptyBST
          )
          (BSTNode
            9
            EmptyBST
            EmptyBST
          )
        )

instance Show a => Show (BST a) where
  show EmptyBST = "EmptyBST"
  show (BSTNode a left right) = "BSTNode " ++ show a ++ " (" ++ show left ++ ") (" ++ show right ++ ")"

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
  let a = [1, 2, 3]
  let b = [7, 8, 9]
  putStr("Safezip implementation: ")
  print(safezip a b)  -- putStrLn ( show (...)) can be replaced by print(...)
  putStr("Safezip on unequal lengths: ")
  let aTail = tail a
  putStrLn(show(safezip aTail b))
  let c = Circle 5
  let r = Rectangle 4 3
  putStrLn("Given two shapes, ")
  print c
  print r
  putStr("They are equal in area: ")
  print (c == r)
  putStrLn("Testing out type classes: ")
  putStr(describe c)
  putStr(", ")
  putStrLn(describe r)
  putStrLn(show(map describe [False, True, False]))
  putStrLn("Testing new Person data type:")
  let p = Person "George" 31 "Engineer"
  print p

  print(bst1)
