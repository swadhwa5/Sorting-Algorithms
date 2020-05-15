package hw3;


/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 *
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  // Helper to make code more readable.
  private boolean less(T a, T b) {

    return a.compareTo(b) < 0;
  }

  @Override
  //Bubble sort repeatedly steps through the list,
  // compares adjacent elements and swaps them
  // if they are in the wrong order, i.e. if the
  //element on the right is smaller than the element
  //on the left.
  //At the end of each i th iteration, the largest element gets
  //placed at the n-i-1 th index.
  public void sort(Array<T> array) {
    int n = array.length();
    for (int i = 0; i < n - 1; i++) {
      //this counter keeps tab of the no. of swaps in
      // each iteration of the outer loop.
      // If the no. of swaps in a particular
      // iteration is 0, then the array is sorted and
      //the sorting algorithm is stopped right there
      // for optimization.
      int swap = 0;
      //the loop goes till j < n - i - 1 since we
      // assume that the n - i - 1 th index
      //contains the right value that was placed
      // there in the previous iteration.
      for (int j = 0; j < n - i - 1; j++) {
        //If the element on the right is
        // smaller than the element on the left,
        // then swap, since we are trying to take the
        // largest element to the n - i - 1 th position.
        if (this.less(array.get(j + 1), array.get(j))) {
          swap++;
          //swap
          T temp = array.get(j);
          array.put(j, array.get(j + 1));
          array.put(j + 1, temp);
        }
      }
      if (swap == 0) {
        break;
      }
    }
  }

  @Override
  public String name() {

    return "Bubble Sort";
  }
}
