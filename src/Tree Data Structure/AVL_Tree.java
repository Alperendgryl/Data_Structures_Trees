package pkg2102_project2_p1.pkg2;

public class AVL_Tree extends SuperTree { //ID

    Song root;

    public void insertSongName(String songName, int key) {
        root = insertSongName(songName, key, root);
    }

    private Song insertSongName(String songName, int key, Song focus) {
        if (focus == null) {
            focus = new Song(songName, key);
        } else if (key < focus.key) {
            focus.leftChild = insertSongName(songName, key, focus.leftChild);
            if (getBalance(focus) == 2) {
                if (key < focus.leftChild.key) {
                    focus = rotateLeft(focus);
                } else {
                    focus = doubleRotateLeft(focus);
                }
            }
        } else if (key > focus.key) {
            focus.rightChild = insertSongName(songName, key, focus.rightChild);
            if (getBalance(focus) == 2) {
                if (key > focus.rightChild.key) {
                    focus = rotateRight(focus);
                } else {
                    focus = doubleRotateRight(focus);
                }
            }
        } else {
            focus.key = key;
        }
        focus.height = Math.max(getHeight(focus.leftChild), getHeight(focus.rightChild)) + 1;
        return focus;
    }

    public void insert(String songName, String artist, int key, String genre, int year) {
        root = insertSong(songName, artist, key, genre, year, root);
    }

    private Song insertSong(String songName, String artist, int key, String genre, int year, Song focus) {
        if (focus == null) {
            focus = new Song(songName, artist, key, genre, year);
        } else if (key < focus.key) {
            focus.leftChild = insertSong(songName, artist, key, genre, year, focus.leftChild);
            if (getBalance(focus) == 2) {
                if (key < focus.leftChild.key) {
                    focus = rotateLeft(focus);
                } else {
                    focus = doubleRotateLeft(focus);
                }
            }
        } else if (key > focus.key) {
            focus.rightChild = insertSong(songName, artist, key, genre, year, focus.rightChild);
            if (getBalance(focus) == 2) {
                if (key > focus.rightChild.key) {
                    focus = rotateRight(focus);
                } else {
                    focus = doubleRotateRight(focus);
                }
            }
        } else {
            focus.key = key;
        }
        focus.height = Math.max(getHeight(focus.leftChild), getHeight(focus.rightChild)) + 1;
        return focus;
    }

    public void inOrder() {
        if (root != null) {
            System.out.println("Inorder Traversal\n");
            super.inOrderTraversal(root);
        }
    }

    public void preOrder() {
        System.out.println("Preorder Traversal\n");
        if (root != null) {
            super.preOrderTraversal(root);
        }
    }

    public void postOrder() {
        System.out.println("Postorder Traversal\n");
        if (root != null) {
            super.postOrderTraversal(root);
        }
    }

    public Song searchKey(int key) {
        return super.searchKey(this.root, key);
    }

    public Song searchSongName(String name) {
        return super.searchSongName(this.root, name);
    }

    public Song searchArtist(String artist) {
        return super.searchArtist(this.root, artist);
    }

    public Song searchGenre(String genre) {
        return super.searchGenre(root, genre);
    }

    public Song searchRange(int lowerBound, int upperBound) {
        return super.searchRange(root, lowerBound, upperBound);
    }

    public void delete(int key) {
        deleteSong(root, key);
    }

    private Song deleteSong(Song focus, int key) {
        if (focus == null) {
            return focus;
        } else if (key < focus.key) {
            focus.leftChild = deleteSong(focus.leftChild, key);
        } else if (key > focus.key) {
            focus.rightChild = deleteSong(focus.rightChild, key);
        } else {
            if ((focus.leftChild == null) || (focus.rightChild == null)) {
                focus = (focus.leftChild == null) ? focus.rightChild : focus.leftChild; // if (focus.left == null) focus = focus.rightChild; else focus = focus.leftChild;
            } else {
                Song tmpSong = getMinValue(focus.rightChild);
                focus.key = tmpSong.key;
                focus.rightChild = deleteSong(focus.rightChild, tmpSong.key);
            }
        }
        return rebalance(focus);
    }

    private int getBalance(Song focus) {
        return focus == null ? 0 : Math.abs(getHeight(focus.leftChild) - getHeight(focus.rightChild));
    }

    private int getHeight(Song focus) {
        return focus == null ? 0 : focus.getHeight(); // if(focus == null) return 0; else return focus.getHeight();
    }

    @Override
    public Song getMinValue(Song focus) {
        Song current = focus;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    @Override
    public Song getMaxValue(Song focus) {
        Song current = focus;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    private Song rebalance(Song focus) {
        if (focus == null) {
            return focus;
        }
        int balance = getBalance(focus);
        if (balance < -1) {
            if (getBalance(focus.leftChild) <= 0) {
                focus = rotateRight(focus);
            } else {
                focus.leftChild = rotateLeft(focus.leftChild);
                focus = rotateRight(focus);
            }
        }
        if (balance > 1) {
            if (getBalance(focus.rightChild) >= 0) {
                focus = rotateLeft(focus);
            } else {
                focus.rightChild = rotateRight(focus.rightChild);
                focus = rotateLeft(focus);
            }
        }
        return focus;
    }

    private Song rotateLeft(Song focus) {
        Song newLeftChild = focus.leftChild;
        focus.leftChild = newLeftChild.rightChild;
        newLeftChild.rightChild = focus;
        focus.height = Math.max(getHeight(focus.leftChild), getHeight(focus.rightChild)) + 1; // Returns the higher one. If leftChild's height is bigger than the right child's height returns leftChild.height value 
        newLeftChild.height = Math.max(getHeight(newLeftChild.leftChild), getHeight(newLeftChild.rightChild)) + 1;
        return newLeftChild;
    }

    private Song rotateRight(Song focus) {
        Song newRightChild = focus.rightChild;
        focus.rightChild = newRightChild.leftChild;
        newRightChild.leftChild = focus;
        focus.height = Math.max(getHeight(focus.leftChild), getHeight(focus.rightChild)) + 1;
        newRightChild.height = Math.max(getHeight(newRightChild.leftChild), getHeight(newRightChild.rightChild)) + 1;
        return newRightChild;
    }

    private Song doubleRotateLeft(Song focus) {
        focus.leftChild = rotateRight(focus.leftChild);
        return rotateLeft(focus);
    }

    private Song doubleRotateRight(Song focus) {
        focus.rightChild = rotateLeft(focus.rightChild);
        return rotateRight(focus);
    }

}
