package pkg2102_project2_p1.pkg2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//MUHAMMED ALPEREN DOĞRUYOL 218CS2085
//KAAN GÜL 218CS2078

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        AVL_Tree avl = new AVL_Tree();
        AVL_Tree avSongName = new AVL_Tree();
        BS_Tree bstArtistName = new BS_Tree();
        List<Object> songList = new ArrayList<>();
        int keyValue = 10;

        //IF THERE IS NO FILE, PROGRAM CREATES A DEFAULT NEW ONE.
        FileWriter fw = null;
        File song_file = null;
        try {
            song_file = new File("song.txt");
            if (!song_file.exists()) {
                song_file.createNewFile();
            }
            fw = new FileWriter(song_file);
            fw.write("Cooler Than Me;Mike Posner;0;dance pop;2007\n"
                    + "Telephone;Lady Gaga;1;dance pop;2007\n"
                    + "Like A G6;Far East Movement;2;dance pop;2007\n"
                    + "OMG;Usher;3;atl hip hop;2007\n"
                    + "Only Girl;Rihanna;4;pop;2008\n"
                    + "Eenie Meenie;Sean Kingston;5;dance pop;2008\n"
                    + "The Time;The Black Eyed Peas;6;dance pop;2008\n"
                    + "Alejandro;Lady Gaga;7;dance pop;2008\n"
                    + "Your Love Is My Drug;Kesha;8;dance pop;2008\n"
                    + "Meet Me Halfway;The Black Eyed Peas;9;dance pop;2008");
            fw.flush();
            fw.close();
            System.out.println("Read successful\n");
        } catch (IOException e) {
            System.out.println("IO exception");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(song_file))) {
            String line = null;
            String specialCharacter = ";";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(specialCharacter);
                int values2 = Integer.parseInt(values[2]);
                int values4 = Integer.parseInt(values[4]);
                Song s1 = new Song(values[0], values[1], values2, values[3], values4);
                songList.add(s1);
                avl.insert(values[0], values[1], values2, values[3], values4);
                avSongName.insertSongName(values[0], values2);
                bstArtistName.insertArtistName(values[1], values2);
            }
        }
        while (true) {
            System.out.println("\n-----WELCOME TO MUSIC RECORD SYSTEM-----\n");
            System.out.println("1) Add song");
            System.out.println("2) Delete song");
            System.out.println("3) Search record");
            System.out.println("4) Display song(s)");
            System.out.println("5) QUIT");
            int option = scn.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter the song name: ");
                    String songName = scanner.nextLine();
                    System.out.print("Enter the artist's name: ");
                    String artistNm = scanner.nextLine();
                    System.out.print("Enter the song genre: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter the song year: ");
                    int year = scn.nextInt();
                    Song s2 = new Song(songName, artistNm, keyValue, type, year);
                    avl.insert(songName, artistNm, keyValue, type, year); // komple objeden oluşan tree
                    avSongName.insertSongName(songName, keyValue); //şarkı isimlerinden oluşan tree
                    bstArtistName.insertArtistName(artistNm, keyValue); // artistten oluşan tree
                    songList.add(s2);
                    keyValue++;
                    break;
                case 2:
                    System.out.print("Enter the song's key that you want to delete: ");
                    int key = scn.nextInt();
                    avl.delete(key);
                    break;
                case 3: {
                    System.out.println("Search according to...");
                    System.out.println("1: Song name");
                    System.out.println("2: Artist name");
                    System.out.println("3: ID");
                    System.out.println("4: Genre");
                    int op = scn.nextInt();
                    switch (op) {
                        case 1:
                            System.out.print("Enter the song name: ");
                            String songNm = scanner.nextLine();
                            System.out.println(avl.searchSongName(songNm));
                            // System.out.println(avSongName.searchSongName(songNm));
                            break;
                        case 2:
                            System.out.println("Enter the Artist name: ");
                            String artNm = scanner.nextLine();
                            avl.searchArtist(artNm);
                            //bstArtistName.searchArtist(artNm);
                            break;
                        case 3:
                            System.out.println("1: Single ID");
                            System.out.println("2: Between two ID's (interval)");
                            int op2 = scn.nextInt();
                            if (op2 == 1) {
                                System.out.print("Enter the ID: ");
                                int id = scn.nextInt();
                                System.out.println(avl.searchKey(id) + "\n");
                                //System.out.print(avSongName.searchKey(id));
                                //System.out.print(bstArtistName.searchKey(id));
                            } else if (op2 == 2) {
                                System.out.print("Enter a first ID: ");
                                int id1 = scn.nextInt();
                                System.out.print("Enter a second ID: ");
                                int id2 = scn.nextInt();
                                avl.searchRange(id1, id2);
                                System.out.println("");
                                //avSongName.searchRange(id1, id2);
                                //bstArtistName.searchRange(id1, id2);
                            }
                            break;
                        case 4:
                            System.out.print("Enter the genre: ");
                            String genre = scanner.nextLine();
                            System.out.println(avl.searchGenre(genre));
                            break;
                    }
                    break;
                }
                case 4: {
                    System.out.println("1: Preorder");
                    System.out.println("2: Postorder");
                    System.out.println("3: Inorder");
                    int op = scn.nextInt();
                    switch (op) {
                        case 1:
                            avl.preOrder();
                            //avSongName.preOrder();
                            // bstArtistName.preOrder();
                            break;
                        case 2:
                            avl.postOrder();
                            break;
                        case 3:
                            avl.inOrder();
                            break;
                    }
                    break;
                }
                case 5:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}
