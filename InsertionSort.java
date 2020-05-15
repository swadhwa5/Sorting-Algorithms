package hw3;


/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  // Helper to make code more readable.
  private boolean less(T a, T b) {

    return a.compareTo(b) < 0;
  }

  //At each iteration, insertion sort considers
  //one element from the input data sequentially, finds the location
  //it belongs within the sorted part of the list, and inserts it there.
  //Insertion sort sorts the list from left to right.
  @Override
  public void sort(Array<T> array) {
    int n = array.length();
    //It starts with index 1 and not 0 because the sorted list
    //on the left begins with one element, i.e. array[0].
    for (int i = 1; i < n; i++) {
      T temp = array.get(i);
      int j = i - 1;
      //Until the elements on the left are greater than the
      //main element(ith) being considered, the algorithm
      //shifts all the elements on the left towards right by 1
      //and then inserts the main element at the correct position in the
      //sorted part of the list.
      while (j >= 0 && this.less(temp, array.get(j))) {

        array.put(j + 1, array.get(j));
        j--;
      }
      array.put(j + 1, temp);
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
