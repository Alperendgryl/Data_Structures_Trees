package pkg2102_project2_p1.pkg2;

public class BS_Tree extends SuperTree {

    Song root;

    public void insertArtistName(String artist, int key) {
        if (root == null) {
            root = new Song<>(artist, key);
        } else {
            insertArtistName(artist, key, root);
        }
    }

    private void insertArtistName(String artist, int key, Song focus) {
        if (focus.compareTo(key) > 0) {
            if (focus.getLeftChild() != null) {
                insertArtistName(artist, key, focus.getLeftChild());
            } else {
                Song newSong = new Song<>(artist, key);
                focus.setLeftChild(newSong);
            }
        } else {
            if (focus.getRightChild() != null) {
                insertArtistName(artist, key, focus.getRightChild());
            } else {
                Song newSong = new Song<>(artist, key);
                focus.setRightChild(newSong);
            }
        }
    }

    public void insert(String songName, String artist, int key, String genre, int year) {
        if (root == null) {
            root = new Song<>(songName, artist, key, genre, year);
        } else {
            insertSong(songName, artist, key, genre, year, root);
        }
    }

    private void insertSong(String songName, String artist, int key, String genre, int year, Song focus) {
        if (focus.compareTo(key) > 0) {
            if (focus.getLeftChild() != null) {
                insertSong(songName, artist, key, genre, year, focus.getLeftChild());
            } else {
                Song newSong = new Song<>(songName, artist, key, genre, year);
                focus.setLeftChild(newSong);
            }
        } else {
            if (focus.getRightChild() != null) {
                insertSong(songName, artist, key, genre, year, focus.getRightChild());
            } else {
                Song newSong = new Song<>(songName, artist, key, genre, year);
                focus.setRightChild(newSong);
            }
        }
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
        root = (root != null) ? deleteSong(root, key) : null;
    }

    private Song deleteSong(Song focus, int key) {
        if (focus == null) {
            return focus;
        }
        if (focus.compareTo(key) > 0) { //key is smaller than the key song, go left
            focus.setLeftChild(deleteSong(focus.getLeftChild(), key));
        } else if (focus.compareTo(key) < 0) { //key is larger than the key song, go right
            focus.setRightChild(deleteSong(focus.getRightChild(), key));
        } else {
            if (focus.getLeftChild() == null && focus.getRightChild() == null) {
                System.out.println("\nRemoving a leaf node");
                return null;
            }
            if (focus.getLeftChild() == null) {
                System.out.println("\nRemoving the right child");
                Song tempSong = focus.getRightChild();
                focus = null;
                return tempSong;
            } else if (focus.getRightChild() == null) {
                System.out.println("\nRemoving the left child");
                Song tempSong = focus.getLeftChild();
                focus = null;
                return tempSong;
            }
            System.out.println("\nRemoving item with two children");
            Song tempSong = getPredecessor(focus.getLeftChild());
            focus.setKey(tempSong.getKey());
            focus.setLeftChild(deleteSong(focus.getLeftChild(), tempSong.getKey()));
        }

        return focus;
    }

    private Song getPredecessor(Song focus) {
        if (focus.getRightChild() != null) {
            return getPredecessor(focus.getRightChild());
        }
        System.out.println("Predecessor is: " + focus);
        return focus;
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

}
