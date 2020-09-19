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

    setSpeed(10);
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
    if(getDx() > 0)
    {
      if(nextX() + getDx() + getWidth() < game.getScaledWidth())
      {
        moveX(getDx() * getSpeed());
      }
      else
      {
        while(getX() + getWidth() < game.getScaledWidth())
        {
          moveX(1);
        }

        reflectDx();
      }
    }
    else if(getDx() < 0)
    {
      if(getX() + getDx() > 0)
      {
        moveX(getSpeed() * getDx());
      }
      else
      {
        while (getX() > 0)
        {
          moveX(-1);
        }

        reflectDx();
      }
    }

    if(getDy() > 0)
    {
      if(getY() + getDy() + getHeight() < game.getScaledHeight())
      {
        moveY(getDy() * getSpeed());
      }
      else
      {
        while(getY() + getHeight() < game.getScaledHeight())
        {
          moveY(1);
        }

        reflectDy();
      }
    }
    else if(getDy() < 0)
    {
      if(getY() + getDy() > 0)
      {
        moveY(getDy() * getSpeed());
      }
      else
      {
        while (getY() > 0)
        {
          moveY(-1);
        }
        reflectDy();
      }
    }

    if(getX() < 0)
    {
      game.point(enemy);
    }
    else if(getX() + getWidth() > game.getScaledWidth())
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
