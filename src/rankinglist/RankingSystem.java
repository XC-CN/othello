package rankinglist;

import java.io.*;
import java.util.ArrayList;

public class RankingSystem {
    public static ArrayList<Player> players = new ArrayList<>();

    public static boolean checkPlayer(String playerName) {
        try {
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader
                    ("PlayersInformation\\" + playerName + ".txt"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void addPlayer(String playerName) {
        if (!checkPlayer(playerName)) {
            String[] playerInfo = {playerName, "0", "0", "0"};
            writePlayerInfoToFile(playerInfo);
        }
    }

    public static void addWinGames(String playerName) {
        String[] playerInfo = readPlayerInfoFromFile(playerName);
        int winGames = Integer.parseInt(playerInfo[1]);
        winGames++;
        playerInfo[1] = String.valueOf(winGames);
        writePlayerInfoToFile(playerInfo);
    }

    public static void addLoseGames(String playerName) {
        String[] playerInfo = readPlayerInfoFromFile(playerName);
        int loseGames = Integer.parseInt(playerInfo[2]);
        loseGames++;
        playerInfo[2] = String.valueOf(loseGames);
        writePlayerInfoToFile(playerInfo);
    }

    public static void calcWinRate(String playerName) {
        String[] playerInfo = readPlayerInfoFromFile(playerName);
        double winGames = Double.parseDouble(playerInfo[1]);
        double loseGames = Double.parseDouble(playerInfo[2]);
        double winRate = winGames / (winGames + loseGames);
        playerInfo[3] = String.valueOf(winRate);
        writePlayerInfoToFile(playerInfo);
    }

    public static void sortWinRate() {
        try {
            String path = "PlayersInformation";        //要遍历的路径
            File file = new File(path);        //获取其file对象
            File[] fileArray = file.listFiles();//遍历path下的文件和目录，放在File数组中
            for (File file1 : fileArray) {  //遍历File[]数组,获取每个player的winRate
                BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(file1));
                players.add(new Player(reader.readLine(), Integer.parseInt(reader.readLine()),
                        Integer.parseInt(reader.readLine()), Double.parseDouble(reader.readLine())));
            }
            players.sort((a, b) -> {
                if (a.winRates < b.winRates)
                    return 1;
                if (a.winRates == b.winRates)
                    return 0;
                if (a.winRates > b.winRates)
                    return -1;
                return 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writePlayerInfoToFile(String[] playerInfo) {
        try {
            File file = new File("PlayersInformation\\" + playerInfo[0] + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "PlayersInformation\\" + playerInfo[0] + ".txt"));
            writer.write(playerInfo[0] + "\n");
            writer.write(playerInfo[1] + "\n");
            writer.write(playerInfo[2] + "\n");
            writer.write(playerInfo[3] + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] readPlayerInfoFromFile(String playerName) {
        String[] playerInfo = new String[4];
        try {
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader
                    ("PlayersInformation\\" + playerName + ".txt"));
            playerInfo[0] = reader.readLine();
            playerInfo[1] = reader.readLine();
            playerInfo[2] = reader.readLine();
            playerInfo[3] = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerInfo;
    }
}
