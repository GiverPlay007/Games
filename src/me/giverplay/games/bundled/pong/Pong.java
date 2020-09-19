package me.giverplay.games.bundled.pong;

import me.giverplay.games.entity.Entity;
import me.giverplay.games.game.GameBase;
import me.giverplay.games.Main;
import me.giverplay.games.bundled.pong.entities.Ball;
import me.giverplay.games.bundled.pong.entities.Enemy;
import me.giverplay.games.bundled.pong.entities.Player;
import me.giverplay.games.game.KeyBind;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pong extends GameBase
{
  public static final int WIDTH = 240;
  public static final int HEIGHT = 160;
  public static final int SCALE = 3;

  private KeyBind keyBind;
  private Player player;
  private State state;
  private Enemy enemy;
  private Ball ball;

  private Graphics screenGraphics;
  private BufferedImage screen;
  private Graphics graphics;

  private String enter = "Press Enter to restart";
  private String gameOver = "Game Over";
  private String victory = "Point!";

  private boolean gameOverShowing;

  private int maxGameOverFrames = 30;
  private int gameOverFrames = 0;
  private int score;

  public Pong(String name, BufferedImage icon)
  {
    super(name, icon, WIDTH, HEIGHT, SCALE);

    setup();
    initialize();
  }

  private void setup()
  {
    screen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    screenGraphics = screen.getGraphics();

    createBufferStrategy(3);
    graphics = getBufferStrategy().getDrawGraphics();

    keyBind = new KeyBind(this);
  }

  private void initialize()
  {
    gameOverFrames = 0;
    gameOverShowing = false;
    score = 0;
    player = new Player(4, getScaledHeight() / 2, this);
    enemy = new Enemy(getScaledWidth() - 9, getScaledHeight() / 2, this);
    ball = new Ball(getScaledWidth() / 2, getScaledHeight() / 2, this);
    enemy.fixInstance(ball);
    state = State.RUNNING;
  }

  @Override
  public void tick()
  {
    if(state == State.RUNNING)
    {
      if (!hasFocus())
      {
        requestFocus();
      }

      if (!getFrame().isVisible())
      {
        destroy();
        return;
      }

      player.tick();
      enemy.tick();
      ball.tick();
    }
    else
    {
      if(keyBind.enter.isPressed())
      {
        initialize();
      }
    }
  }

  public Ball getBall()
  {
    return ball;
  }

  public void point(Entity entity)
  {
    state = (entity instanceof Player ? State.VICTORY : State.GAME_OVER);
  }

  public void markScore()
  {
    score++;
  }

  public int getScore()
  {
    return this.score;
  }

  public Enemy getEnemy()
  {
    return enemy;
  }

  public Player getPlayer()
  {
    return player;
  }

  public KeyBind getKeyBind()
  {
    return this.keyBind;
  }

  @Override
  public void render()
  {
    screenGraphics.setColor(Color.BLACK);
    screenGraphics.fillRect(0, 0, getScaledWidth(), getScaledHeight());

    player.render(screenGraphics);
    enemy.render(screenGraphics);
    ball.render(screenGraphics);

    graphics.drawImage(screen, 0, 0, getScaledWidth(), getScaledHeight(), null);

    graphics.setColor(Color.WHITE);
    graphics.setFont(new Font("arial", Font.PLAIN, 12));
    graphics.drawString("Score: " + score, 16, 16);

    if(state != State.RUNNING)
    {
      gameOverFrames++;

      if(gameOverFrames >= maxGameOverFrames)
      {
        gameOverFrames = 0;
        gameOverShowing = !gameOverShowing;
      }

      String txt = state == State.GAME_OVER ? gameOver : victory;

      graphics.setColor(new Color(0, 0, 0, 100));
      graphics.fillRect(0, 0, getScaledWidth(), getScaledHeight());

      graphics.setColor(Color.WHITE);
      graphics.setFont(new Font("Arial", Font.BOLD, 32));
      graphics.drawString(txt, textStart(txt), getScaledHeight() / 2 -32);

      if(gameOverShowing)
      {
        graphics.setFont(new Font("Arial", Font.BOLD, 18));
        graphics.drawString(enter, textStart(enter), getScaledHeight() / 2 + 4);
      }
    }

    getBufferStrategy().show();
  }

  private int textStart(String txt)
  {
    return (getScaledWidth() - graphics.getFontMetrics().stringWidth(txt)) / 2;
  }

  @Override
  public void start()
  {
    getFrame().setVisible(true);
  }

  @Override
  public void destroy()
  {
    Main.getMenu().stopGame();
  }
}
