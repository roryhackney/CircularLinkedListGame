/**
 * Represents a single Player in a game
 */
public class Player {
    /**
     * name of the Player
     */
    private final String name;
    /**
     * current score of the Player
     */
    private int score;

    /**
     * Constructor
     * @param name  name of the Player; cannot be null or blank. Cannot be changed.
     */
    public Player(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        this.name = name;
        this.score = 0;
    }

    /**
     * Gets the name of the Player
     * @return the Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the score of the Player
     * @return the Player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Increases the Player's score by a whole number greater than 0
     * @param increaseBy amount to increase the score by; must be greater than 0
     * @return the updated score
     */
    public int increaseScore(int increaseBy) {
        if (increaseBy <= 0) {
            throw new IllegalArgumentException("increaseBy must be more than 0");
        }
        return score += increaseBy;
    }

    /**
     * Checks for equality, true if name and score are the same, else false
     */
    @Override
    public boolean equals(Object player2) {
        if (!(player2 instanceof Player)) {
            return false;
        }
        return ((Player)player2).getName().equals(name) &&
                ((Player)player2).getScore() == score;
    }
}
