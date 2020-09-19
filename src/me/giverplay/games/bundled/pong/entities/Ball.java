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
    super(x, y, 12, 12, null, game);

    this.game = game;
    this.enemy = game.getEnemy();
    this.player = game.getPlayer();

    setSpeed(8);
    createAngle(65, 115);
  }

  public void createAngle(int min, int max)
  {
    int angle = random.nextInt(max - min) + min;

    setDx(Math.cos(Math.toRadians(angle)));
    setDy(Math.sin(Math.toRadians(angle)));
  }

  @Override
  public void tick()
  {
    if(getDx() > 0)
    {
      if(getX() + getWidth() + getDx() * getSpeed() < game.getScaledWidth() -1)
      {
        moveX(getDx() * getSpeed());
      }
      else
      {
        while(getX() + getWidth() < game.getScaledWidth() -1)
        {
          moveX(1);
        }

        reflectDx();
      }
    }
    else if(getDx() < 0)
    {
      if(getX() + getDx() * getSpeed() > 1)
      {
        moveX(getSpeed() * getDx());
      }
      else
      {
        while (getX() > 1)
        {
          moveX(-1);
        }

        reflectDx();
      }
    }

    if(getDy() > 0)
    {
      if(getY() + getHeight() + getDy() * getSpeed() < game.getScaledHeight() -1)
      {
        moveY(getDy() * getSpeed());
      }
      else
      {
        while(getY() + getHeight() < game.getScaledHeight() -1)
        {
          moveY(1);
        }

        reflectDy();
      }
    }
    else if(getDy() < 0)
    {
      if(getY() + getDy() * getSpeed() > 1)
      {
        moveY(getDy() * getSpeed());
      }
      else
      {
        while (getY() > 1)
        {
          moveY(-1);
        }

        reflectDy();
      }
    }

    if (getCollisionBox().intersects(player.getCollisionBox()))
    {
      if (checkPossibility(20))
      {
        createAngle(65, 135);
      }
      else
      {
        reflectDx();
      }

      while(getX() <= player.getX() + player.getWidth())
      {
        moveX(1);
      }

      game.markScore();
    }

    if(getCollisionBox().intersects(enemy.getCollisionBox()))
    {
      if(checkPossibility(20))
      {
        createAngle(200, 340);
      }
      else
      {
        reflectDx();
      }

      while(getX() >= enemy.getX())
      {
        moveX(-1);
      }
    }

    if(getX() <= 1)
    {
      game.point(enemy);
    }
    else if(getX() + getWidth() >= game.getScaledWidth() -1)
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
