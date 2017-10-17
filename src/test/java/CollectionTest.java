import com.google.common.base.Function;
import com.google.common.collect.*;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 集合类操作示例
 *
 * @author chenx
 * @date 2017/10/17
 */
@RunWith(JUnit4.class)
public class CollectionTest {
    @Test
    public void testImmutableCollection() {
        /*
         * 3种构建方式，ImmutableSet类似
         */
        List<Integer> list = ImmutableList.of(1, 2, 3);
        System.out.println(list);

        list = ImmutableList.copyOf(Lists.newArrayList(1, 2, 3));
        System.out.println(list);

        list = ImmutableList.<Integer>builder().addAll(Lists.newArrayList(1, 2)).add(3).build();
        System.out.println(list);
    }

    @Test
    public void testMultiset() {
        // Multiset 应用场景：统计单词出现次数
        List<String> words = Lists.newArrayList("you", "he", "I", "you");
        Multiset<String> wordSet = HashMultiset.create(words);
        Assert.assertEquals(wordSet.count("you"), 2);
        Assert.assertEquals(wordSet.size(), 4);

        // 相当于新增了1个元素“you”
        wordSet.add("you", 1);
        Assert.assertEquals(wordSet.count("you"), 3);
        Assert.assertEquals(wordSet.size(), 5);

        // 相当于减少了2个元素“you”
        wordSet.remove("you", 2);
        Assert.assertEquals(wordSet.count("you"), 1);
        Assert.assertEquals(wordSet.size(), 3);

        // 相当于设置元素“you”为5个
        wordSet.setCount("you", 5);
        Assert.assertEquals(wordSet.count("you"), 5);
        Assert.assertEquals(wordSet.size(), 7);

        for (Multiset.Entry<String> entry : wordSet.entrySet()) {
            System.out.println(entry.getElement());
        }
    }

    @Test
    public void testMultiMap() {
        ListMultimap<Integer, String> listMultimap = ArrayListMultimap.create();
        listMultimap.put(15, "Li Chao");
        listMultimap.put(15, "Wang Li");
        listMultimap.put(12, "Zhao Mu");
        listMultimap.put(14, "Zhao Lu");

        Map<Integer, Collection<String>> namesByAge = listMultimap.asMap();

        for (Map.Entry<Integer, Collection<String>> entry : namesByAge.entrySet()) {
            int age = entry.getKey();
            Collection<String> names = entry.getValue();

            System.out.println("age: " + age);
            System.out.println("names: " + names);
        }

        // 按照年龄值进行分组
        List<User> users = Lists.newArrayList(
                new User(1L, 12, "Lucy"),
                new User(2L, 14, "Tomy"),
                new User(3L, 11, "Lily"),
                new User(4L, 11, "Tony"),
                new User(5L, 12, "John")
        );
        ListMultimap<Integer, User> usersByAge = Multimaps.index(users, new Function<User, Integer>() {
            public Integer apply(User user) {
                return user.getAge();
            }
        });
        System.out.println(usersByAge.get(11));
        System.out.println(usersByAge.get(15));

        // 将Multimap转为Map
        Map<Integer, List<User>> usersByAgeForMap = Multimaps.asMap(usersByAge);
        System.out.println(usersByAgeForMap.get(11));
        System.out.println(usersByAgeForMap.get(15));
    }

    @Test
    public void testBiMap() {
        BiMap<String, Integer> nameToId = ImmutableBiMap.of("Li Chao", 1, "Lucy", 2);
        Assert.assertTrue(1 == nameToId.get("Li Chao"));
        BiMap<Integer, String> idToName = nameToId.inverse();
        Assert.assertEquals(idToName.get(2), "Lucy");
    }

    @Test
    public void testTable() {
        Table<Integer, Integer, String> nameGraph = HashBasedTable.create();
        nameGraph.put(1, 1, "Li");
        nameGraph.put(1, 2, "Wang");
        nameGraph.put(1, 3, "Zhou");
        nameGraph.put(2, 1, "Wu");
        nameGraph.put(2, 2, "Zheng");

        System.out.println(nameGraph.row(2));
        System.out.println(nameGraph.column(2));
        System.out.println(nameGraph.get(1, 3));
    }
}
