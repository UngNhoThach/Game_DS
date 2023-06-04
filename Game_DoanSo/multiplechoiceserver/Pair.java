package multiplechoiceserver;

import static Utils.Constant.LUCK;
import static Utils.Constant.MATCH_LENGTH;
import static Utils.Constant.NUMBER_AMOUNT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pair {

    private Worker player1;
    private Worker player2;
    private boolean isFull = false;

    String line = null;
    BufferedReader in = null;
    BufferedWriter out = null;

    public int currentNumber;
    private static int currentIndex = 0;
    public static int[] array = new int[NUMBER_AMOUNT];
    Random rand = new Random();

    public int matchTime = MATCH_LENGTH;
    int Checked_Num;
    private boolean isGameStarted = false;
    boolean lucky = false;

    public Pair() {
        RandomizeArray();
        this.Checked_Num = 0;
    }

    public Pair(Worker player1) {
        this.player1 = player1;
        isFull = false;
        this.Checked_Num = 0;
    }

    public Pair(Worker player1, Worker player2) {
        this.player1 = player1;
        this.player2 = player2;
        isFull = true;
        this.Checked_Num = 0;
    }

    public void AddClient(Worker t) {
        if (player1 == null) {
            player1 = t;
        } else if (player2 == null) {
            player2 = t;
            isFull = true;
        }
    }

    public void StartGame() {
        // Kiểm tra số lượng
        if (isFull) {
            // Khởi tạo mảng
            RandomizeArray();
            isGameStarted = true;
            // bat19 đầu game
            write(player1, "gamestart", "");
            write(player2, "gamestart", "");
            // gửi số đầu tiên
            NextNumber();
            // bộ đếm thời gian
            Thread game = new Thread() {
                @Override
                public void run() {
                    double drawInterval = 1000000000;
                    long lastTime = System.nanoTime();
                    double delta = 0;
                    while (isGameStarted) {
                        long currentTime = System.nanoTime();
                        delta += (currentTime - lastTime) / drawInterval;
                        lastTime = currentTime;
                        if (delta >= 1) {
                            if (matchTime <= 0) {
                                Result();
                                return;
                            }
                            matchTime--;
                            String remainingTime = String.format(" %02d:%02d", TimeUnit.SECONDS.toMinutes(matchTime), TimeUnit.SECONDS.toSeconds(matchTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(matchTime)));
                            write(player1, "time", remainingTime);
                            write(player2, "time", remainingTime);
                            delta--;
                        }
                    }
                }
            };
            game.start();
        }
    }

    private void RandomizeArray() {
        currentIndex = 0;
        Checked_Num = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }
// Số tiếp theo

    public void NextNumber() {
        if (isGameStarted) {
            if (currentIndex + 1 <= NUMBER_AMOUNT) {
                currentNumber = array[currentIndex++];
                // Kiểm tra số may mắn
                int luck = rand.nextInt() % 100;
                if (luck > LUCK) {
                    lucky = true;
                    write(player1, "lucky", Integer.toString(currentNumber));
                    write(player2, "lucky", Integer.toString(currentNumber));
                } else {
                    write(player1, "next", Integer.toString(currentNumber));
                    write(player2, "next", Integer.toString(currentNumber));
                }
            } else {
                Result();
            }
        }
    }
// Kết thúc trận đấu

    public void Result() {
        if (player1.getScore() > player2.getScore()) {
            write(player1, "result", "You Win");
            write(player2, "result", "You Lose");
        }
        if (player1.getScore() < player2.getScore()) {
            write(player1, "result", "You Lose");
            write(player2, "result", "You Win");
        }
        if (player1.getScore() == player2.getScore()) {
            write(player1, "result", "Draw");
            write(player2, "result", "Draw");
        }
        isGameStarted = false;
    }

// Nếu một trong hai socket đóng kết nối
    public void CallForStop() {
        isGameStarted = false;
        if (player1.getSocket().isClosed()) {
            write(player2, "result", "You Win");
        } else {
            if (player2.getSocket().isClosed()) {
                write(player1, "result", "You Win");
            }
        }
        Stop();
    }

    // Nếu một bên dừng trò chơi
    public void Surrender(Worker w) {
        if (w.getId() == player1.getId()) {
            write(player1, "result", "You Lose");
            write(player2, "result", "You Win");
        } else if (w.getId() == player2.getId()) {
            write(player1, "result", "You Win");
            write(player2, "result", "You Lose");
        }
        Stop();
    }

    void Click(Worker w) {
        // Kiểm tra số may mắn
        if (!lucky) {
            w.setScore(w.getScore() + 1);
        } else {
            // Số may mắn được 3 điểm và quyền che đồi phương
            w.setScore(w.getScore() + 3);
            write(w, "PowerUp", "");
        }
        lucky = false;
        // 
        if (w.getId() == player1.getId()) {
            write(player1, "IClicked", String.valueOf(currentNumber));
            write(player2, "opponentClicked", String.valueOf(currentNumber));
        } else if (w.getId() == player2.getId()) {
            write(player1, "opponentClicked", String.valueOf(currentNumber));
            write(player2, "IClicked", String.valueOf(currentNumber));
        }
        // Trả điểm số
        write(player1, "point1", String.valueOf(player1.getScore()));
        write(player1, "point2", String.valueOf(player2.getScore()));
        write(player2, "point1", String.valueOf(player1.getScore()));
        write(player2, "point2", String.valueOf(player2.getScore()));
        // số tiếp the
        NextNumber();
        Checked_Num++;
//        Result();
    }
// Dùng che đồi phương 3 giây
    void UsePower(Worker w) {
        if (w.getId() == player1.getId()) {
            write(player2, "blackout", "");
        } else if (w.getId() == player2.getId()) {
            write(player1, "blackout", "");
        }
    }

    public void write(Worker w, String command, String output) {
        w.send(command + "]:$:[" + output);
    }

// KẾt thúc trò chơi
    public void Stop() {
        isGameStarted = false;
//        Server.workerMap.remove(player1.getId());
//        Server.workerMap.remove(player2.getId());
        player1.setPair(null);
        player2.setPair(null);
        Server.pairList.remove(this);
    }

    // Get Set
    public boolean isFull() {
        return isFull;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Worker getPlayer1() {
        return player1;
    }

    public void setPlayer1(Worker player1) {
        this.player1 = player1;
    }

    public Worker getPlayer2() {
        return player2;
    }

    public void setPlayer2(Worker player2) {
        this.player2 = player2;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public void setOut(BufferedWriter out) {
        this.out = out;
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        Pair.currentIndex = currentIndex;
    }

    public static int[] getArray() {
        return array;
    }

    public static void setArray(int[] array) {
        Pair.array = array;
    }

    public int getChecked_Num() {
        return Checked_Num;
    }

    public void setChecked_Num(int Checked_Num) {
        this.Checked_Num = Checked_Num;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean isGameStarted) {
        this.isGameStarted = isGameStarted;
    }

}
