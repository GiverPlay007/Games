package me.giverplay.games.bundled.pong.entities;

import me.giverplay.games.bundled.pong.Pong;
import me.giverplay.games.entity.Entity;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Entity
{
  private Pong game;
  private Ball ball;

  public Enemy(int x, int y, Pong game)
  {
    super(x, y, 5, 64, null, game);

    this.game = game;

    setSpeed(8);
  }

  @Override
  public void tick()
  {
    if(ball.getX() < (float) game.getScaledWidth() / 2)
    {
      return;
    }

    if(nextY() + (float) (getHeight() / 2) >= ball.getY())
    {
      if(!(previousY() <= 0))
      {
        moveY(-getSpeed());
      }
    }

    if(nextY() + (float) (getHeight() / 2) <= ball.getY())
    {
      if((nextY() + getHeight() < game.getScaledHeight()))
      {
        moveY(getSpeed());
      }
    }
  }

  @Override
  public void render(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(getXInt(), getYInt(), getWidth(), getHeight());
  }

  public void fixInstance(Ball ball)
  {
    this.ball = ball;
  }
}
