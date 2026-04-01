package com.happy.alg.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.happy.alg.util.MyHashMap.LinkedHashMapEntry;

public class MyHashMap<K, V> extends HashMap<K, V> implements MyMap<K, V> {

    // 成员变量
    transient MyNode<K, V>[] table; // table 为数组样式
    static final int MAXIMUM_CAPACITY = 1 << 30; // 最大的容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 默认初始的大小 16
    int threshold; // 成员变量 threshold 表示什么时候需要触发 resize() 扩容,既是扩容的临界值，默认是 capacity * loadFactor
    final float loadFactor;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;// 负载因子
    transient int modCount;// modification count（修改计数）, 防止多线程使用的，用于快速失败机制（fail-fast）检测并发修改
    transient int size; // size 成员变量表示当前 HashMap 中的键值对数量
    static final int TREEIFY_THRESHOLD = 8;// 数组下的链表转化为树的阈值
    static final int MIN_TREEIFY_CAPACITY = 64;// 最小树化容量

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    // 打印 HashMap 中的元素，按照桶的索引顺序打印，每个桶中的元素以链表形式展示
    public void PrintHashContainTable() {
        MyNode<K, V>[] tab;
        int n;
        if ((tab = table) != null && (n = tab.length) > 0) {
            for (int i = 0; i < n; i++) {
                MyNode<K, V> e = tab[i];
                while (e != null) {
                    System.out.print("<" + e.key + ":" + e.value + ">");
                    e = e.next;
                    if (e != null) {
                        System.out.print("->");
                    }
                }
                if (tab[i] != null) {
                    System.out.println();
                }
            }
        } else {
            System.out.println("HashMap is empty.");
        }
    }

    // reference to HashMap
    // public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>,
    // Cloneable, Serializable
    // public abstract class AbstractMap<K,V> implements Map<K,V>
    // public interface Map<K, V>

    // 接口下，再次声明接口
    static class MyNode<K, V> implements MyMap.MyEntry<K, V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.MyNode<K, V> next;

        // construction MyNode
        MyNode(int hash, K key, V value, MyHashMap.MyNode<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            // 这里的 e 写法
            // Java 16+ 引入的模式匹配:instanceof 不仅判断类型，如果匹配成功，还会直接将 o 转换并赋值给模式变量 e
            return o instanceof Map.Entry<?, ?> e && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }

    }

    // 红黑树节点，继承自 MyNode，增加了红黑树特有的属性和链接
    // MyTreeNode 算是 MyNode 的一个特殊版本，类型是子类,专门用于当链表过长时，将链表转换为红黑树以优化性能
    static final class MyTreeNode<K, V> extends LinkedHashMapEntry<K, V> {
        MyTreeNode<K, V> parent; // red-black tree links
        MyTreeNode<K, V> left;
        MyTreeNode<K, V> right;
        MyTreeNode<K, V> prev; // needed to unlink next upon deletion
        boolean red;

        MyTreeNode(int hash, K key, V val, MyNode<K, V> next) {
            super(hash, key, val, next);
        }

        final MyTreeNode<K, V> putTreeVal(HashMap<K, V> map, MyNode<K, V>[] tab,
                int h, K k, V v) {
            return null;
        }

        public void treeify(MyNode<K, V>[] tab) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'treeify'");
        }
    }

    static class LinkedHashMapEntry<K, V> extends MyHashMap.MyNode<K, V> {
        LinkedHashMapEntry<K, V> before, after;

        LinkedHashMapEntry(int hash, K key, V value, MyNode<K, V> next) {
            super(hash, key, value, next);
        }
    }

    static final int hash(Object key) {
        if (key != null) {
            if (key instanceof String keyString) { // 以 a 结尾的 string，强制 hash 值为 1，测试碰撞
                if (keyString.endsWith("a")) {
                    return 1;
                }
            }
        }
        int h;
        // key==null 的情况下，hashcode=0
        // key.hashCode() 获取原始的 32 位整型哈希值
        // h >>> 16: 这是无符号右移 16 位。原本的高 16 位移动到了低 16 位的位置，而高 16 位补 0
        // ^ (XOR): 将原始的 h 与右移后的值进行异或运算。
        // “扰动函数”（Perturbation Function）,通过 (h ^ (h >>> 16))，
        // 我们将原始哈希值的高 16 位“混合”到了低 16 位中。这样即使数组长度很小，
        // 低位的索引值也包含了高位的特征。异或运算能让结果位为 0 或 1 的概率保持在 50% 左右
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // resize方法：
    // 1.防止碰撞链（或树）过长，保持 O(1) 平均查找/插入复杂度
    // 2.保证负载因子（size / capacity）不超过设定值（默认 0.75）
    // 3.关键就是“扩容 + 重新分配/重哈希节点”
    final MyNode<K, V>[] resize() {
        MyNode<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        } else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else { // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        threshold = newThr;
        @SuppressWarnings({ "rawtypes", "unchecked" })
        MyNode<K, V>[] newTab = (MyNode<K, V>[]) new MyNode[newCap];
        table = newTab;
        if (oldTab != null) {
            // 老的 Tab 存在内容的时候，进行迁移，但是现在还没有到这个地方！
        }
        return table;
    }

    // Create a regular (non-tree) mynode 新建一个节点，非树节点
    MyNode<K, V> newNode(int hash, K key, V value, MyNode<K, V> next) {
        return new MyNode<>(hash, key, value, next);
    }

    // Callbacks to allow LinkedHashMap post-actions
    // 不是“冗余”，而是标准的扩展点（hook methods），用于保持 API/实现兼容性和可扩展性。
    void afterNodeAccess(MyNode<K, V> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }

    void afterNodeRemoval(MyNode<K, V> p) {
    }

    // 当链表长度达到 TREEIFY_THRESHOLD 时，调用 treeifyBin 方法将链表转换为红黑树，以优化性能
    // 树化不是单纯看链表长度，还需要 MIN_TREEIFY_CAPACITY = 64（桶数组容量 >= 64），否则会优先 resize()
    // 扩容，而不是树化。
    final void treeifyBin(MyNode<K, V>[] tab, int hash) {
        int n, index;
        MyNode<K, V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            MyTreeNode<K, V> hd = null, tl = null;
            do {
                MyTreeNode<K, V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }

    private MyTreeNode<K, V> replacementTreeNode(MyNode<K, V> e, Object next) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replacementTreeNode'");
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Implements Map.put and related methods.
     *
     * @param hash         hash for key
     * @param key          the key
     * @param value        the value to put
     * @param onlyIfAbsent if true, don't change existing value 默认值为 false
     * @param evict        if false, the table is in creation mode. 默认值为 true
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        MyNode<K, V>[] tab;
        MyNode<K, V> p;
        int n, i;
        // 先是判断 table 是否为 null，如果为 null，则进行 resize
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;// 这里的resize()，标识扩容，但是如果没有扩容也能包含初始化
        if ((p = tab[i = (n - 1) & hash]) == null) // 判断存放的位置是否为 null，如果为 null 直接复制
            tab[i] = newNode(hash, key, value, null);
        else {
            // 如果存放的位置，不为空，则说明当前的 hash 位置存在多个元素，有可能是一个链表或者一棵树
            // 最起码 p 不为 null
            MyNode<K, V> e;
            K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;// 当前的位置，就是需要存放 <key，value> 的位置：e
            else if (p instanceof MyTreeNode)
                // 如果是红黑节点树，由红黑节点树操作具体的存放元素putTreeVal
                e = ((MyTreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0;; ++binCount) {
                    if ((e = p.next) == null) {// 如果P 后面没有元素了，直接插入到尾巴上
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    public V get(Object key) {
        MyNode<K, V> e;
        return (e = getNode(key)) == null ? null : e.value;
    }

    final MyNode<K, V> getNode(Object key) {
        MyNode<K, V>[] tab;
        MyNode<K, V> first, e;
        int n, hash;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & (hash = hash(key))]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                // if (first instanceof TreeNode)
                // return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

}
