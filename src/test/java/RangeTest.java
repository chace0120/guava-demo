import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 区间操作相关示例
 *
 * @author chenx
 * @date 2017/10/21
 */
@RunWith(JUnit4.class)
public class RangeTest {
    @Test
    public void testRangeCreate() {
        Range<Integer> range1 = Range.open(1, 3); // (1, 3)
        Assert.assertTrue(range1.hasLowerBound());
        Assert.assertTrue(range1.hasUpperBound());
        Assert.assertEquals(range1.lowerBoundType(), BoundType.OPEN);
        Assert.assertEquals(range1.upperBoundType(), BoundType.OPEN);
        Assert.assertEquals(range1.lowerEndpoint().intValue(), 1);
        Assert.assertEquals(range1.upperEndpoint().intValue(), 3);

        Range<Integer> range2 = Range.closed(1, 3); // [1, 3]
        Assert.assertTrue(range2.hasLowerBound());
        Assert.assertTrue(range2.hasUpperBound());
        Assert.assertEquals(range2.lowerBoundType(), BoundType.CLOSED);
        Assert.assertEquals(range2.upperBoundType(), BoundType.CLOSED);
        Assert.assertEquals(range2.lowerEndpoint().intValue(), 1);
        Assert.assertEquals(range2.upperEndpoint().intValue(), 3);

        Range<Integer> range3 = Range.closedOpen(1, 3); // [1, 3)
        Assert.assertTrue(range3.hasLowerBound());
        Assert.assertTrue(range3.hasUpperBound());
        Assert.assertEquals(range3.lowerBoundType(), BoundType.CLOSED);
        Assert.assertEquals(range3.upperBoundType(), BoundType.OPEN);
        Assert.assertEquals(range3.lowerEndpoint().intValue(), 1);
        Assert.assertEquals(range3.upperEndpoint().intValue(), 3);

        Range<Integer> range4 = Range.atLeast(6); // [6, ..)
        Assert.assertTrue(range4.hasLowerBound());
        Assert.assertFalse(range4.hasUpperBound());
        Assert.assertEquals(range4.lowerBoundType(), BoundType.CLOSED);
        Assert.assertEquals(range4.lowerEndpoint().intValue(), 6);

        Range<Integer> range5 = Range.atMost(6); // (.., 6]
        Assert.assertFalse(range5.hasLowerBound());
        Assert.assertTrue(range5.hasUpperBound());
        Assert.assertEquals(range5.upperBoundType(), BoundType.CLOSED);
        Assert.assertEquals(range5.upperEndpoint().intValue(), 6);
    }

    @Test
    public void testIsEmpty() {
        Range<Integer> range1 = Range.openClosed(4, 4); // (4, 4]
        Assert.assertTrue(range1.isEmpty());

        Range<Integer> range2 = Range.closedOpen(4, 4); // [4, 4)
        Assert.assertTrue(range2.isEmpty());

        Range<Integer> range3 = Range.closed(4, 4); // [4, 4]
        Assert.assertFalse(range3.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsEmptyExp() {
        Range<Integer> range1 = Range.open(4, 4); // (4, 4)
        range1.isEmpty();
    }

    @Test
    public void testContains() {
        Range<Integer> range2 = Range.open(1, 3); // (1, 3)
        Assert.assertTrue(range2.contains(2));
        Assert.assertFalse(range2.contains(1));
    }

    @Test
    public void testIsConnected() {
        Range<Integer> range1 = Range.open(3, 5);
        Assert.assertTrue(range1.isConnected(Range.closedOpen(5, 7)));
        Assert.assertFalse(range1.isConnected(Range.open(5, 7)));

        Range<Integer> range2 = Range.open(0, 9);
        Assert.assertTrue(range2.isConnected(Range.closed(3, 4)));
        Assert.assertTrue(range2.isConnected(Range.open(3, 4)));

        Range<Integer> range3 = Range.closed(1, 5);
        Assert.assertFalse(range3.isConnected(Range.closed(6, 10)));
    }

    @Test
    public void testIntersection() {
        Range<Integer> range1 = Range.closed(3, 5);
        System.out.println(range1.intersection(Range.open(5, 7))); // (5, 5]

        Range<Integer> range2 = Range.closed(0, 9);
        System.out.println(range2.intersection(Range.open(3, 4))); // (3, 4)

        Range<Integer> range3 = Range.closed(0, 5);
        System.out.println(range3.intersection(Range.closed(3, 9))); // [3, 5]
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectionExt() {
        Range<Integer> range1 = Range.open(0, 5);
        System.out.println(range1.intersection(Range.open(5, 7)));
    }

    @Test
    public void testSpan() {
        Range range1 = Range.open(1, 5);
        System.out.println(range1.span(Range.open(6, 8))); // (1, 8)

        Range range2 = Range.open(3, 6);
        System.out.println(range2.span(Range.closedOpen(6, 8))); // (3, 8)

        Range<Integer> range3 = Range.closed(0, 9);
        System.out.println(range3.span(Range.open(3, 4))); // [0, 9]

        Range<Integer> range4 = Range.closed(0, 5);
        System.out.println(range4.span(Range.closed(3, 9))); // [0, 9]
    }
}
