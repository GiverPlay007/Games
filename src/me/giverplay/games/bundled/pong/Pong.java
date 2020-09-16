package me.giverplay.games.bundled.pong;

import me.giverplay.games.entity.Entity;
import me.giverplay.games.game.GameBase;
import me.giverplay.games.Main;
import me.giverplay.games.bundled.pong.entities.Ball;
import me.giverplay.games.bundled.pong.entities.Enemy;
import me.giverplay.games.bundled.pong.entities.Player;
import me.giverplay.games.game.KeyBind;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pong extends GameBase
{
  public static final int WIDTH = 240;
  public static final int HEIGHT = 160;
  public static final int SCALE = 3;

  private KeyBind keyBind;
  private Player player;
  private Enemy enemy;
  private Ball ball;

  private BufferedImage screen;
  private Graphics graphics;
  private Graphics screenGraphics;

  public Pong(String name, BufferedImage icon)
  {
    super(name, icon, WIDTH, HEIGHT, SCALE);

    setup();
    initiate();
  }

  private void setup()
  {
    screen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    screenGraphics = screen.getGraphics();

    createBufferStrategy(3);
    graphics = getBufferStrategy().getDrawGraphics();

    keyBind = new KeyBind(this);
  }

  private void initiate()
  {
    player = new Player(4, getScaledHeight() / 2, this);
    enemy = new Enemy(getScaledWidth() - 30, getScaledHeight() / 2, this);
    ball = new Ball(getScaledWidth() / 2, getScaledHeight() / 2, this);
    enemy.fixInstance(ball);
  }

  @Override
  public void tick()
  {
    if(!hasFocus())
    {
      requestFocus();
    }

    if(!getFrame().isVisible())
    {
      destroy();
      return;
    }

    player.tick();
    enemy.tick();
    ball.tick();
  }

  public Ball getBall()
  {
    return ball;
  }

  public void point(Entity entity)
  {
    initiate();
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
    getBufferStrategy().show();
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
