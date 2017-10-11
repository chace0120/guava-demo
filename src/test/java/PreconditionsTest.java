import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
}
