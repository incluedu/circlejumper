package net.lustenauer.jumper.common;

public class GameManager {

    /* CONSTANTS */
    public static final GameManager INSTANCE = new GameManager();

    /* ATTRIBUTES */
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

    /* CONSTRUCTORS */
    private GameManager() {

    }

    /* PUBLIC METHODS */
    public void reset() {
        score = 0;
        displayScore = 0;
    }

    public void addScore(int amount) {
        score += amount;

        if (score > highScore) {
            highScore = score;
        }
    }

    public void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (int) (100 * delta));
        }

        if (displayHighScore < highScore) {
            displayHighScore = Math.min(score, displayHighScore + (int) (100 * delta));
        }
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }
}
