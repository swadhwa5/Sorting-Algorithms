# Discussion

Student 1: Casey Levitt(clevitt1)
Student 2: Shreya Wadhwa(swadhwa5)

## Part A
1. Discuss from a design perspective whether or not iterating over a MeasuredArray should affect the accesses and 
mutation counts. 

The Iterator is used to access the elements of the array, but it cannot be used to change them. Therefore, an iterator 
should increment the accesses count by the size of the array, and make no changes to the mutation count.

2. Can you inherit ArrayIterator from SimpleArray and override the relevant methods, or not? Explain.

No we cannot inherit ArrayIterator from SimpleArray and override the relevant methods, because the ArrayIterator class 
is a private inner class of SimpleArray class. Even though MeasuredArray is a subClass of SimpleArray, it cannot access 
the methods defined inside the ArrayIterator class, because subClasses don't inherit the private members of SuperClass.
Instead, we are able to override the iterator() method in MeasuredArray class and make a new MeasuredArrayIterator and 
define hasNext(), next(), and remove() for that.

## Part B
Include the results of experiments (must have concrete time measurements and size of data set used).
1. There is an intentional mistake in one of the provided data files. The goal of this assignment is to use the 
measurements to catch that mistake. 

We noticed that the mutation count for all types of sorts of the ascending data file was rather high, especially
considering that, since the data is already sorted in ascending order, the mutation count should have been zero.
We looked over ascending.data, and the data was indeed sorted in ascending order by numerical value. This led us to 
the conclusion that the SortingAlgorithmDriver was not sorting the data by numerical value - it was treating the data 
as Strings. This hypothesis was confirmed when we looked at descending.data, which looks out of order by
numerical comparison, but is ordered by String comparison.

2. Does the actual running time correspond to the asymptotic complexity as you would expect?

Yes! To test this, we collected sort-time data for 3 different input values for SIZE: 2000, 4000, and 8000.
Since the asymptotic complexity we expect for all of the sort algorithms is O(N^2), we expect the run-times
to increase quadratically.
For each SIZE value, we ran the SortingAlgorithmDriver 3 times, and recorded the run times for all 4 sorts. 
To analyze the results, we decided to focus on the sort-times for random.data. We took the averages of the 3
runs for each SIZE value and for each sort algorithm. The averages are listed here:

|SIZE/SORT |2000             |4000           |Time(4000)/Time(2000) ~ 4   |
| -------- | -------------- | ------------- | ------------------------- |
|Gnome     |0.017355        |0.076058       |4.382483434                |
|Selection |0.009748333333  |0.04207833333  |4.316464353                |
|Bubble    |0.02431633333   |0.1026413333   |4.221085964                |
|Insertion |0.003844333333  |0.015741       |4.09459811                 |

|SIZE/SORT |4000            |8000           |Time(8000)/Time(4000) ~ 4  |
| -------- | -------------- | ------------- | ------------------------- |
|Gnome     |0.076058        |0.287033       |3.773869941                |
|Selection |0.009748333333  |0.1556286667   |3.698546362                |
|Bubble    |0.04207833333   |0.3602756667   |3.510044686                |
|Insertion |0.015741        |0.06402766667  |4.067573005                |

|SIZE/SORT |2000            |8000           |Time(8000)/Time(2000) ~ 16 |
| -------- | -------------- | ------------- | ------------------------- |
|Gnome     |0.017355        |0.287033       |16.5389225                 |
|Selection |0.009748333333  |0.1556286667   |15.96464353                |
|Bubble    |0.02431633333   |0.3602756667   |14.81620036                |
|Insertion |0.003844333333  |0.06402766667  |16.65507674                |


We can see from the data that the sort-times do indeed increase quadratically. Specifically, doubling the
inputs results in a 4x increase in the sort-time, and increasing the inputs by 4x result in a 16x increase
in sort-time.

3. What explains the practical differences between these algorithms? (Theoretically, the algorithm runs 
in O(X) time, where X is a function of the input size, but in practice (i.e running it on data sets), you may 
observe that it is slower/faster compared to the other algorithms)

    The practical differences between the algorithms can be explained by the fact that though all of the algorithms 
use the same basic operations (comparisons, assignments, etc), the number of times they perform each operation
will vary. For example, lets assume we have the worst case run-time that requires the maximum number of traversals
and mutations.
    In the worst-case scenario, Bubble sort and Selection sort will do the same number of traversals and the
same number of comparisons in each traversal. However, Selection sort will only perform one swap per traversal,
while Bubble sort will perform multiple swaps with each traversal. This example shows that though the Bubble sort 
and Selection sort algorithms have the same asymptotic complexity, there will be practical differences in the
run-times based on the varying number of operations performed. 
    Bubble sort and Insertion sort will also make the same number of comparisons. However, 
after each comparison, Bubble sort will perform a swap while Insertion sort will perform a shift. Since a swap 
involves more steps than a shift, the Bubble Sort will have to perform more steps, increasing its runtime. 
    We must also consider that different machines perform different operations with varying speed. Therefore, 
the time it takes a given machine to perform each operation combined with the varying numbers each operation 
is performed by each sort algorithms will result in practical differences in the runtime.

4. Does it matter what kind of data (random, already sorted in ascending
 order, sorted in descending order) you are sorting? How should each algorithm behave 
 (in terms of performance) based on the type of data it receives?
 
Yes, it does matter what kind of data is being sorted. Since all the sorting algorithms sort the data in 
ascending order, they all should take the least amount of run-time to sort ascending data, provided data is 
accurately ordered in ascending order according to its data type in the data file. All Sorts should take approximately
equal amount of time to sort descending and random data.
 
|KIND OF DATA/SORT | (Best Case)          | (Worst Case)           | Average Case)        |
| ---------------- | -------------------- | ---------------------- | -------------------- |
|Selection         |O(n^2)                |O(n^2)                  |O(n^2)                |
|Bubble            |O(n)                  |O(n^2)                  |O(n^2)                |
|Insertion         |O(n)                  |O(n^2)                  |O(n^2)                |

Selection Sort: Given a data set of size 'n', regardless of the kind of data, selection sort traverses 
the elements of the array the same number of times. Selection sort will always traverse the array n times
(with each traversal, it finds the minimum in the remaining search space). This gives it a run time performance
of O(N^2). However, having the data be sorted, or unsorted, changes the number of swaps that the algorithm makes,
which in turn affects the run-time. Example, in ascending data, the number of swaps will be zero, since the 
data is already sorted. Even though the algorithmic complexity will still be O(N^2), the sort will be faster 
because there are fewer swaps required.

Bubble sort: For bubble sort, the number of swaps in each traversal, as well the number of traversals is dependant
on the way the data set is sorted. For example, if the data is sorted in ascending order, bubble sort will traverse 
the array only once, and makes no swaps, hence making it a linear run time. However, if the data is not sorted in
ascending order, the run time approximates to O(n^2), even though it might be slightly more for descending as compared 
to random, since descending data is the worst case scenario where the no. of swaps is maximum.

Insertion Sort: The Insertion sort visits each element in the array regardless of how the data is sorted, to begin 
with. However, having the data be sorted, or unsorted, determines the number of shifts it makes to place each element
in its appropriate position. For example, in ascending data, even though each element is visited, no shifts are made 
ever, hence making it linear run-time. However, if the data is not sorted in ascending order, the run time 
approximates to O(n^2), even though it might be slightly more for descending as compared to random, since 
descending data is the worst case scenario where the no. of shifts is maximum.


## Part C
1. Determine exactly how many comparisons C(n) and assignments A(n) are performed by this implementation of 
selection sort in the worst case. Both of those should be polynomials of degree 2 since you know that the 
asymptotic complexity of selection sort is O(n^2).
Let a.length() = n
Line 1: _C(n)_ = 0 
        _A(n)_ = 0
        
Line 2: _C(n)_ = 0 
        _A(n)_ = 0
        
Line 3: _C(n)_ = n : 
        The loop runs from 0 to n - 1 and only increments i by 1, and so the loop runs n - 1 times. The 
        comparison will be done (n - 1) + 1 = n times since the condition gets checked one last time 
        before the loop terminates.
        _A(n)_ = 1 + n - 1 = n : 
        The initial assignment of i = 0 happens once, and i is incremented(assigned) every time 
        the loop runs, i.e. n - 1 times. Hence, the total number of assignments is 
        1 + n - 1 = n  
                                      
Line 4: _C(n)_ = 0 
        _A(n)_ = n - 1 : 
        The loop runs n - 1 times, and hence, the assignment will take place n - 1 times.
        
Line 5: _C(n)_ = ((n-1) + (n-2) + .. + 2 + 1) + (n - 1) = n(n - 1)/2 + (n - 1) = (n^2 + n - 2)/2 : 
        In the first iteration of the outer loop, when i = 0, the inner loop runs from j = 1 to j < n which is n - 1 
        comparisons, since j gets incremented by 1 in each iteration. In the second iteration of the outer loop, 
        when i = 1, the inner loop runs from j = 2 to j < n, which is n - 2 comparisons. This pattern continues until
        finally, in the last iteration of the outer loop, when i = n - 2, the inner loop runs from j = n - 1 to 
        j < n which is 1 comparison. Adding all of this up, we get ((n-1) + (n-2) + .. + 2 + 1) = n(n - 1)/2.
        We add the additional n - 1 to account for the one extra comparison done when the condition fails 
        and the inner loop is exited. We say n - 1 specifically because the outer loop runs n - 1 times.  
        _A(n)_ = n - 1 + ((n-1) + (n-2) + .. + 2 + 1) = (n - 1) + n(n - 1)/2 = (n^2 + n - 2)/2 : 
        The initial assignment in the inner loop of j = i + 1 happens n - 1 times since the outer loop runs n - 1 times. 
        Since the inner loop runs n(n - 1)/2 times, as explained above, the increment(assignment)
        j++ takes place n(n - 1)/2 times.  Hence, the total no. of assignments is n(n - 1)/2 + (n - 1)
         = (n^2 + n - 2)/2. 
Line 6: _C(n)_ = ((n-1) + (n-2) + .. + 2 + 1) = n(n - 1)/2 = (n^2 - n)/2 : 
        Since the inner loop runs n(n - 1)/2 times, the comparison a[j] > a[max] takes place n(n - 1)/2 = (n^2 - n)/2 
        times.
        _A(n)_ = 0 

Line 7: _C(n)_ = 0 
        _A(n)_ = ((n-1) + (n-2) + .. + 2 + 1) = n(n - 1)/2  = (n^2 - n)/2 : 
        In the worst case scenario, the if statement will be true for the n(n - 1)/2 times the loop runs, and hence 
        this assignment max = j will take place n(n - 1)/2 times.
        
Line 8: _C(n)_ = 0 
        _A(n)_ = 0
        
Line 9: _C(n)_ = 0 
        _A(n)_ = 0
        
Line 10: _C(n)_ = 0 
         _A(n)_ = n - 1 :
         Since the outer loop runs n - 1 times, the assignment temp = a[i] takes n - 1 times.
Line 11: _C(n)_ = 0 
         _A(n)_ = n - 1 :
         Since the outer loop runs n - 1 times, the assignment a[i] = a[max] takes n - 1 times.
Line 12: _C(n)_ = 0 
         _A(n)_ = n - 1 :
         Since the outer loop runs n - 1 times, the assignment a[max] = temp takes n - 1 times.
         
Line 13: _C(n)_ = 0  
         _A(n)_ = 0
         
Line 14: _C(n)_ = 0 
         _A(n)_ = 0

Total C(n) = n + (n^2 + n - 2)/2 + (n^2 - n)/2 
           = (2n^2 + 2n - 2)/2
           = n^2 + n - 1
Total A(n) = n + n - 1 + (n^2 + n - 2)/2 + (n^2 - n)/2 + 3(n - 1)
           = 5n - 4 + (2n^2 - 2)/2 = 5n - 4 + n^2 - 1
           = n^2 + 5n - 5

Therefore, asymptotic complexity of Selection Sort is O(n^2)
