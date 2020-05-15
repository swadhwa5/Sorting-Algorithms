package hw3;

import exceptions.IndexException;
import exceptions.LengthException;

/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {

  private int accessCount;
  private int mutateCount;

  /**
   * Constructor for a MeasuredArray that calls the SimpleArray constructor.
   *
   * @param n The size of the array.
   * @param t The initial value to set every object to in the array..
   * @throws LengthException if n &le; 0.
   */
  public MeasuredArray(int n, T t) throws LengthException {
    super(n, t);
    accessCount = 0;
    mutateCount = 0;
  }

  /**
   * Get the declared capacity of the array.
   *
   * @return the length
   */
  @Override
  public int length() {

    return super.length();
  }

  /**
   * Get the value in the array at the given index.
   *
   * @param i the position of the value requested;
   * @return the value at that position
   * @throws IndexException when <code>index < 0 or index >= length()</code>
   */
  @Override
  public T get(int i) throws IndexException {
    T temp = super.get(i);
    accessCount++;
    return temp;
  }

  /**
   * Put a value into the array at the given index.
   *
   * @param i the position it goes;
   * @param t the value being put at the position i
   * @throws IndexException when <code>index < 0 or index >= length()</code>
   */
  @Override
  public void put(int i, T t) throws IndexException {
    super.put(i, t);
    mutateCount++;
  }

  /**
   * Sets/Resets the value of accessCount and mutateCount to zero.
   */
  @Override
  public void reset() {
    accessCount = 0;
    mutateCount = 0;
  }

  /**
   *Gives the access count.
   *
   * @return the no. of accesses
   */
  @Override
  public int accesses() {

    return accessCount;
  }

  /**
   *Gives the mutation count.
   *
   * @return the no. of mutations
   */
  @Override
  public int mutations() {

    return mutateCount;
  }

  /**
   *Gives the no. of occurrences of a particular value in the array.
   *
   * @param t The value to count.
   * @return the no. of occurrences of the value t
   */
  @Override
  public int count(T t) {
    int occurrences = 0;
    for (int i = 0; i < this.length(); i++) {
      if (this.get(i) == t) {
        occurrences++;
      }
    }
    return occurrences;
  }

}
