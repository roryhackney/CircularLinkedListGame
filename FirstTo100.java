import java.util.Random;

/**
 * Plays dice game where the first Player to reach 100 wins.
 */
public class FirstTo100 {
    public static void main(String[] args) throws InterruptedException {
        SoundClipTest sound = new SoundClipTest();
        final int PAUSE_TIME = 200;
        int currentHighScore = 0;

        CircularLinkedList<Player> players = new CircularLinkedList<>();
        players.add(new Player("Lilac"));
        players.add(new Player("Daisy"));
        players.add(new Player("Rose"));
        players.add(new Player("Briar"));

        Random random = new Random();

        System.out.println("LET THE GAME BEGIN!");
        Thread.sleep(PAUSE_TIME);

        for (Player player : players) {
            if (player.getName().equals("Lilac")) {
                System.out.println("\nNew Round Starting...");
                //start round sound
                if (sound.clip.isRunning()) {
                    sound.clip.stop();
                }
                sound.clip.setFramePosition(0);
                sound.clip.start();
                Thread.sleep(sound.clip.getMicrosecondLength() / 1000 / 4);
//                sound.clip.stop();
            }
            int roll1 = random.nextInt(6) + 1;
            int roll2 = random.nextInt(6) + 1;
            String printer = player.getName();
            printer += " rolls a " + roll1 + " and a " + roll2;
            printer += " score now totaling "  + player.increaseScore(roll1 + roll2);
            if (player.getScore() > currentHighScore) {
                currentHighScore = player.getScore();
                printer += "...new high player!";
            }
            System.out.println(printer);
            Thread.sleep(PAUSE_TIME);
            if (player.getScore() >= 100) {
                //play victory sound
                System.out.println("\nThe winner is " + player.getName() + " with a score of " + player.getScore() + "!");
                return;
            }
        }
    }
}
