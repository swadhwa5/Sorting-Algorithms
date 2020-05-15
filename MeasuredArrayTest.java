package hw3;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeasuredArrayTest {

  private static final int SIZE = 20;
  private static final String VAL = "test";
  private MeasuredArray<String> array;


  @Before
  public void createArray() {

    this.array = new MeasuredArray<>(SIZE, VAL);
  }

  @Test
  public void NewArrayLengthWorks(){

    assertEquals(SIZE, array.length());
  }

  @Test(expected = LengthException.class)
  public void ArrayThrowsLengthExceptionForNegativeLength() {
    int BAD_SIZE = -2;
    MeasuredArray<String> badLengthArray = new MeasuredArray<>(BAD_SIZE, VAL);
  }

  @Test
  public void newArrayZeroMutations() {

    assertEquals(0, array.mutations());
  }

  @Test
  public void newArrayZeroAccesses() {

    assertEquals(0, array.accesses());
  }

  @Test
  public void getUpdatesAccesses() {
    assertEquals(array.get(0), VAL); // or temp = get(i) ?
    assertEquals(1, array.accesses());
  }

  @Test (expected = IndexException.class)
  public void AccessesNotUpdatedWhenGetThrowsException() {
    array.get(-1);
    assertEquals(0, array.accesses());
    array.get(SIZE);
    assertEquals(0, array.accesses());
  }

  @Test
  public void putUpdatesMutations() {
    array.put(SIZE-1, "hello");
    assertEquals(1, array.mutations());
  }

  @Test (expected = IndexException.class)
  public void MutationsNotUpdatedWhenPutThrowsException() {
    array.put(-1, "anything");
    assertEquals(0, array.mutations());
    array.put(SIZE, "anything");
    assertEquals(0, array.mutations());
  }

  @Test
  public void testReset() {
    array.get(SIZE/2);
    assertEquals(1, array.accesses());
    array.put(SIZE - 2, "anything");
    assertEquals(1, array.mutations());
    array.reset();
    assertEquals(0, array.accesses());
    assertEquals(0, array.mutations());
  }

  @Test
  public void testCount() {
    assertEquals(SIZE, array.count("test"));
    array.put(SIZE-5, "twice");
    array.put(SIZE-7, "twice");
    assertEquals(2, array.count("twice"));
  }

  @Test
  public void countUpdatesAccesses() {
    array.count("anything");
    assertEquals(array.accesses(), SIZE);
  }

  @Test
  public void countUpdatesAccessesWhenElementNotPresent() {
    array.count("Absent");
    assertEquals(array.accesses(), SIZE);
  }

}



