package me.giverplay.pong;

import java.awt.*;

public class Enemy extends Entity
{
  public Enemy(Game game)
  {
    super(Game.WIDTH / 2, 0, 150, 20, new Color(255, 0, 0), game);
  }

  @Override
  public void tick()
  {

  }
}
