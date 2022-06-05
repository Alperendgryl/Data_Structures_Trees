package pkg2102_project2_p1.pkg2;

import java.util.NoSuchElementException;

public class RB_Tree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    RedBlackSong RB_root;

    private boolean isRed(RedBlackSong focus) {
        if (focus == null) {
            return false;
        }
        return focus.color == RED;
    }

    private int size(RedBlackSong focus) {
        if (focus == null) {
            return 0;
        }
        return focus.size;
    }

    public int size() {
        return size(RB_root);
    }

    public boolean isEmpty() {
        return RB_root == null;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        return get(RB_root, key);
    }

    private Value get(RedBlackSong focus, Key key) {
        Value val = focus.val;
        while (focus != null) {
            int cmp = key.compareTo(key);
            if (cmp < 0) {
                focus = focus.leftChild;
            } else if (cmp > 0) {
                focus = focus.rightChild;
            } else {
                return val;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("Null exception");
        }
        if (val == null) {
            delete(key);
            return;
        }
        RB_root = put(RB_root, key, val);
        RB_root.color = BLACK;
    }

    private RedBlackSong put(RedBlackSong focus, Key key, Value val) {
        if (focus == null) {
            return new RedBlackSong(key, val, RED, 1);
        }
        int cmp = key.compareTo(focus.key);
        if (cmp < 0) {
            focus.leftChild = put(focus.leftChild, key, val);
        } else if (cmp > 0) {
            focus.rightChild = put(focus.rightChild, key, val);
        } else {
            focus.val = val;
        }
        if (isRed(focus.rightChild) && !isRed(focus.leftChild)) {
            focus = rotateLeft(focus);
        }
        if (isRed(focus.leftChild) && isRed(focus.leftChild.leftChild)) {
            focus = rotateRight(focus);
        }
        if (isRed(focus.leftChild) && isRed(focus.rightChild)) {
            flipColors(focus);
        }
        focus.size = size(focus.leftChild) + size(focus.rightChild) + 1;
        return focus;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow");
        }
        if (!isRed(RB_root.leftChild) && !isRed(RB_root.rightChild)) {
            RB_root.color = RED;
        }
        RB_root = deleteMin(RB_root);
        if (!isEmpty()) {
            RB_root.color = BLACK;
        }
    }

    private RedBlackSong deleteMin(RedBlackSong focus) {
        if (focus.leftChild == null) {
            return null;
        }
        if (!isRed(focus.leftChild) && !isRed(focus.leftChild.leftChild)) {
            focus = moveRedLeft(focus);
        }
        focus.leftChild = deleteMin(focus.leftChild);
        return balance(focus);
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow");
        }
        if (!isRed(RB_root.leftChild) && !isRed(RB_root.rightChild)) {
            RB_root.color = RED;
        }
        RB_root = deleteMax(RB_root);
        if (!isEmpty()) {
            RB_root.color = BLACK;
        }
    }

    private RedBlackSong deleteMax(RedBlackSong focus) {
        if (isRed(focus.leftChild)) {
            focus = rotateRight(focus);
        }

        if (focus.rightChild == null) {
            return null;
        }

        if (!isRed(focus.rightChild) && !isRed(focus.rightChild.leftChild)) {
            focus = moveRedRight(focus);
        }

        focus.rightChild = deleteMax(focus.rightChild);

        return balance(focus);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Null exception");
        }
        if (!contains(key)) {
            return;
        }
        if (!isRed(RB_root.leftChild) && !isRed(RB_root.rightChild)) {
            RB_root.color = RED;
        }
        RB_root = delete(RB_root, key);
        if (!isEmpty()) {
            RB_root.color = BLACK;
        }
    }

    private RedBlackSong delete(RedBlackSong focus, Key key) {
        if (key.compareTo(focus.key) < 0) {
            if (!isRed(focus.leftChild) && !isRed(focus.leftChild.leftChild)) {
                focus = moveRedLeft(focus);
            }
            focus.leftChild = delete(focus.leftChild, key);
        } else {
            if (isRed(focus.leftChild)) {
                focus = rotateRight(focus);
            }
            if (key.compareTo(focus.key) == 0 && (focus.rightChild == null)) {
                return null;
            }
            if (!isRed(focus.rightChild) && !isRed(focus.rightChild.leftChild)) {
                focus = moveRedRight(focus);
            }
            if (key.compareTo(focus.key) == 0) {
                RedBlackSong x = min(focus.rightChild);
                focus.key = x.key;
                focus.val = x.val;
                focus.rightChild = deleteMin(focus.rightChild);
            } else {
                focus.rightChild = delete(focus.rightChild, key);
            }
        }
        return balance(focus);
    }

    private RedBlackSong rotateRight(RedBlackSong focus) {
        assert (focus != null) && isRed(focus.leftChild);
        RedBlackSong x = focus.leftChild;
        focus.leftChild = x.rightChild;
        x.rightChild = focus;
        x.color = x.rightChild.color;
        x.rightChild.color = RED;
        x.size = focus.size;
        focus.size = size(focus.leftChild) + size(focus.rightChild) + 1;
        return x;
    }

    private RedBlackSong rotateLeft(RedBlackSong focus) {
        assert (focus != null) && isRed(focus.rightChild);
        RedBlackSong x = focus.rightChild;
        focus.rightChild = x.leftChild;
        x.leftChild = focus;
        x.color = x.leftChild.color;
        x.leftChild.color = RED;
        x.size = focus.size;
        focus.size = size(focus.leftChild) + size(focus.rightChild) + 1;
        return x;
    }

    private void flipColors(RedBlackSong focus) {
        focus.color = !focus.color;
        focus.leftChild.color = !focus.leftChild.color;
        focus.rightChild.color = !focus.rightChild.color;
    }

    private RedBlackSong moveRedLeft(RedBlackSong focus) {
        flipColors(focus);
        if (isRed(focus.rightChild.leftChild)) {
            focus.rightChild = rotateRight(focus.rightChild);
            focus = rotateLeft(focus);
            flipColors(focus);
        }
        return focus;
    }

    private RedBlackSong moveRedRight(RedBlackSong h) {
        flipColors(h);
        if (isRed(h.leftChild.leftChild)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private RedBlackSong balance(RedBlackSong focus) {
        if (isRed(focus.rightChild) && !isRed(focus.leftChild)) {
            focus = rotateLeft(focus);
        }
        if (isRed(focus.leftChild) && isRed(focus.leftChild.leftChild)) {
            focus = rotateRight(focus);
        }
        if (isRed(focus.leftChild) && isRed(focus.rightChild)) {
            flipColors(focus);
        }
        focus.size = size(focus.leftChild) + size(focus.rightChild) + 1;
        return focus;
    }

    public int height() {
        return height(RB_root);
    }

    private int height(RedBlackSong x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.leftChild), height(x.rightChild));
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(RB_root).key;
    }

    private RedBlackSong min(RedBlackSong focus) {
        if (focus.leftChild == null) {
            return focus;
        } else {
            return min(focus.leftChild);
        }
    }

    public boolean isBalanced() {
        int black = 0;
        RedBlackSong focus = RB_root;
        while (focus != null) {
            if (!isRed(focus)) {
                black++;
            }
            focus = focus.leftChild;
        }
        return isBalanced(RB_root, black);
    }

    private boolean isBalanced(RedBlackSong focus, int black) {
        if (focus == null) {
            return black == 0;
        }
        if (!isRed(focus)) {
            black--;
        }
        return isBalanced(focus.leftChild, black) && isBalanced(focus.rightChild, black);
    }

    private class RedBlackSong {

        Key key;
        Value val;
        RedBlackSong leftChild, rightChild;
        boolean color;
        int size;

        public RedBlackSong(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

}
