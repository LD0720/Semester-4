type Cell = (Int,Int)
data MyState = Null | S Cell [Cell] String MyState deriving (Show , Eq)

up Null = Null
up (S (0,y) l m g) = Null
up (S (x,y) l m g) = (S (x-1,y) l "up" (S (x,y) l m g))

down Null = Null
down (S (3,y) l m g) = Null
down (S (x,y) l m g) = (S (x+1,y) l "down" (S (x,y) l m g))

left Null = Null
left (S (x,0) l m g) = Null
left (S (x,y) l m g) = (S (x,y-1) l "left" (S (x,y) l m g))

right Null = Null
right (S (x,3) l m g) = Null
right (S (x,y) l m g) = (S (x,y+1) l "right" (S (x,y) l m g))

--member [] y = False
--member (x:xs) y = (x==y) || member xs y
 
remove y (x:xs)| y==x = xs
			   | otherwise =  x:remove y xs 

collect Null = Null
collect (S (x,y) l m g) = if elem (x,y) l == True then (S (x,y) (remove (x,y) l) "Collect" (S (x,y) l m g)) 
							else Null 


nextMyStates (S (x,y) l m g) =helper2( helper (S (x,y) l m g) )				
helper (S (x,y) l m g) = up (S (x,y) l m g) : down (S (x,y) l m g) : right (S (x,y) l m g) : 
						left (S (x,y) l m g)  : collect (S (x,y) l m g) : []
helper2 [] = []
helper2 (x:xs) | x /= Null = x : helper2 xs 
			   |otherwise = helper2 xs			   
			   
isGoal (S (x,y) [] m g) = True
isGoal (S (x,y) l m g) = False


search (x:xs) |isGoal x = x 
			  |otherwise = search (xs ++ (nextMyStates x) )
			  
constructSolution Null = [] 	
constructSolution (S (x,y) l "" g) = [] 		  
constructSolution (S (x,y) l m g) = constructSolution g ++ [m]   



--if(search((z:zs)) == [] then solve z where (z:zs) = nextMyStates (S (x,y) l m g) 
--else constructSolution((j)) where (j:js) = search((z:zs))

solve (x,y) l = constructSolution ( search ( nextMyStates (S (x,y) l "" Null))) 






