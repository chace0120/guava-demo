import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * 前置条件判断示例
 *
 * @author chenx
 * @date 2017/10/10
 */
@RunWith(JUnit4.class)
public class PreconditionsTest {
    @Test
    public void testCheckArg() {
        int i = 1;
        checkArgument(i > 1);
    }

    @Test
    public void testCheckArgWithMsg() {
        int i = 1;
        checkArgument(i > 1, "Expected i > 1, but %s <= 1", i);
    }

    @Test
    public void testCheckNotNull() {
        Integer value = null;
        checkNotNull(value, "This value is null");
    }

    @Test
    public void testCheckElementIndex() {
        List<Integer> nums = Lists.newArrayList(1,2,3,4,5);
        int index = 5;
        checkElementIndex(index, nums.size());
    }

    @Test
    public void testCheckPositionIndex() {
        List<Integer> nums = Lists.newArrayList(1,2,3,4,5);
        int position = 5;
        checkPositionIndex(position, nums.size());
    }

    @Test
    public void testCheckPositionIndexes() {
        List<Integer> nums = Lists.newArrayList(1,2,3,4,5);
        int start = 3;
        int end = 2;
        checkPositionIndexes(start, end, nums.size());
    }
}
