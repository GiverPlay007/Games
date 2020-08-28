package me.giverplay.games.menu;

import me.giverplay.games.graphics.Spritesheet;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GameMenu extends Canvas implements MouseInputListener, Runnable
{
  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;

  private ArrayList<Option> options = new ArrayList<>();

  private Option selected = null;
  private Thread threadMenu;
  private Graphics graphics;
  private JFrame frame;

  private int mx, my;

  private boolean runningGame = false;

  public GameMenu()
  {
    setupFrame();
    setupOptions();

    this.addMouseMotionListener(this);
    this.addMouseListener(this);

    threadMenu = new Thread(this);
    threadMenu.start();
  }

  private void setupFrame()
  {
    this.frame = new JFrame("Games");
    this.frame.add(this);
    this.frame.setResizable(false);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);

    showFrame();

    this.createBufferStrategy(3);
    this.graphics = getBufferStrategy().getDrawGraphics();
  }

  private void setupOptions()
  {
    options.add(new Option(10, 10, Spritesheet.getSprite(0, 0, 32, 32), "Pong", this, null));
  }

  public void showFrame()
  {
    this.frame.setVisible(true);
  }

  public void hideFrame()
  {
    frame.setVisible(false);
  }

  private void draw()
  {
    graphics.setColor(new Color(64, 68, 75));
    graphics.fillRect(0, 0, WIDTH, HEIGHT);

    for(int i = 0; i < options.size(); i++)
    {
      options.get(i).draw(graphics);
    }

    this.getBufferStrategy().show();
  }

  private void update()
  {
    for(int i = 0; i < options.size(); i++)
    {
      Option opt = options.get(i);

      if(mx >= opt.getX() && mx <= opt.getX() + Option.WIDTH && my >= opt.getY() && my <= opt.getY() + Option.HEIGHT)
      {
        selected = opt;
        break;
      }

      selected = null;
    }
  }

  @Override
  public void run()
  {
    while(!runningGame)
    {
      update();
      draw();

      try
      {
        Thread.sleep(50);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public Option getSelected()
  {
    return this.selected;
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if(selected != null)
    {
      selected.launch();
      System.out.println("aaa");
    }
  }

  @Override
  public void mouseMoved(MouseEvent e)
  {
    mx = e.getX();
    my = e.getY();
  }

  @Override
  public void mouseClicked(MouseEvent e) {  }

  @Override
  public void mouseReleased(MouseEvent e) {  }

  @Override
  public void mouseEntered(MouseEvent e) {  }

  @Override
  public void mouseExited(MouseEvent e)  {  }

  @Override
  public void mouseDragged(MouseEvent e)  {  }
}
