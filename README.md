# TinkeredMSTVol.2

## Program

program for Tinkered MST Project in DBLab of catholic university

## Problem 

In the Tinkered MST Vol.1, we proposed a method of obtaining optimized connecting in given partitions when each partition's terminals are connected in MST. 
In this paper, we consider moving Terminals in real time.
New Terminals are able to insert.
Also existing Terminals are able to delete or move to other Partition.
If there are many operations, after operation are ended, getting MST of all terminal  takes considerable time.
So a method of maintaining MST following operation without reconstructing  new MST is described in this paper.

## Algoritm
1. Insert operation
![insert_operation_image](https://user-images.githubusercontent.com/77263283/151778840-8787026c-9d83-42a7-86e6-dfa870c4922e.png)

2. Delete operation
![delete_opartion_image](https://user-images.githubusercontent.com/77263283/151780411-be257e35-9103-4bb7-8600-54c6d277f019.png)

3. Move operation

Move operation is combination of delete operation and insert operation.

## UML

![uml](https://user-images.githubusercontent.com/77263283/152115261-e103f803-8cab-470c-91ba-9ebb313fb879.png)
