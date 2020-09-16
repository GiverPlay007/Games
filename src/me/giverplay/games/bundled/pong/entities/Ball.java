package me.giverplay.games.bundled.pong.entities;

import me.giverplay.games.bundled.pong.Pong;
import me.giverplay.games.entity.Entity;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity
{
  private Pong game;
  private Player player;
  private Enemy enemy;

  public Ball(int x, int y, Pong game)
  {
    super(x, y, 10, 10, null);

    this.game = game;
    this.enemy = game.getEnemy();
    this.player = game.getPlayer();

    setSpeed(20);
    createAngle();
  }

  public void createAngle()
  {
    int angle = random.nextInt(360);
    setDx(Math.cos(Math.toRadians(angle)));
    setDy(Math.sin(Math.toRadians(angle)));
  }

  @Override
  public void tick()
  {
    if(nextX() + getWidth() >= game.getScaledWidth() -1)
    {
      reflectDx();
    }

    if(previousX() <= 0)
    {
      reflectDx();
    }

    if(nextY() + getHeight() >= game.getScaledHeight() -1)
    {
      reflectDy();
    }

    if(previousY() <= 0)
    {
      reflectDy();
    }

    moveX(getDx() * getSpeed());
    moveY(getDy() * getSpeed());

    if(getX() <= 0)
    {
      game.point(enemy);
    }
    else if(getX() + getWidth() >= game.getScaledWidth())
    {
      game.point(player);
    }
  }

  @Override
  public void render(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillOval(getXInt(), getYInt(), getWidth(), getHeight());
  }
}
