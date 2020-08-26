package me.giverplay.pong;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable
{
  public static final int WIDTH = 240, HEIGHT = 160, SCALE = 3;

  private static Game instance;

  private List<Entity> entities = new ArrayList<>();

  private Thread thread;
  private JFrame frame;
  private Graphics graphics;
  private BufferedImage screen;
  private Listeners keyBind;

  private boolean isRunning = false;
  private int ticks, fps;

  public static void main(String[] args)
  {
    instance = new Game();
    instance.start();
  }

  public Game()
  {
    setupFrame();
  }

  private void setupFrame()
  {
    frame = new JFrame("Pongzito");
    frame.setResizable(false);
    frame.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.add(this);

    this.createBufferStrategy(3);
    graphics = this.getBufferStrategy().getDrawGraphics();

    screen = new BufferedImage(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, BufferedImage.TYPE_INT_RGB);

    keyBind = new Listeners(this);
  }

  public synchronized void start()
  {
    isRunning = true;

    init();
    thread = new Thread(this);
    thread.start();
  }

  public synchronized void stop()
  {
    isRunning = false;

    try
    {
      thread.join();
    }
    catch(InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void run()
  {
    long lastTime = System.nanoTime();
    long now;
    long timer = System.currentTimeMillis();

    double nsTick = 1000000000 / 60D;
    double unprocessed = 0;

    while(isRunning)
    {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsTick;
      lastTime = now;

      while(unprocessed >= 1)
      {
        ticks++;
        tick();
        unprocessed--;
      }

      fps++;
      render();

      if(System.currentTimeMillis() - timer >= 1000)
      {
        System.out.println("Running at " + ticks + " ticks and " + fps + " fps");
        fps = 0;
        ticks = 0;
        timer += 1000;
      }
    }
  }

  public void tick()
  {
    for(int i = 0; i < entities.size(); i++)
    {
      entities.get(i).tick();
    }
  }

  public void render()
  {
    Graphics g = screen.getGraphics();

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

    /////////////////////////////////////

    for(int i = 0; i < entities.size(); i++)
    {
      entities.get(i).render(g);
    }

    /////////////////////////////////////
    graphics.drawImage(screen, 0, 0, null);

    this.getBufferStrategy().show();
  }

  private void init()
  {
    entities.clear();
    entities.add(new Player((Game.WIDTH * Game.SCALE) / 2, Game.HEIGHT * Game.SCALE -50, this));
    entities.add(new Enemy(this));
    entities.add(new Bolinha((Game.WIDTH * Game.SCALE) / 2, (Game.HEIGHT * Game.SCALE) / 2, this));
    requestFocus();
  }

  public Listeners getKeyBind()
  {
    return this.keyBind;
  }
}
