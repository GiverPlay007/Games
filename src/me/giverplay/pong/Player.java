package me.giverplay.pong;

import static me.giverplay.pong.Game.WIDTH;
import static me.giverplay.pong.Game.HEIGHT;
import static me.giverplay.pong.Game.SCALE;

import java.awt.*;

public class Player extends Entity
{
  private Listeners keyBind;

  public Player(int x, int y, Game game)
  {
    super(x, y, 150, 20, new Color(50, 255, 50), game);

    keyBind = game.getKeyBind();
  }

  @Override
  public void tick()
  {
    if(keyBind.right.pressed)
    {
      moveX(speed);

      if(getX() + getWidth() > WIDTH * SCALE)
      {
        setX(WIDTH * SCALE - getWidth());
      }
    }

    if(keyBind.left.pressed)
    {
      moveX(-speed);

      if(getX() < 0)
        setX(0);
    }
  }
}
