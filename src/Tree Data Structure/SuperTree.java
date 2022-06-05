package pkg2102_project2_p1.pkg2;

public class SuperTree {

    void inOrderTraversal(Song focus) {
        if (focus.getLeftChild() != null) {
            inOrderTraversal(focus.getLeftChild());
        }
        System.out.print(focus + "\n");
        if (focus.getRightChild() != null) {
            inOrderTraversal(focus.getRightChild());
        }
    }

    void preOrderTraversal(Song focus) {
        System.out.print(focus + "\n");
        if (focus.getLeftChild() != null) {
            preOrderTraversal(focus.getLeftChild());
        }
        if (focus.getRightChild() != null) {
            preOrderTraversal(focus.getRightChild());
        }
    }

    void postOrderTraversal(Song focus) {
        if (focus.getLeftChild() != null) {
            postOrderTraversal(focus.getLeftChild());
        }
        if (focus.getRightChild() != null) {
            preOrderTraversal(focus.getRightChild());
        }
        System.out.print(focus + "\n");
    }

    Song searchKey(Song focus, int key) {
        if (focus == null) {
            System.out.print("KEY cannot be found : " + key + " : ");
            return null;
        }
        if (focus.key == key) { //found return the node
            return focus;
        } else if (focus.compareTo(key) > 0) { // if provided key is smaller than the key of current node, go left.......focus.key > key
            return searchKey(focus.getLeftChild(), key);
        } else {
            return searchKey(focus.getRightChild(), key);
        }
    }

    Song searchRange(Song focus, int lowerBound, int upperBound) {
        if (focus == null) {
            return null;
        }
        if (focus.compareTo(lowerBound) > 0) { // checks left subtree lowerbound < focus.key
            searchRange(focus.leftChild, lowerBound, upperBound);
        }
        if (lowerBound <= focus.key && upperBound >= focus.key) {
            System.out.print(focus + "\n");
        }
        return searchRange(focus.rightChild, lowerBound, upperBound); //checks right subtree
    }

    Song searchSongName(Song focus, String songName) {
        if (focus == null) {
//            System.out.print("SONG NAME cannot be found : " + songName + " : ");
            return null;
        }

        if (focus.songName.equalsIgnoreCase(songName)) {
            System.out.println(focus + "\n");
        }
        searchSongName(focus.getLeftChild(), songName);
        return searchSongName(focus.getRightChild(), songName); //go right subtree
    }

    Song searchArtist(Song focus, String artist) {
        if (focus == null) {
//            System.out.print("ARTIST cannot be found : " + artist + " : ");
            return null;
        }

        if (focus.artist.equalsIgnoreCase(artist)) {
            System.out.println(focus + "\n");
        }
        searchArtist(focus.getLeftChild(), artist);
        return searchArtist(focus.getRightChild(), artist); //go right subtree
    }

    Song searchGenre(Song focus, String genre) {
        if (focus == null) {
//            System.out.print("ARTIST cannot be found : " + genre + " : ");
            return null;
        }
        if (focus.genre.equalsIgnoreCase(genre)) {
            System.out.println(focus + "\n");  // if they are equal print
        }
        searchGenre(focus.getLeftChild(), genre); // go left subtree and check all the songs' genre
        return searchGenre(focus.getRightChild(), genre); //go right subtree and do the same
    }

    public Song getMinValue(Song focus) {
        Song current = focus;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    public Song getMaxValue(Song focus) {
        Song current = focus;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    public int size(Song focus) {
        return (focus == null) ? 0 : size(focus.getLeftChild()) + size(focus.getRightChild()) + 1;
    }

    public void pathLengths(Song focus, int count) {
        if (focus.getLeftChild() == null && focus.getRightChild() == null) {
            System.out.println("Leaf reached: " + focus + " Path: " + count);
        } else {
            if (focus.getLeftChild() != null) {
                pathLengths(focus.getLeftChild(), ++count);
                count--;
            }
            if (focus.getRightChild() != null) {
                pathLengths(focus.getRightChild(), ++count);
            }
            count--;
        }
    }

}
