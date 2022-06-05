package pkg2102_project2_p1.pkg2;

public class Song<Type> {

    int key;
    int year;
    int height; //for AVL

    String songName, artist, genre;
    String data;
    Song<Type> leftChild;
    Song<Type> rightChild;

    public Song(String songName, String artist, int key, String genre, int year) {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.key = key;
        this.year = year;
        leftChild = null;
        rightChild = null;
        height = 1;
    }

    public Song(String data, int key) {

        this.data = data;
        this.key = key;
        leftChild = null;
        rightChild = null;
        height = 1;
    }

    @Override
    public String toString() {
        return "Song{" + "key=" + key + ", year=" + year + ", songName=" + songName + ", artist=" + artist + ", genre=" + genre + '}';
    }

    public int compareTo(int newSongKey) {
        return compare(key, newSongKey);
    }

    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1); //if (x<y) return -1; else if (x==y) return 0; else 1;
    }
    
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Song<Type> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Song<Type> leftChild) {
        this.leftChild = leftChild;
    }

    public Song<Type> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Song<Type> rightChild) {
        this.rightChild = rightChild;
    }

}
