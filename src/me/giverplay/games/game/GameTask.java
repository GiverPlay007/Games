package me.giverplay.games.game;

import me.giverplay.games.Main;
import me.giverplay.games.menu.GameMenu;

public class GameTask implements Runnable
{
  private final GameBase game;
  private final GameMenu menu;

  private int ticks, fps, fpsAvg, tps;

  public GameTask()
  {
    this.menu = Main.getMenu();
    this.game = menu.getGame();
  }

  @Override
  public void run()
  {
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    long now;

    double nsTick = 1000000000 / 60D;
    double unprocessed = 0D;

    while(menu.isRunningGame())
    {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsTick;
      lastTime = now;

      while(unprocessed >= 1)
      {
        game.tick();
        ++ticks;
        --unprocessed;
      }

      fps++;
      game.render();

      if(System.currentTimeMillis() - timer >= 1000)
      {
        fpsAvg = fps;
        tps = ticks;
        fps = 0;
        ticks = 0;
        timer += 1000;
      }
    }
  }

  public int getFpsAvg()
  {
    return fpsAvg;
  }

  public int getTps()
  {
    return tps;
  }
}
