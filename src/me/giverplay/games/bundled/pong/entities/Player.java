package me.giverplay.games.bundled.pong.entities;

import me.giverplay.games.bundled.pong.Pong;
import me.giverplay.games.entity.Entity;
import me.giverplay.games.game.KeyBind;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity
{
  private Pong game;
  private KeyBind keyBind;

  public Player(int x, int y, Pong game)
  {
    super(x, y, 5, 64, null, game);

    this.game = game;
    this.keyBind = game.getKeyBind();

    setSpeed(10);
  }

  @Override
  public void tick()
  {
    if(keyBind.down.isPressed())
    {
      if(getY() + getSpeed() + getHeight() < game.getScaledHeight())
      {
        moveY(getSpeed());
      }
    }

    if(keyBind.up.isPressed())
    {
      if(previousY() > 0)
      {
        moveY(-getSpeed());
      }
    }
  }

  @Override
  public void render(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(getXInt(), getYInt(), getWidth(), getHeight());
  }
}
