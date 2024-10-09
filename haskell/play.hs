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

reverseFoldl :: Num a => (c -> d) -> [a] -> [a]
reverseFoldl = foldl (flip (:))

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

data NTree a = NLeaf a | Branch [NTree a]

type Points = Int  -- type keywords is used to define aliases, not new types.

ntree1 :: NTree Int
ntree1 = Branch [
          Branch [
            NLeaf 2,
            NLeaf 4
          ],
          Branch [
            Branch [
              NLeaf 5
            ],
            Branch [
              NLeaf 9
            ]
          ]
         ]

instance Show a => Show (NTree a) where
  show (NLeaf a) = "Leaf(" ++ show a ++ ")"
  show (Branch b) = "Branch(" ++ show b ++ ")"

ntreeDepth :: NTree a -> Int
ntreeDepth (NLeaf _) = 1
ntreeDepth (Branch []) = 0
ntreeDepth (Branch bs) = 1 + maximum (map ntreeDepth bs)

newtype Stack a = Stack [a]
push :: a -> Stack a -> Stack a
push x (Stack xs) = Stack (x:xs)

pop :: Stack a -> Maybe (Stack a, a)
pop (Stack []) = Nothing
pop (Stack (x:xs)) = Just (Stack xs, x)

peek :: Stack a -> Maybe a
peek (Stack []) = Nothing
peek (Stack (x:_)) = Just x

isEmpty :: Stack a -> Bool
isEmpty (Stack xs) = length xs == 0

emptyStack :: Stack a
emptyStack = Stack []

instance Show a => Show (Stack a) where
  show (Stack xs) = "Stack:\n" ++ unlines (map (\x -> " " ++ show x) (reverse xs))

getPopStack:: Maybe (Stack a, a) -> Stack a
getPopStack Nothing = Stack []
getPopStack (Just (Stack stack, _)) = Stack stack

getPopped :: Maybe (Stack a, a) -> a
getPopped Nothing = error "No element to pop"
getPopped (Just (_, x)) = x

-- MONADS --

data Try a = Success a | Failure String
instance Show a => Show (Try a) where
  show (Success a) = "Success: " ++ show a
  show (Failure msg) = "Failure: " ++ msg

instance Functor Try where
  fmap f (Success a) = Success (f a)
  fmap _ (Failure msg) = Failure msg

instance Applicative Try where
  pure = Success
  (Success f) <*> (Success a) = Success (f a)
  (Failure msg) <*> _ = Failure msg
  _ <*> (Failure msg) = Failure msg

instance Monad Try where
  return = pure
  (Success a) >>= f = f a
  (Failure msg) >>= _ = Failure msg

-- Example application:
divide :: Int -> Int -> Try Int
divide _ 0 = Failure "Division by zero"
divide a b = Success (a `div` b)

filterTry :: (a -> Bool) -> [a] -> Try [a]
filterTry _ [] = Failure "No items in list."
filterTry f xs = Success (filter f xs)

computation :: Try Int
computation = do
  x <- divide 10 2
  y <- divide 20 x
  return (y + 5)

computation2 :: Try [Int]
computation2 = do
  let xs = map (\x -> 3^x - 11*x) [1..9]
  ys <- filterTry even xs
  return ys

xs >>= f = [y | x <- xs, y <- f x]
--fun js ks = js >>= \s -> ks >>= \t -> return (s, t)
--fun2 js ks = js >>= bind1  -- note these two are equivalent! I prefer the second one
--  where
--    bind1 s = ks >>= bind2
--      where
--        bind2 t = return (s, t)
--
--combineLists :: [Int] -> [Int] -> [Int]
--combineLists xs ys = xs >>= bind1
--  where
--    bind1 x = ys >>= bind2
--      where
--        bind2 y = return (x + y)



-- FUNCTORS --
data XEither a b = XLeft a | XRight b
instance Functor (XEither a) where
  fmap _ (XLeft x) = XLeft x
  fmap f (XRight y) = XRight (f y)

safeDivide :: Float -> Float -> XEither String Float
safeDivide _ 0 = XLeft "Error: division by zero"
safeDivide x y = XRight (x / y)

processSafeDivide :: XEither String Float -> String
processSafeDivide (XLeft error) = "Calculation failed: " ++ error
processSaveDivide (XRight result) = "Result: " ++ show result

main = do
  let x = [1..10]

  -- BASIC EXAMPLE FUNCTIONS --
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

  -- CUSTOM TYPES --
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

  -- BINARY SEARCH TREE --
  putStrLn("Binary Search Tree: ")
  print(bst1)
  print(bstMap (\x -> 100 `div` x) bst1)
  putStrLn("NTree: ")
  print(ntree1)

  -- STACK --
  let s1 = push 5 (emptyStack :: Stack Int)
  let s2 = push 9 s1
  let s3 = push 13 s2
  let s4 = push 2 s3
  -- Pop and print
  putStrLn "Working with Stack (initially set to 5-9-13-2): "
  let popRes = pop s4
  let s5 = getPopStack popRes
  putStr "Popped value: "
  print (getPopped popRes)
  print s5
  let s6 = getPopStack (pop (getPopStack (pop (getPopStack (pop s5)))))
  let pop2Res = pop s6  -- this will run ok, and be nothing since nothing could be popped
--  print (getPopped pop2Res)  -- this will throw an error, because there is nothing popped

  -- MONADS --
  putStr "Monad Computation 1 results: "
  print computation
  putStr "Monad Computation 2 results: "
  print computation2

  print (processSafeDivide (safeDivide 10 0))
