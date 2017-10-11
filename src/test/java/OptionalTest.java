import com.google.common.base.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Optional操作示例
 *
 * @author chenx
 * @date 2017/10/10
 */
@RunWith(JUnit4.class)
public class OptionalTest {
    @Test
    public void testOf() {
        // of方法中的引用不能为null，否则抛出NPE
        Optional<Integer> result = Optional.of(5);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(5, result.get().intValue());
    }

    @Test
    public void testAbsent() {
        // 创建引用缺失的Optional实例
        Optional<Integer> result = Optional.absent();
        Assert.assertFalse(result.isPresent());
        Assert.assertEquals(0, result.or(0).intValue());
    }

    @Test
    public void testFromNullable() {
        Integer value = null;
        Optional<Integer> result = Optional.fromNullable(value);
        Assert.assertFalse(result.isPresent());
        Assert.assertEquals(result.or(0).intValue(), 0);
    }
}
