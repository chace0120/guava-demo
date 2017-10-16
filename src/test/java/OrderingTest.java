import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

/**
 * 排序操作示例
 *
 * @author chenx
 * @date 2017/10/11
 */
@RunWith(JUnit4.class)
public class OrderingTest {
    @Test
    public void testGreatestOf() {
        List<Integer> nums = Lists.newArrayList(2, 1, 5, 4);
        Ordering<Integer> orderingForNums = Ordering.natural();
        List<Integer> greatestOfNums = orderingForNums.greatestOf(nums, 2);

        Assert.assertEquals(greatestOfNums.get(0).intValue(), 5);
        Assert.assertEquals(greatestOfNums.get(1).intValue(), 4);
    }

    @Test
    public void testOnResultOf() {
        List<User> users = Lists.newArrayList(
                new User(1L, 12, "Lucy"),
                new User(2L, 13, "Tomy"),
                new User(3L, 11, "Lily"),
                new User(4L, 11, "Tony"),
                new User(5L, null, "John")
        );

        // 按照用户年龄的自然顺序排序，没有年龄的排在最前
        Ordering<User> orderingForUsers = Ordering.natural().nullsFirst().onResultOf(new Function<User, Comparable>() {
            public Comparable apply(User user) {
                return user.getAge();
            }
        });
        Collections.sort(users, orderingForUsers);

        System.out.println(users);

        // 用户年龄相同时，按照用户id降序排列
        Ordering<User> orderingById = Ordering.natural().onResultOf(new Function<User, Comparable>() {
            public Comparable apply(User user) {
                return user.getId();
            }
        });
        orderingById = orderingById.reverse();
        orderingForUsers = orderingForUsers.compound(orderingById);
        Collections.sort(users, orderingForUsers);

        System.out.println(users);

        // 按照相反的排序方式排列
        orderingForUsers = orderingForUsers.reverse();
        Collections.sort(users, orderingForUsers);

        System.out.println(users);
    }
}
