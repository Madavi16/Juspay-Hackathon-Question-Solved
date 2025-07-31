/* - Logic and Approach for the M-ary Tree
 
M ary tree of space
 
7 2
World Asia Africa China India SouthAfrica Egypt
5
1 China 9
1 India 9
3 Asia 9
2 India 9
2 Asia 9
 
7 -> N -> no of nodes in that tree
2 -> M -> no of child count
World Asia Africa China India SouthAfrica Egypt -> node_data
5 -> Q queries count
Quries
q_no  node_name uid
1     China     9    -> true
1     India     9    -> true
3     Asia      9    -> true
2     India     9    -> false
2     Asia      9    -> true
 
1 -> lock
2 -> unlock
3 -> upgrade
 
World -> 1st data -> root
Asia , Africa -> child of World
China and India -> child of Asia
SouthAfrica and egypt -> child of Africa
 
 
              world
             /     \
            /       \
           /         \
          /           \
        Asia         Africa
       /    \       /      \
      /      \     /        \
    China  India SAfrica  Egypt  
 
 
name , uid
 
                       1
 
                 2          3
                 
            4     5        6     7
          8  9  10  11   12 13 14  15
 
 
 
1) create a M ary tree
    -> accept the inputs
    -> create a node for the input
    -> copy the data to nodes
    -> link the nodes in a M ary tree
2) process the queries
    -> Accept the queries
    -> identify the process
        1) Lock
            -> current node not contain uid
            -> Ancestors of the current node must not contain uid
            -> Descendants of the current node must not contain uid
                -> assign uid to the node
        2) Unlock
            -> current node must contain uid
            -> uid must be similar to the current which we have
                -> remove the uod from the node
        3) Upgrade
            -> current node must must not contain uid
            -> Ancestors of the current node must not contain uid
            -> Descendants of the current node must contain atleast one uid
            -> if more then one node were locked, all the nodes must be locked by the same uid
                -> Assign uid to the node and remove uid from all the descendants
 
 
 */
